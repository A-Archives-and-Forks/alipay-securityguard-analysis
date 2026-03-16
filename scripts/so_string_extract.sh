#!/bin/bash
# so_string_extract.sh — Extract and categorize security strings from native SOs
# Usage: ./so_string_extract.sh [so_dir] [output_dir]
# Requires: radare2

set -euo pipefail

SO_DIR="${1:-./sg_unpacked/native_libs}"
OUTPUT_DIR="${2:-./so_analysis}"

which r2 >/dev/null 2>&1 || { echo "ERROR: radare2 not found. Install: brew install radare2"; exit 1; }

echo "=== Native SO String Extractor ==="
mkdir -p "$OUTPUT_DIR"

CATEGORIES=(
    "crypto:sm[234]|aes|rsa|hmac|sha|md5|encrypt|decrypt|cipher|key|certificate|ssl|tls|whitebox|wbsm|wbaes"
    "auth:sign|verify|token|cookie|session|auth|login|password|credential|biometric|fingerprint|faceid"
    "anti_tamper:anti|debug|tamper|root|hook|frida|xposed|magisk|substrate|ptrace|mprotect|chroot|integrity"
    "permission:permission|check|allow|deny|block|whitelist|blacklist|domain|url|scheme|redirect"
    "device:device|imei|imsi|oaid|apdid|umid|utdid|android_id|mac|fingerprint|hardware"
    "network:rpc|http|https|request|response|header|cookie|gateway|proxy|spdy|quic"
    "vm:avmp|litevm|bytecode|invoke|dispatch|command|router"
)

for so_file in "$SO_DIR"/*.so; do
    [ -f "$so_file" ] || continue
    so_name=$(basename "$so_file" .so)
    so_out="$OUTPUT_DIR/$so_name"
    mkdir -p "$so_out"

    echo ""
    echo "=== $so_name ==="
    size=$(stat -f%z "$so_file" 2>/dev/null || stat -c%s "$so_file" 2>/dev/null)
    echo "Size: $((size/1024))KB"

    # Extract all strings
    r2 -q -c "izz" "$so_file" 2>/dev/null > "$so_out/all_strings.txt"
    total=$(wc -l < "$so_out/all_strings.txt" | tr -d ' ')
    echo "Total strings: $total"

    # Extract exports
    r2 -q -c "iE" "$so_file" 2>/dev/null > "$so_out/exports.txt"
    exports=$(grep -c "FUNC\|OBJ" "$so_out/exports.txt" 2>/dev/null || echo 0)
    echo "Exports: $exports"

    # Extract imports
    r2 -q -c "ii" "$so_file" 2>/dev/null > "$so_out/imports.txt"

    # Categorize strings
    for cat_def in "${CATEGORIES[@]}"; do
        cat_name="${cat_def%%:*}"
        cat_pattern="${cat_def##*:}"
        matches=$(grep -iE "$cat_pattern" "$so_out/all_strings.txt" 2>/dev/null | grep -v "domain_error" | wc -l | tr -d ' ')
        if [ "$matches" -gt 0 ]; then
            grep -iE "$cat_pattern" "$so_out/all_strings.txt" 2>/dev/null | grep -v "domain_error" > "$so_out/cat_${cat_name}.txt"
            echo "  [$cat_name]: $matches matches"
        fi
    done

    # Generate summary JSON
    cat > "$so_out/summary.json" << SUMEOF
{
  "file": "$(basename "$so_file")",
  "size": $size,
  "total_strings": $total,
  "exports": $exports,
  "categories": {
$(for cat_def in "${CATEGORIES[@]}"; do
    cat_name="${cat_def%%:*}"
    f="$so_out/cat_${cat_name}.txt"
    count=0
    [ -f "$f" ] && count=$(wc -l < "$f" | tr -d ' ')
    echo "    \"$cat_name\": $count,"
done | sed '$ s/,$//')
  }
}
SUMEOF
done

# Generate master report
echo ""
echo "=== Generating Master Report ==="
cat > "$OUTPUT_DIR/report.md" << 'REPORTEOF'
# Native SO Security String Analysis Report

| SO File | Size | Strings | Exports | Crypto | Auth | Anti-Tamper | Permission | Device | Network | VM |
|---------|------|---------|---------|--------|------|-------------|------------|--------|---------|-----|
REPORTEOF

for so_out in "$OUTPUT_DIR"/*/; do
    [ -d "$so_out" ] || continue
    [ -f "$so_out/summary.json" ] || continue
    name=$(basename "$so_out")
    # Parse summary.json with basic tools
    size=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['size']//1024)" 2>/dev/null || echo "?")
    total=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['total_strings'])" 2>/dev/null || echo "?")
    exports=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['exports'])" 2>/dev/null || echo "?")
    crypto=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['categories'].get('crypto',0))" 2>/dev/null || echo "0")
    auth=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['categories'].get('auth',0))" 2>/dev/null || echo "0")
    anti=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['categories'].get('anti_tamper',0))" 2>/dev/null || echo "0")
    perm=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['categories'].get('permission',0))" 2>/dev/null || echo "0")
    dev=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['categories'].get('device',0))" 2>/dev/null || echo "0")
    net=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['categories'].get('network',0))" 2>/dev/null || echo "0")
    vm=$(python3 -c "import json; d=json.load(open('$so_out/summary.json')); print(d['categories'].get('vm',0))" 2>/dev/null || echo "0")
    echo "| $name | ${size}KB | $total | $exports | $crypto | $auth | $anti | $perm | $dev | $net | $vm |" >> "$OUTPUT_DIR/report.md"
done

echo "Report: $OUTPUT_DIR/report.md"
echo "Done."
