#!/bin/bash
# sg_decompile.sh — Decompile all SecurityGuard DEX files with jadx
# Usage: ./sg_decompile.sh [sg_unpacked_dir] [output_dir]

set -euo pipefail

SG_DIR="${1:-./sg_unpacked}"
OUTPUT_DIR="${2:-./sg_decompiled}"

if [ ! -d "$SG_DIR/modules" ]; then
    echo "ERROR: No modules found in $SG_DIR/modules/"
    echo "Run sg_unpack.sh first"
    exit 1
fi

which jadx >/dev/null 2>&1 || { echo "ERROR: jadx not found. Install: brew install jadx"; exit 1; }

echo "=== SecurityGuard DEX Decompiler ==="
echo "Input: $SG_DIR/modules/"
echo "Output: $OUTPUT_DIR/"
echo ""

mkdir -p "$OUTPUT_DIR"

total_classes=0
for moddir in "$SG_DIR/modules/"*/; do
    [ -d "$moddir" ] || continue
    modname=$(basename "$moddir")

    for dex in "$moddir"*.dex "$moddir"classes*.dex; do
        [ -f "$dex" ] || continue
        dex_basename=$(basename "$dex" .dex)
        out="$OUTPUT_DIR/$modname/${dex_basename}"

        echo "[*] Decompiling $modname/$dex_basename..."
        jadx -d "$out" --show-bad-code --threads-count 4 "$dex" 2>&1 | grep -E "^(INFO|ERROR)" | tail -2

        count=$(find "$out" -name "*.java" 2>/dev/null | wc -l | tr -d ' ')
        total_classes=$((total_classes + count))
        echo "    → $count Java classes"
    done
done

echo ""
echo "=== Decompilation Complete ==="
echo "Total classes: $total_classes"
echo "Output: $OUTPUT_DIR/"

# Generate class inventory
echo ""
echo "Generating class inventory..."
find "$OUTPUT_DIR" -name "*.java" -type f | while read f; do
    rel=$(echo "$f" | sed "s|$OUTPUT_DIR/||")
    pkg=$(grep "^package " "$f" 2>/dev/null | head -1 | sed 's/package //; s/;//')
    echo "$rel|$pkg"
done > "$OUTPUT_DIR/class_inventory.txt"

echo "Class inventory: $OUTPUT_DIR/class_inventory.txt"
