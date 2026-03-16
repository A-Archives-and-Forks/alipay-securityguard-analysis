#!/bin/bash
# sg_unpack.sh — SecurityGuard Module Unpacker
# Extracts and unpacks all SecurityGuard ZIP modules from APK
# Usage: ./sg_unpack.sh <apk_path> [output_dir]

set -euo pipefail

APK_PATH="${1:?Usage: $0 <apk_path> [output_dir]}"
OUTPUT_DIR="${2:-./sg_unpacked}"

if [ ! -f "$APK_PATH" ]; then
    echo "ERROR: APK file not found: $APK_PATH"
    exit 1
fi

echo "=== SecurityGuard Unpacker ==="
echo "APK: $APK_PATH"
echo "Output: $OUTPUT_DIR"

mkdir -p "$OUTPUT_DIR"/{raw,modules}

# Step 1: Extract assets containing SecurityGuard modules
echo ""
echo "[1/4] Extracting SecurityGuard assets..."
TMPDIR=$(mktemp -d)
unzip -o "$APK_PATH" "assets/*" -d "$TMPDIR" 2>/dev/null || true

# SecurityGuard modules are typically stored as ZIP files in assets or lib
SG_MODULES=("libsgmain" "libsgmiddletier" "libsgmisc" "libsgnocaptcha" "libsgsecuritybody")

for mod in "${SG_MODULES[@]}"; do
    # Search in multiple locations
    for pattern in "assets/${mod}.zip" "assets/SecurityGuard/${mod}.zip" "assets/sgsdk/${mod}.zip"; do
        found=$(find "$TMPDIR" -name "${mod}*" -type f 2>/dev/null | head -1)
        if [ -n "$found" ]; then
            cp "$found" "$OUTPUT_DIR/raw/"
            echo "  Found: $mod ($(du -h "$found" | awk '{print $1}'))"
            break
        fi
    done
done

# Also try extracting from lib directory (UCB format)
unzip -o "$APK_PATH" "lib/arm64-v8a/libsgmain*" "lib/arm64-v8a/libsgsecurity*" -d "$TMPDIR" 2>/dev/null || true

# If no ZIP modules found, search in the full assets tree
if [ "$(ls -A "$OUTPUT_DIR/raw/" 2>/dev/null | wc -l)" -eq 0 ]; then
    echo "  No standard modules found, searching assets tree..."
    find "$TMPDIR" -name "*.zip" -exec sh -c '
        for f; do
            if unzip -l "$f" 2>/dev/null | grep -q "classes.dex\|AndroidManifest.xml"; then
                name=$(basename "$f" .zip)
                cp "$f" "'"$OUTPUT_DIR/raw/"'"
                echo "  Found module: $name"
            fi
        done
    ' _ {} +
fi

# Step 2: Unpack each module
echo ""
echo "[2/4] Unpacking modules..."
for zip in "$OUTPUT_DIR/raw/"*.zip; do
    [ -f "$zip" ] || continue
    modname=$(basename "$zip" .zip)
    moddir="$OUTPUT_DIR/modules/$modname"
    mkdir -p "$moddir"
    unzip -o "$zip" -d "$moddir" >/dev/null 2>&1

    dex_count=$(find "$moddir" -name "*.dex" | wc -l)
    so_count=$(find "$moddir" -name "*.so" | wc -l)
    echo "  $modname: ${dex_count} DEX, ${so_count} SO"
done

# Step 3: Extract native SOs from main APK
echo ""
echo "[3/4] Extracting native libraries..."
SO_DIR="$OUTPUT_DIR/native_libs"
mkdir -p "$SO_DIR"
unzip -o "$APK_PATH" "lib/arm64-v8a/*.so" -d "$TMPDIR" 2>/dev/null || true

if [ -d "$TMPDIR/lib/arm64-v8a" ]; then
    # Copy security-relevant SOs
    for so in libsgmainso* libsgsecurity* libAPSE* libxriver-core.so libantuser.so \
              libdatabase_sqlcrypto.so libopenssl.so libiot-security-kernel.so \
              libv8jsbridge_jsi.so libnative-v8bridge.so; do
        found=$(find "$TMPDIR/lib/arm64-v8a" -name "$so" 2>/dev/null | head -1)
        if [ -n "$found" ]; then
            cp "$found" "$SO_DIR/"
        fi
    done
    echo "  Extracted $(ls "$SO_DIR/" 2>/dev/null | wc -l) security-relevant SOs"
fi

# Step 4: Summary
echo ""
echo "[4/4] Summary"
echo "  Modules: $(ls "$OUTPUT_DIR/modules/" 2>/dev/null | wc -l)"
echo "  Native SOs: $(ls "$SO_DIR/" 2>/dev/null | wc -l)"
echo "  Output: $OUTPUT_DIR"

# Generate manifest
cat > "$OUTPUT_DIR/manifest.json" << MANIFEST
{
  "apk": "$(basename "$APK_PATH")",
  "timestamp": "$(date -u '+%Y-%m-%dT%H:%M:%SZ')",
  "modules": [
$(for d in "$OUTPUT_DIR/modules/"*/; do
    [ -d "$d" ] || continue
    name=$(basename "$d")
    dex=$(find "$d" -name "*.dex" | wc -l | tr -d ' ')
    so=$(find "$d" -name "*.so" | wc -l | tr -d ' ')
    echo "    {\"name\": \"$name\", \"dex_count\": $dex, \"so_count\": $so},"
done | sed '$ s/,$//')
  ],
  "native_libs": [
$(for f in "$SO_DIR/"*.so; do
    [ -f "$f" ] || continue
    name=$(basename "$f")
    size=$(stat -f%z "$f" 2>/dev/null || stat -c%s "$f" 2>/dev/null)
    echo "    {\"name\": \"$name\", \"size\": $size},"
done | sed '$ s/,$//')
  ]
}
MANIFEST

echo ""
echo "Done. Manifest: $OUTPUT_DIR/manifest.json"

rm -rf "$TMPDIR"
