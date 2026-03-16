#!/bin/bash
# jsapi_audit.sh — Audit JSAPI permission model from decompiled APK
# Finds all BridgeExtension registrations, checks permit() return values
# Usage: ./jsapi_audit.sh <apk_path_or_jadx_dir> [output_dir]

set -euo pipefail

INPUT="${1:?Usage: $0 <apk_or_jadx_dir> [output_dir]}"
OUTPUT_DIR="${2:-./jsapi_audit}"

mkdir -p "$OUTPUT_DIR"

# Determine if input is APK or jadx dir
JADX_DIR="$INPUT"
if [ -f "$INPUT" ] && [[ "$INPUT" == *.apk ]]; then
    echo "Decompiling APK with jadx..."
    JADX_DIR="$OUTPUT_DIR/jadx_sources"
    jadx -d "$JADX_DIR" --show-bad-code --threads-count 4 "$INPUT" 2>&1 | tail -3
fi

if [ ! -d "$JADX_DIR" ]; then
    echo "ERROR: jadx output directory not found"
    exit 1
fi

# Handle nested sources dir
[ -d "$JADX_DIR/sources" ] && JADX_DIR="$JADX_DIR/sources"

echo "=== JSAPI Permission Audit ==="
echo "Source: $JADX_DIR"

# 1. Find all JSAPI registrations
echo ""
echo "[1/4] Finding JSAPI registrations..."
grep -rn "makeRaw\|ExtHubBridgeExtensionManifest\|BridgeExtensionManifest" "$JADX_DIR" --include="*.java" 2>/dev/null | \
    grep -oE '"[a-zA-Z_]+"' | sort -u > "$OUTPUT_DIR/registered_jsapis.txt"
echo "  Found $(wc -l < "$OUTPUT_DIR/registered_jsapis.txt" | tr -d ' ') unique JSAPI names"

# 2. Find all BridgeExtension classes
echo ""
echo "[2/4] Finding BridgeExtension implementations..."
grep -rl "implements BridgeExtension\|extends.*BridgeExtension" "$JADX_DIR" --include="*.java" 2>/dev/null > "$OUTPUT_DIR/bridge_extensions.txt"
echo "  Found $(wc -l < "$OUTPUT_DIR/bridge_extensions.txt" | tr -d ' ') BridgeExtension classes"

# 3. Audit permit() return values
echo ""
echo "[3/4] Auditing permit() methods..."

echo "| Class | permit() Returns | Security Impact |" > "$OUTPUT_DIR/permit_audit.md"
echo "|-------|-----------------|-----------------|" >> "$OUTPUT_DIR/permit_audit.md"

while IFS= read -r file; do
    [ -f "$file" ] || continue
    classname=$(basename "$file" .java)

    # Check if file has permit() method
    if grep -q "public Permission permit()" "$file" 2>/dev/null; then
        # Check what permit() returns
        # Extract the permit method body (simplified: look for return null pattern)
        returns_null=$(grep -A 10 "public Permission permit()" "$file" 2>/dev/null | grep -c "return null" || echo 0)
        returns_permission=$(grep -A 10 "public Permission permit()" "$file" 2>/dev/null | grep -c "return.*Permission\." || echo 0)

        if [ "$returns_null" -gt 0 ] && [ "$returns_permission" -eq 0 ]; then
            impact="NO GUARD (returns null)"
            echo "| $classname | null | **VULNERABLE** |" >> "$OUTPUT_DIR/permit_audit.md"
        elif [ "$returns_permission" -gt 0 ]; then
            echo "| $classname | Permission object | Protected |" >> "$OUTPUT_DIR/permit_audit.md"
        else
            echo "| $classname | Unknown | Needs review |" >> "$OUTPUT_DIR/permit_audit.md"
        fi
    fi
done < "$OUTPUT_DIR/bridge_extensions.txt"

null_count=$(grep -c "VULNERABLE" "$OUTPUT_DIR/permit_audit.md" 2>/dev/null || echo 0)
echo "  permit() returns null: $null_count classes (VULNERABLE)"

# 4. Find EmbedWebView whitelist
echo ""
echo "[4/4] Checking EmbedWebView JSAPI whitelist..."
embed_file=$(grep -rl "EmbedWebViewJsApiPermission" "$JADX_DIR" --include="*.java" 2>/dev/null | head -1)
if [ -n "$embed_file" ]; then
    grep -oE '"[a-zA-Z]+"' "$embed_file" 2>/dev/null | sort -u > "$OUTPUT_DIR/embed_whitelist.txt"
    echo "  EmbedWebView whitelisted APIs: $(wc -l < "$OUTPUT_DIR/embed_whitelist.txt" | tr -d ' ')"
    echo "  File: $embed_file"
fi

# 5. Check H5JsApiPermissionHelper presets
echo ""
preset_file=$(grep -rl "H5JsApiPermissionHelper\|getPresetPermissionStr" "$JADX_DIR" --include="*.java" 2>/dev/null | head -1)
if [ -n "$preset_file" ]; then
    echo "  H5 Permission preset file: $preset_file"
fi

echo ""
echo "=== Audit Complete ==="
echo "  Registered JSAPIs: $(wc -l < "$OUTPUT_DIR/registered_jsapis.txt" | tr -d ' ')"
echo "  BridgeExtensions: $(wc -l < "$OUTPUT_DIR/bridge_extensions.txt" | tr -d ' ')"
echo "  permit()=null (VULNERABLE): $null_count"
echo ""
echo "Reports:"
echo "  $OUTPUT_DIR/permit_audit.md"
echo "  $OUTPUT_DIR/registered_jsapis.txt"
echo "  $OUTPUT_DIR/embed_whitelist.txt"
