# SecurityGuard v8000 vs v9000 — Version Comparison Analysis

> APK versions: v10.8.30.8000 vs v10.8.30.9000
> SG internal version: both report 6.6.230507 (via .version.so)
> Analysis date: 2026-03-17
> **IMPORTANT**: Both APKs built on 2026-01-26 — pre-disclosure. Differences are build variants, NOT patches.

## Binary Size Changes

| Module | v8000 | v9000 | Delta | Notes |
|--------|-------|-------|-------|-------|
| sgmain | 2.14MB | 1.14MB | **-47%** | Massive reduction |
| sgmiddletier | 921KB | 597KB | **-35%** | Significant |
| sgmisc | 153KB | 85KB | **-45%** | Significant |
| sgnocaptcha | 137KB | 79KB | **-42%** | Significant |
| sgsecurity | 1.09MB | 949KB | **-13%** | Moderate |
| **APSE** | 4.13MB | 4.13MB | **0%** | **Identical binary** (sha1 match) |

## Naming Convention Change

- v8000: `libsgmainso-6.6.230507.so` (version in filename)
- v9000: `libsgmain.so` + `libsgmainso.version.so` (version in separate 10-byte file)

## String Analysis

| Module | v8000 strings | v9000 strings | Change |
|--------|--------------|--------------|--------|
| sgmain | 7,133 | 12,042 | **+69%** (more strings but more obfuscated) |
| sgsecurity | 10,432 | 11,566 | +11% |
| sgmiddletier | 3,226 | 6,308 | **+96%** (nearly doubled) |

## Key Strings Removed in v9000

The following security-relevant strings present in v8000 are **absent** from v9000:

| String | Category | Significance |
|--------|----------|-------------|
| `LiteVM` | AVMP | VM identifier — now obfuscated |
| `__uvm_call_registry_` | AVMP | UVM native function registration |
| `__builtin_uvm_bind_native_callback` | AVMP | UVM callback binding |
| `sgcookiecheck` | Cookie | Cookie security validation |
| `wkcookiesync` | Cookie | WebKit cookie synchronization |
| `[insndebug]` | Debug | Debug detection logging |
| `$tIsys_epolptrace_s` | Anti-debug | Obfuscated ptrace detection |
| `__android_log_print` | Logging | Android logging (now stripped) |
| C++ runtime symbols | Infrastructure | `__cxa_*` exception handling symbols |

## Interpretation

### v9000 represents a **hardening** of SecurityGuard, not a functional change:

1. **Symbol stripping**: C++ runtime symbols (`__cxa_throw`, `__android_log_print`) removed from STRTAB — standard hardening practice
2. **String encryption strengthened**: Security identifiers (`LiteVM`, `sgcookiecheck`) likely encrypted rather than removed — the features still exist but are harder to identify statically
3. **Binary size reduction**: Consistent with aggressive symbol stripping + possible LTO (Link-Time Optimization)
4. **APSE unchanged**: Anti-tampering module is identical — no security logic changes in the protection layer
5. **Version unchanged**: Both report `6.6.230507` — this is a recompilation, not a new version

### What was NOT changed:
- APSE/BlueShield (identical binary)
- UCB module stubs (identical hashes)
- Internal version number
- Core architecture (single JNI entry still present)

### Implication for our research:
- Our v8000 analysis remains valid — the architecture is unchanged
- v9000 would be harder to analyze statically due to better obfuscation
- **CORRECTION**: Both APKs were built on 2026-01-26 (3 weeks BEFORE our disclosure on 2026-02-16)
- v8000 was downloaded Feb 16, v9000 downloaded Mar 15 — but build dates are identical
- The differences are likely A/B testing variants or regional/channel builds, NOT post-disclosure patches
- **We CANNOT claim these changes are a response to our research** — that would be factually incorrect
- The 8000/9000 suffix is likely a channel identifier, not a sequential version number
