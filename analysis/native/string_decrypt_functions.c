// === J2qaFN4xHz @ 0012b250 (16 bytes) — STRING DECRYPTION FUNCTION ===

void J2qaFN4xHz(void)

{
  (*(code *)PTR_J2qaFN4xHz_00315158)();
  return;
}



// === HlupqL3ZAR @ 0012b680 (16 bytes) — STRING DECRYPTION FUNCTION ===

void HlupqL3ZAR(void)

{
  (*(code *)PTR_HlupqL3ZAR_00315370)();
  return;
}



// === pDhsAJkF7h @ 0012bcc0 (16 bytes) — STRING DECRYPTION FUNCTION ===

void pDhsAJkF7h(void)

{
  (*(code *)PTR_pDhsAJkF7h_00315690)();
  return;
}



// === HlupqL3ZAR @ 0013dee8 (164 bytes) — STRING DECRYPTION FUNCTION ===

void HlupqL3ZAR(byte *param_1,uint param_2,char *param_3,byte *param_4,uint param_5,char param_6)

{
  long lVar1;
  uint uVar2;
  bool bVar3;
  uint uVar4;
  uint uVar5;
  long lVar6;
  long lVar7;
  long lVar8;
  
  if (param_2 != 0) {
    bVar3 = (param_2 & 1) != 0;
    if (bVar3) {
      *param_1 = *param_4 ^ *param_3 - param_6;
    }
    if (param_2 != 1) {
      lVar1 = (ulong)bVar3 + 1;
      lVar8 = 0;
      do {
        uVar2 = (int)lVar1 + (int)lVar8;
        uVar4 = 0;
        if (param_5 != 0) {
          uVar4 = (uVar2 - 1) / param_5;
        }
        uVar5 = 0;
        if (param_5 != 0) {
          uVar5 = uVar2 / param_5;
        }
        lVar7 = lVar8 + (ulong)bVar3;
        param_1[lVar7] =
             param_4[(uVar2 - 1) - uVar4 * param_5] ^ param_3[lVar8 + lVar1 + -1] - param_6;
        lVar6 = lVar8 + lVar1;
        lVar8 = lVar8 + 2;
        (param_1 + lVar7)[1] = param_4[uVar2 - uVar5 * param_5] ^ param_3[lVar6] - param_6;
      } while (param_2 - bVar3 != (int)lVar8);
    }
  }
  return;
}



// === pDhsAJkF7h @ 0013df8c (164 bytes) — STRING DECRYPTION FUNCTION ===

void pDhsAJkF7h(char *param_1,uint param_2,byte *param_3,byte *param_4,uint param_5,char param_6)

{
  long lVar1;
  uint uVar2;
  bool bVar3;
  uint uVar4;
  uint uVar5;
  long lVar6;
  long lVar7;
  long lVar8;
  
  if (param_2 != 0) {
    bVar3 = (param_2 & 1) != 0;
    if (bVar3) {
      *param_1 = (*param_4 ^ *param_3) + param_6;
    }
    if (param_2 != 1) {
      lVar1 = (ulong)bVar3 + 1;
      lVar8 = 0;
      do {
        uVar2 = (int)lVar1 + (int)lVar8;
        uVar4 = 0;
        if (param_5 != 0) {
          uVar4 = (uVar2 - 1) / param_5;
        }
        uVar5 = 0;
        if (param_5 != 0) {
          uVar5 = uVar2 / param_5;
        }
        lVar7 = lVar8 + (ulong)bVar3;
        param_1[lVar7] =
             (param_4[(uVar2 - 1) - uVar4 * param_5] ^ param_3[lVar8 + lVar1 + -1]) + param_6;
        lVar6 = lVar8 + lVar1;
        lVar8 = lVar8 + 2;
        (param_1 + lVar7)[1] = (param_4[uVar2 - uVar5 * param_5] ^ param_3[lVar6]) + param_6;
      } while (param_2 - bVar3 != (int)lVar8);
    }
  }
  return;
}



// === J2qaFN4xHz @ 0013e030 (164 bytes) — STRING DECRYPTION FUNCTION ===

void J2qaFN4xHz(char *param_1,uint param_2,byte *param_3,char *param_4,uint param_5,byte param_6)

{
  long lVar1;
  uint uVar2;
  bool bVar3;
  uint uVar4;
  uint uVar5;
  long lVar6;
  long lVar7;
  long lVar8;
  
  if (param_2 != 0) {
    bVar3 = (param_2 & 1) != 0;
    if (bVar3) {
      *param_1 = (*param_3 ^ param_6) - *param_4;
    }
    if (param_2 != 1) {
      lVar1 = (ulong)bVar3 + 1;
      lVar8 = 0;
      do {
        uVar2 = (int)lVar1 + (int)lVar8;
        uVar4 = 0;
        if (param_5 != 0) {
          uVar4 = (uVar2 - 1) / param_5;
        }
        uVar5 = 0;
        if (param_5 != 0) {
          uVar5 = uVar2 / param_5;
        }
        lVar7 = lVar8 + (ulong)bVar3;
        param_1[lVar7] =
             (param_3[lVar8 + lVar1 + -1] ^ param_6) - param_4[(uVar2 - 1) - uVar4 * param_5];
        lVar6 = lVar8 + lVar1;
        lVar8 = lVar8 + 2;
        (param_1 + lVar7)[1] = (param_3[lVar6] ^ param_6) - param_4[uVar2 - uVar5 * param_5];
      } while (param_2 - bVar3 != (int)lVar8);
    }
  }
  return;
}



// === KEl52mTtCb @ 0013e0d4 (104 bytes) — STRING DECRYPTION FUNCTION ===

undefined4 KEl52mTtCb(uint *param_1,uint param_2)

{
  uint local_18;
  uint *local_10;
  
  local_18 = 0;
  local_10 = param_1;
  while( true ) {
    if (param_2 >> 2 <= local_18) {
      return 0;
    }
    if ((*local_10 & 0xffe0001f) == 0xd4200000) break;
    local_10 = local_10 + 1;
    local_18 = local_18 + 1;
  }
  return 1;
}


