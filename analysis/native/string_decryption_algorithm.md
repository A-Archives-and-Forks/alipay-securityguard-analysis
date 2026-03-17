# sgmain String Decryption Algorithm — Fully Reversed

## 3 Decryption Functions + 1 Anti-Debug

All strings in sgmain.so are encrypted at compile time and decrypted at runtime using 3 XOR-based functions. Each encrypted string has a "decrypted" flag (DAT_*) to ensure one-time decryption.

### Function 1: HlupqL3ZAR
```c
// Signature: void HlupqL3ZAR(byte *dest, uint len, char *src, byte *key, uint keyLen, char xorByte)
// Algorithm: dest[i] = key[i % keyLen] XOR src[i] - xorByte
dest[i] = key[i % keyLen] ^ src[i] - xorByte;
```

### Function 2: pDhsAJkF7h  
```c
// Signature: void pDhsAJkF7h(char *dest, uint len, byte *src, byte *key, uint keyLen, char xorByte)
// Algorithm: dest[i] = (key[i % keyLen] XOR src[i]) + xorByte
dest[i] = (key[i % keyLen] ^ src[i]) + xorByte;
```

### Function 3: J2qaFN4xHz
```c
// Signature: void J2qaFN4xHz(char *dest, uint len, byte *src, char *key, uint keyLen, byte xorByte)
// Algorithm: dest[i] = (src[i] XOR xorByte) - key[i % keyLen]
dest[i] = (src[i] ^ xorByte) - key[i % keyLen];
```

### Function 4: KEl52mTtCb (Anti-Debug)
```c
// Scans for BRK #0x0 instructions (0xd4200000) in code
// Used to detect software breakpoints set by debuggers
// Returns 1 if breakpoint found, 0 otherwise
```

## Decryption Pattern in Code

Every encrypted string follows this pattern:
```c
if ((DAT_flag & 1) == 0) {          // Check if already decrypted
    HlupqL3ZAR(dest, len, src, key, keyLen, xorByte);  // Decrypt
    DAT_flag = 1;                    // Mark as decrypted
}
```

## How to Decrypt All Strings

### Method 1: Static (using the algorithm)
```python
def decrypt_hlupqL3ZAR(src, key, keyLen, xorByte):
    """dest[i] = key[i % keyLen] ^ src[i] - xorByte"""
    return bytes([(key[i % keyLen] ^ src[i]) - xorByte & 0xFF for i in range(len(src))])

def decrypt_pDhsAJkF7h(src, key, keyLen, xorByte):
    """dest[i] = (key[i % keyLen] ^ src[i]) + xorByte"""
    return bytes([(key[i % keyLen] ^ src[i]) + xorByte & 0xFF for i in range(len(src))])

def decrypt_J2qaFN4xHz(src, key, keyLen, xorByte):
    """dest[i] = (src[i] ^ xorByte) - key[i % keyLen]"""
    return bytes([(src[i] ^ xorByte) - key[i % keyLen] & 0xFF for i in range(len(src))])
```

### Method 2: Dynamic (using stnel/frida)
```javascript
// Hook all 3 decrypt functions and log the results
['HlupqL3ZAR', 'HlupqL3ZAR', 'pDhsAJkF7h', 'J2qaFN4xHz'].forEach(function(name) {
    var addr = Module.findExportByName("libsgmainso-6.6.230507.so", name);
    if (addr) {
        Interceptor.attach(addr, {
            onEnter: function(args) {
                this.dest = args[0];
                this.len = args[1].toInt32();
            },
            onLeave: function(retval) {
                if (this.len > 0) {
                    var str = Memory.readUtf8String(this.dest, this.len);
                    console.log("[DECRYPT] " + str);
                }
            }
        });
    }
});
```

## Statistics
- 166 decrypt calls found in just 13 analyzed functions
- Estimated 1000+ encrypted strings in full SO (3034 functions)
- 3 variants use slightly different XOR/add/sub combinations for diversity
