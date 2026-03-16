#!/bin/bash
# sg_full_analysis.sh — One-click full SecurityGuard analysis pipeline
# Usage: ./sg_full_analysis.sh <apk_path> [old_apk_for_diff]

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
APK="${1:?Usage: $0 <apk_path> [old_apk_for_diff]}"
OLD_APK="${2:-}"
WORK_DIR="$(pwd)/sg_output_$(date +%Y%m%d_%H%M%S)"

echo "╔══════════════════════════════════════════════╗"
echo "║  SecurityGuard Full Analysis Pipeline v1.0   ║"
echo "╚══════════════════════════════════════════════╝"
echo ""
echo "APK: $APK"
echo "Output: $WORK_DIR"
echo ""

mkdir -p "$WORK_DIR"

# Phase 1: Unpack
echo "━━━ Phase 1: Unpack ━━━"
bash "$SCRIPT_DIR/sg_unpack.sh" "$APK" "$WORK_DIR/unpacked"
echo ""

# Phase 2: Decompile
echo "━━━ Phase 2: Decompile ━━━"
bash "$SCRIPT_DIR/sg_decompile.sh" "$WORK_DIR/unpacked" "$WORK_DIR/decompiled"
echo ""

# Phase 3: Native SO Analysis
echo "━━━ Phase 3: Native SO String Analysis ━━━"
bash "$SCRIPT_DIR/so_string_extract.sh" "$WORK_DIR/unpacked/native_libs" "$WORK_DIR/so_analysis"
echo ""

# Phase 4: Command ID Mapping
echo "━━━ Phase 4: Command ID Mapping ━━━"
bash "$SCRIPT_DIR/sg_command_trace.sh" "$WORK_DIR/decompiled" "$WORK_DIR/analysis/command_map.md"
echo ""

# Phase 5: Version Diff (optional)
if [ -n "$OLD_APK" ] && [ -f "$OLD_APK" ]; then
    echo "━━━ Phase 5: Version Diff ━━━"
    bash "$SCRIPT_DIR/version_diff.sh" "$OLD_APK" "$APK" "$WORK_DIR/version_diff"
    echo ""
fi

# Phase 6: JSAPI Permission Audit
echo "━━━ Phase 6: JSAPI Permission Audit ━━━"
bash "$SCRIPT_DIR/jsapi_audit.sh" "$APK" "$WORK_DIR/jsapi_audit" 2>/dev/null || echo "  [SKIP] jsapi_audit.sh not found or failed"
echo ""

# Summary
echo "╔══════════════════════════════════════════════╗"
echo "║              Analysis Complete               ║"
echo "╚══════════════════════════════════════════════╝"
echo ""
echo "Output structure:"
echo "  $WORK_DIR/"
echo "  ├── unpacked/          — Extracted SG modules + native SOs"
echo "  ├── decompiled/        — jadx Java source"
echo "  ├── so_analysis/       — Native string categorization"
echo "  ├── analysis/          — Command ID map"
echo "  └── version_diff/      — Version comparison (if provided)"
echo ""
du -sh "$WORK_DIR" 2>/dev/null
