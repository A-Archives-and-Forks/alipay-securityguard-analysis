#!/bin/bash
# version_diff.sh — Binary diff SecurityGuard functions between APK versions
# Usage: ./version_diff.sh <old_apk> <new_apk> [output_dir]
# Requires: radare2

set -euo pipefail

OLD_APK="${1:?Usage: $0 <old_apk> <new_apk> [output_dir]}"
NEW_APK="${2:?Usage: $0 <old_apk> <new_apk> [output_dir]}"
OUTPUT_DIR="${3:-./version_diff}"

which r2 >/dev/null 2>&1 || { echo "ERROR: radare2 not found"; exit 1; }

echo "=== SecurityGuard Version Diff ==="
echo "Old: $OLD_APK"
echo "New: $NEW_APK"

mkdir -p "$OUTPUT_DIR"/{old,new}

# Step 1: Extract SOs from both versions
echo ""
echo "[1/4] Extracting native libraries..."
TMPOLD=$(mktemp -d)
TMPNEW=$(mktemp -d)
unzip -o "$OLD_APK" "lib/arm64-v8a/*.so" -d "$TMPOLD" 2>/dev/null || true
unzip -o "$NEW_APK" "lib/arm64-v8a/*.so" -d "$TMPNEW" 2>/dev/null || true

# Security-critical SOs to compare
TARGET_SOS="libxriver-core.so libsgmainso.ucb.so libsgsecuritybodyso.ucb.so libAPSE_1.9.41.so libantuser.so"

# Step 2: File-level diff
echo ""
echo "[2/4] File-level comparison..."
echo "| SO File | Old Size | New Size | Delta | Changed? |" > "$OUTPUT_DIR/file_diff.md"
echo "|---------|----------|----------|-------|----------|" >> "$OUTPUT_DIR/file_diff.md"

for so in $TARGET_SOS; do
    old_f="$TMPOLD/lib/arm64-v8a/$so"
    new_f="$TMPNEW/lib/arm64-v8a/$so"
    if [ -f "$old_f" ] && [ -f "$new_f" ]; then
        old_size=$(stat -f%z "$old_f" 2>/dev/null || stat -c%s "$old_f")
        new_size=$(stat -f%z "$new_f" 2>/dev/null || stat -c%s "$new_f")
        delta=$((new_size - old_size))
        old_hash=$(md5 -q "$old_f" 2>/dev/null || md5sum "$old_f" | awk '{print $1}')
        new_hash=$(md5 -q "$new_f" 2>/dev/null || md5sum "$new_f" | awk '{print $1}')
        changed="NO"
        [ "$old_hash" != "$new_hash" ] && changed="**YES**"
        echo "| $so | $old_size | $new_size | $delta | $changed |" >> "$OUTPUT_DIR/file_diff.md"
        cp "$old_f" "$OUTPUT_DIR/old/"
        cp "$new_f" "$OUTPUT_DIR/new/"
    fi
done

cat "$OUTPUT_DIR/file_diff.md"

# Step 3: Function-level diff for libxriver-core.so
echo ""
echo "[3/4] Function-level diff (libxriver-core.so)..."

OLD_XRIVER="$OUTPUT_DIR/old/libxriver-core.so"
NEW_XRIVER="$OUTPUT_DIR/new/libxriver-core.so"

SECURITY_FUNCTIONS=(
    "nativeCheckValidDomain"
    "nativeCheckDomain"
    "nativeShouldLoadUrl"
    "nativeCheckJSAPI"
    "nativeCheckOperationJSAPI"
    "_1getHttpRequestAllowedDomain"
)

if [ -f "$OLD_XRIVER" ] && [ -f "$NEW_XRIVER" ]; then
    echo "| Function | Old Addr | Old Size | New Addr | New Size | Hash Match? |" > "$OUTPUT_DIR/function_diff.md"
    echo "|----------|----------|----------|----------|----------|-------------|" >> "$OUTPUT_DIR/function_diff.md"

    for func in "${SECURITY_FUNCTIONS[@]}"; do
        # Get old function info
        old_info=$(r2 -q -c "iE~$func" "$OLD_XRIVER" 2>/dev/null | head -1)
        new_info=$(r2 -q -c "iE~$func" "$NEW_XRIVER" 2>/dev/null | head -1)

        if [ -n "$old_info" ] && [ -n "$new_info" ]; then
            old_addr=$(echo "$old_info" | awk '{print $1}')
            old_size=$(echo "$old_info" | awk '{print $4}')
            new_addr=$(echo "$new_info" | awk '{print $1}')
            new_size=$(echo "$new_info" | awk '{print $4}')

            # Hash the function bytes
            old_hash=$(r2 -q -c "s $old_addr; p8 $old_size" "$OLD_XRIVER" 2>/dev/null | md5 -q 2>/dev/null || echo "?")
            new_hash=$(r2 -q -c "s $new_addr; p8 $new_size" "$NEW_XRIVER" 2>/dev/null | md5 -q 2>/dev/null || echo "?")

            match="SAME"
            [ "$old_hash" != "$new_hash" ] && match="**MODIFIED**"

            echo "| $func | $old_addr | $old_size | $new_addr | $new_size | $match |" >> "$OUTPUT_DIR/function_diff.md"

            # If modified, dump disassembly
            if [ "$match" = "**MODIFIED**" ]; then
                r2 -q -e 'scr.color=0' -c "s $old_addr; pd 30" "$OLD_XRIVER" 2>/dev/null > "$OUTPUT_DIR/${func}_old.asm"
                r2 -q -e 'scr.color=0' -c "s $new_addr; pd 30" "$NEW_XRIVER" 2>/dev/null > "$OUTPUT_DIR/${func}_new.asm"
                echo "  $func: MODIFIED (disassembly saved)"
            fi
        fi
    done

    cat "$OUTPUT_DIR/function_diff.md"
fi

# Step 4: Export diff
echo ""
echo "[4/4] Export symbol diff..."
r2 -q -c "iE" "$OLD_XRIVER" 2>/dev/null | awk '{print $NF}' | sort > "$OUTPUT_DIR/old_exports.txt"
r2 -q -c "iE" "$NEW_XRIVER" 2>/dev/null | awk '{print $NF}' | sort > "$OUTPUT_DIR/new_exports.txt"

added=$(diff "$OUTPUT_DIR/old_exports.txt" "$OUTPUT_DIR/new_exports.txt" | grep "^>" | wc -l | tr -d ' ')
removed=$(diff "$OUTPUT_DIR/old_exports.txt" "$OUTPUT_DIR/new_exports.txt" | grep "^<" | wc -l | tr -d ' ')
echo "Exports: +$added added, -$removed removed"

diff "$OUTPUT_DIR/old_exports.txt" "$OUTPUT_DIR/new_exports.txt" > "$OUTPUT_DIR/export_diff.txt" 2>/dev/null || true

# Step 5: DEX-level diff
echo ""
echo "[5/5] DEX file size comparison..."
echo "| DEX | Old Size | New Size | Delta |" > "$OUTPUT_DIR/dex_diff.md"
echo "|-----|----------|----------|-------|" >> "$OUTPUT_DIR/dex_diff.md"

for i in $(seq 1 19); do
    dex="classes${i}.dex"
    [ "$i" -eq 1 ] && dex="classes.dex"
    old_size=$(unzip -l "$OLD_APK" "$dex" 2>/dev/null | tail -1 | awk '{print $1}')
    new_size=$(unzip -l "$NEW_APK" "$dex" 2>/dev/null | tail -1 | awk '{print $1}')
    [ -z "$old_size" ] || [ "$old_size" = "0" ] && continue
    delta=$((new_size - old_size))
    [ "$delta" -ne 0 ] && echo "| $dex | $old_size | $new_size | $delta |" >> "$OUTPUT_DIR/dex_diff.md"
done

cat "$OUTPUT_DIR/dex_diff.md"

echo ""
echo "=== Done ==="
echo "Output: $OUTPUT_DIR/"

rm -rf "$TMPOLD" "$TMPNEW"
