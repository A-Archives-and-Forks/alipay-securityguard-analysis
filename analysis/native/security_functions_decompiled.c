// === FUN_001589f4 @ 001589f4 (4276 bytes) [cookie_sm4_init] ===

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FUN_001589f4(long *param_1,ulong *param_2,uint param_3,long param_4)

{
  long lVar1;
  undefined8 uVar2;
  ulong uVar3;
  int iVar4;
  uint uVar5;
  int iVar6;
  ulong uVar7;
  long lVar8;
  long *plVar9;
  undefined8 *puVar10;
  long *plVar11;
  FILE *__s;
  size_t sVar12;
  ulong uVar13;
  undefined8 uVar14;
  undefined8 uVar15;
  long lVar16;
  code *pcVar17;
  bool bVar18;
  long lVar19;
  char *__s1;
  undefined8 *puVar20;
  undefined8 *puVar21;
  char *__s_00;
  long lVar22;
  void *__ptr;
  long lVar23;
  undefined8 *puVar24;
  ulong uVar25;
  long lVar26;
  int *piVar27;
  int local_1ec;
  long *local_1e8;
  int local_1c4;
  ulong local_1c0;
  undefined4 uStack_1b8;
  undefined4 uStack_1b4;
  undefined4 uStack_1b0;
  undefined4 local_1ac;
  undefined4 uStack_1a8;
  undefined8 local_1a4;
  undefined8 local_19c;
  undefined8 uStack_194;
  undefined8 local_18c;
  undefined8 local_180;
  undefined4 uStack_178;
  undefined4 uStack_174;
  undefined4 uStack_170;
  undefined4 uStack_16c;
  undefined4 uStack_168;
  ulong *local_164;
  undefined8 uStack_154;
  undefined8 local_14c;
  
  lVar1 = tpidr_el0;
  lVar16 = *(long *)(lVar1 + 0x28);
  if ((DAT_0031bded & 1) == 0) {
    HlupqL3ZAR(0x31bdc6,2,&DAT_0029c6ed,&DAT_0029c6eb,2,0x45);
    DAT_0031bded = 1;
  }
  if ((DAT_0031bdec & 1) == 0) {
    HlupqL3ZAR(0x31bdbd,9,&DAT_0029c6e2,&DAT_0029c6df,3,0x87);
    DAT_0031bdec = 1;
  }
  if ((DAT_0031bdeb & 1) == 0) {
    pDhsAJkF7h(0x31bdb8,5,&DAT_0029c6da,&DAT_0029c6d7,3,0x81);
    DAT_0031bdeb = 1;
  }
  if ((DAT_0031bde7 & 1) == 0) {
    HlupqL3ZAR(0x31bda6,8,&DAT_0029c688,&DAT_0029c6cb,3,0xb9);
    DAT_0031bde7 = 1;
  }
  if ((DAT_0031bde6 & 1) == 0) {
    HlupqL3ZAR(0x31bda0,6,&DAT_0029c6c5,&DAT_0029b50c,4,0x47);
    DAT_0031bde6 = 1;
  }
  if ((DAT_0031bde5 & 1) == 0) {
    pDhsAJkF7h(0x31bd9b,5,&DAT_0029c6c0,&DAT_0029c6be,2,0xbb);
    DAT_0031bde5 = 1;
  }
  if ((DAT_0031bde4 & 1) == 0) {
    J2qaFN4xHz(0x31bd99,2,&DAT_0029c6bc,&DAT_0029b508,4,0x43);
    DAT_0031bde4 = 1;
  }
  if ((DAT_0031bde3 & 1) == 0) {
    J2qaFN4xHz(0x31bd94,5,&DAT_0029c6b7,&DAT_0029c6b5,2,0xfb);
    DAT_0031bde3 = 1;
  }
  if ((DAT_0031bde2 & 1) == 0) {
    J2qaFN4xHz(0x31bd89,0xb,&DAT_0029c6aa,&DAT_0029c6a7,3,0xad);
    DAT_0031bde2 = 1;
  }
  if ((DAT_0031bde1 & 1) == 0) {
    HlupqL3ZAR(0x31bd86,3,&DAT_0029c6a4,&DAT_0029b48e,4,7);
    DAT_0031bde1 = 1;
  }
  if ((DAT_0031bde0 & 1) == 0) {
    J2qaFN4xHz(0x31bd80,6,&DAT_0029c69e,&DAT_0029b48a,4,0x41);
    DAT_0031bde0 = 1;
  }
  _DAT_0031bd78 = param_4;
  if (*(long *)(param_4 + 8) != 0) {
    uVar7 = (**(code **)(*(long *)(param_4 + 8) + 0x30))();
    *param_2 = uVar7;
    uVar3 = DAT_0029c680;
    uVar2 = DAT_0029c678;
    if ((uVar7 != 0) && (0 < (int)param_1[1])) {
      lVar22 = 0;
      uVar25 = 99;
LAB_00158d38:
      local_1c4 = 0;
      piVar27 = *(int **)(*param_1 + lVar22 * 8);
      if (*piVar27 == 2) {
        iVar4 = piVar27[1];
        if (iVar4 != 0) {
          if (iVar4 == 2) {
            lVar8 = FUN_00242634(uVar7);
            pcVar17 = *(code **)(lVar8 + 8);
            uVar15 = *(undefined8 *)(piVar27 + 0x12);
            uVar14 = 2;
            goto LAB_001599bc;
          }
          if (iVar4 == 1) {
            uVar7 = FUN_00159aa8(piVar27,*param_2,0);
            uVar25 = uVar7 & 0xffffffff;
          }
          goto LAB_00159a20;
        }
        if (*param_2 == 0) {
LAB_00159a90:
          uVar7 = 99;
          goto LAB_00159a94;
        }
        *(undefined4 *)(*param_2 + 0x10) = 0;
LAB_001599dc:
        piVar27[2] = 0;
        piVar27[0x14] = 0;
        goto LAB_00159a30;
      }
      if (*piVar27 != 1) goto LAB_00159a20;
      if (piVar27[1] != 2) {
        iVar4 = piVar27[0xb];
        if ((iVar4 == 0) || (lVar8 = *(long *)(piVar27 + 0xc), lVar8 == 0)) goto LAB_00159a90;
        if (0 < iVar4) {
          uVar7 = *param_2;
          local_1ec = 0;
          lVar19 = 0;
          do {
            puVar20 = *(undefined8 **)(lVar8 + lVar19 * 8);
            plVar9 = (long *)(**(code **)(_DAT_0031bd78 + 0x78))();
            if (plVar9 == (long *)0x0) {
LAB_00158e14:
              local_1e8 = (long *)0x0;
LAB_00158e18:
              uVar25 = 3;
LAB_00158e1c:
              bVar18 = true;
            }
            else {
              iVar4 = access((char *)*plVar9,2);
              if (iVar4 != 0) goto LAB_00158e14;
              if (_DAT_0031b7f0 == 0) {
                local_1e8 = (long *)0x0;
              }
              else {
                local_1e8 = (long *)(**(code **)(_DAT_0031bd78 + 0x70))();
                if (local_1e8 == (long *)0x0) {
                  lVar8 = 0;
                }
                else {
                  if ((*local_1e8 != 0) && ((int)local_1e8[1] != 0)) {
                    (*(code *)local_1e8[2])(local_1e8,0x31bd80);
                    iVar4 = access((char *)*local_1e8,0);
                    if (iVar4 != 0) {
                      iVar4 = mkdir((char *)*local_1e8,0x1c0);
                      if (iVar4 != 0) goto LAB_00158f70;
                    }
                  }
                  lVar8 = *local_1e8;
                }
                local_1a4._0_4_ = 0;
                uStack_1a8 = 0;
                local_1ac = 0;
                uStack_1b0 = 0;
                uStack_1b4 = 0;
                uStack_1b8 = 0;
                local_1c0 = 0;
                iVar4 = snprintf((char *)&local_1c0,0x20,(char *)0x31bd86,lVar8);
                uStack_178 = 0x18753;
                uStack_16c = 0x31bd89;
                uStack_168 = 0;
                local_164 = &local_1c0;
                local_180 = uVar2;
                uStack_174 = 0;
// ... truncated (443 more lines)

// === FUN_00155860 @ 00155860 (3888 bytes) [cookie_sm4_init] ===

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined4 FUN_00155860(ulong param_1,long param_2)

{
  long lVar1;
  bool bVar2;
  bool bVar3;
  int iVar4;
  uint uVar5;
  ulong uVar6;
  undefined8 *puVar7;
  long *plVar8;
  undefined8 *puVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  void *__ptr;
  long *plVar12;
  size_t sVar13;
  char *pcVar14;
  long lVar15;
  code *pcVar16;
  long *plVar17;
  long *plVar18;
  long lVar19;
  undefined8 *puVar20;
  long *plVar21;
  undefined4 uVar22;
  long lVar23;
  long *plVar24;
  long lVar25;
  undefined8 *puVar26;
  char *__s;
  FILE *local_170;
  long local_160;
  undefined4 local_14c;
  undefined1 *local_148;
  undefined4 local_140;
  undefined4 uStack_13c;
  undefined4 uStack_138;
  undefined4 local_134;
  undefined4 uStack_130;
  undefined8 *local_12c;
  undefined8 local_124;
  undefined8 local_11c;
  undefined8 local_114;
  undefined8 local_108;
  undefined8 uStack_100;
  undefined8 local_f8;
  undefined8 uStack_f0;
  char acStack_e8 [136];
  
  lVar1 = tpidr_el0;
  lVar15 = *(long *)(lVar1 + 0x28);
  uVar6 = param_1;
  if ((DAT_0031bc4a & 1) == 0) {
    uVar6 = J2qaFN4xHz(0x31bc20,9,&DAT_0029c54f,&DAT_0029bf2e,4,0xdb);
    DAT_0031bc4a = 1;
  }
  if ((DAT_0031bc49 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bc15,0xb,&DAT_0029c544,&DAT_0029b703,4,9);
    DAT_0031bc49 = 1;
  }
  if ((DAT_0031bc48 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bc12,3,&DAT_0029c541,&DAT_0029c53e,3,0x29);
    DAT_0031bc48 = 1;
  }
  if ((DAT_0031bc47 & 1) == 0) {
    uVar6 = pDhsAJkF7h(0x31bc10,2,&DAT_0029c53c,&DAT_0029c53a,2,0x53);
    DAT_0031bc47 = 1;
  }
  if ((DAT_0031bc46 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bc05,0xb,&DAT_0029c52f,&DAT_0029b6ff,4,0x41);
    DAT_0031bc46 = 1;
  }
  if ((DAT_0031bc45 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bc03,2,&DAT_0029c52d,&DAT_0029c52b,2,0x45);
    DAT_0031bc45 = 1;
  }
  if ((DAT_0031bc44 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bbf4,0xf,&DAT_0029c51c,&DAT_0029c519,3,0x87);
    DAT_0031bc44 = 1;
  }
  if ((DAT_0031bc43 & 1) == 0) {
    uVar6 = pDhsAJkF7h(0x31bbe0,0x14,&DAT_0029c505,&DAT_0029c502,3,0x81);
    DAT_0031bc43 = 1;
  }
  if ((DAT_0031bc42 & 1) == 0) {
    uVar6 = pDhsAJkF7h(0x31bbd3,0xd,&DAT_0029c4f5,&DAT_0029c4f2,3,0xdd);
    DAT_0031bc42 = 1;
  }
  if ((DAT_0031bc41 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bbc6,0xd,&DAT_0029c4e5,&DAT_0029b6fb,4,0x97);
    DAT_0031bc41 = 1;
  }
  if ((DAT_0031bc40 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bbb4,0x12,&DAT_0029c4d3,&DAT_0029b6f7,4,0xfd);
    DAT_0031bc40 = 1;
  }
  if ((DAT_0031bc3f & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bba4,0x10,&DAT_0029c570,&DAT_0029c4d0,3,0xb9);
    DAT_0031bc3f = 1;
  }
  if ((DAT_0031bc3e & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bb97,0xd,&DAT_0029c4c3,&DAT_0029b50c,4,0x47);
    DAT_0031bc3e = 1;
  }
  if ((DAT_0031bc3d & 1) == 0) {
    uVar6 = pDhsAJkF7h(0x31bb8f,8,&DAT_0029c438,&DAT_0029c4c1,2,0xbb);
    DAT_0031bc3d = 1;
  }
  if ((DAT_0031bc3c & 1) == 0) {
    uVar6 = J2qaFN4xHz(0x31bb89,6,&DAT_0029c4bb,&DAT_0029b508,4,0x43);
    DAT_0031bc3c = 1;
  }
  if ((DAT_0031bc3b & 1) == 0) {
    uVar6 = J2qaFN4xHz(0x31bb87,2,&DAT_0029c4b9,&DAT_0029c4b7,2,0xfb);
    DAT_0031bc3b = 1;
  }
  if ((DAT_0031bc3a & 1) == 0) {
    uVar6 = J2qaFN4xHz(0x31bb7e,9,&DAT_0029c4ae,&DAT_0029c4ab,3,0xad);
    DAT_0031bc3a = 1;
  }
  if ((DAT_0031bc39 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bb7c,2,&DAT_0029c4a9,&DAT_0029b48e,4,7);
    DAT_0031bc39 = 1;
  }
  if ((DAT_0031bc38 & 1) == 0) {
    uVar6 = J2qaFN4xHz(0x31bb75,7,&DAT_0029c4a2,&DAT_0029b48a,4,0x41);
    DAT_0031bc38 = 1;
  }
  if ((DAT_0031bc37 & 1) == 0) {
    uVar6 = HlupqL3ZAR(0x31bb71,4,&DAT_0029c56c,&DAT_0029b486,4,0xa9);
    DAT_0031bc37 = 1;
  }
  if ((DAT_0031bc36 & 1) == 0) {
    uVar6 = pDhsAJkF7h(0x31bb63,0xe,&DAT_0029c494,&DAT_0029c491,3,0x9b);
    DAT_0031bc36 = 1;
  }
  uVar22 = 0;
  if ((param_1 == 0) || (param_2 == 0)) goto LAB_00155eb8;
  bVar2 = _DAT_0031b7f0 == 0;
  bVar3 = _DAT_0031b7f4 == 0;
  puVar7 = (undefined8 *)(**(code **)(param_1 + 0x78))(param_1);
  if (bVar2 || bVar3) {
    plVar8 = (long *)0x0;
  }
  else {
    plVar8 = (long *)(**(code **)(param_1 + 0x70))(param_1);
  }
  if (((puVar7 == (undefined8 *)0x0) || ((char *)*puVar7 == (char *)0x0)) ||
     (iVar4 = access((char *)*puVar7,0), iVar4 != 0)) {
    __s = (char *)0x0;
    plVar18 = (long *)0x0;
    lVar19 = 0;
    plVar24 = (long *)0x0;
    plVar21 = (long *)0x0;
    plVar17 = (long *)0x0;
    puVar26 = (undefined8 *)0x0;
    goto LAB_00155e40;
  }
  __s = (char *)FUN_00250f9c(*(undefined4 *)(param_2 + 0x10));
  plVar18 = (long *)(**(code **)(param_2 + 0x28))(param_2);
  uVar22 = 0;
  plVar17 = (long *)0x0;
  if ((__s == (char *)0x0) || (plVar18 == (long *)0x0)) {
    local_160 = 0;
    lVar19 = 0;
    puVar26 = (undefined8 *)0x0;
    local_170 = (FILE *)0x0;
    puVar9 = (undefined8 *)0x0;
    plVar21 = plVar17;
    plVar24 = plVar17;
    plVar12 = plVar17;
    goto LAB_00155e44;
  }
  if ((*plVar18 == 0) || ((int)plVar18[1] == 0)) {
    lVar19 = 0;
    plVar24 = (long *)0x0;
    plVar21 = (long *)0x0;
    plVar17 = (long *)0x0;
    puVar26 = (undefined8 *)0x0;
LAB_00155e40:
    local_160 = 0;
    local_170 = (FILE *)0x0;
    puVar9 = (undefined8 *)0x0;
    uVar22 = 0;
    plVar12 = (long *)0x0;
  }
  else {
    plVar21 = (long *)FUN_00251b6c(*puVar7);
    if (((plVar21 != (long *)0x0) && (*plVar21 != 0)) && ((int)plVar21[1] != 0)) {
      lVar19 = (*(code *)plVar21[2])(plVar21,0x31bb75);
      lVar19 = (**(code **)(lVar19 + 0x10))(plVar21,__s);
      lVar19 = (**(code **)(lVar19 + 0x10))(plVar21,0x31bb7c);
      lVar19 = (**(code **)(lVar19 + 0x10))(plVar21,*plVar18);
      (**(code **)(lVar19 + 0x10))(plVar21,0x31bb7e);
    }
    lVar19 = (*(code *)puVar7[2])(puVar7,0x31bb7c);
// ... truncated (244 more lines)

// === FUN_00150718 @ 00150718 (2732 bytes) [cookie_sm4_init] ===

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long FUN_00150718(long param_1)

{
  long lVar1;
  bool bVar2;
  int iVar3;
  int iVar4;
  undefined8 *puVar5;
  undefined8 *puVar6;
  long *plVar7;
  undefined8 *puVar8;
  char *pcVar9;
  undefined8 uVar10;
  long lVar11;
  DIR *__dirp;
  dirent *pdVar12;
  ulong uVar13;
  long lVar14;
  char *pcVar15;
  long *local_2088;
  uint local_206c;
  char local_2068 [4096];
  undefined8 local_1068;
  undefined4 local_1060;
  undefined8 local_105c;
  undefined8 local_1054;
  char *local_104c;
  undefined8 local_1044;
  undefined8 local_103c;
  undefined8 local_1034;
  long local_68;
  
  lVar1 = tpidr_el0;
  local_68 = *(long *)(lVar1 + 0x28);
  if ((DAT_0031bb13 & 1) == 0) {
    J2qaFN4xHz(0x31b872,6,&DAT_0029c151,&DAT_0029c14e,3,0xb);
    DAT_0031bb13 = 1;
  }
  if ((DAT_0031bb12 & 1) == 0) {
    HlupqL3ZAR(0x31b86e,4,&DAT_0029c3cc,&DAT_0029c14c,2,0xd9);
    DAT_0031bb12 = 1;
  }
  if ((DAT_0031bb11 & 1) == 0) {
    HlupqL3ZAR(0x31b86a,4,&DAT_0029c3c8,&DAT_0029c149,3,0x45);
    DAT_0031bb11 = 1;
  }
  if ((DAT_0031bb10 & 1) == 0) {
    J2qaFN4xHz(0x31b864,6,&DAT_0029c143,&DAT_0029bf2e,4,0xdb);
    DAT_0031bb10 = 1;
  }
  if ((DAT_0031bb0f & 1) == 0) {
    HlupqL3ZAR(0x31b859,0xb,&DAT_0029c138,&DAT_0029b703,4,9);
    DAT_0031bb0f = 1;
  }
  if ((DAT_0031bb0e & 1) == 0) {
    HlupqL3ZAR(0x31b850,9,&DAT_0029c12f,&DAT_0029c12c,3,0x29);
    DAT_0031bb0e = 1;
  }
  if ((DAT_0031bb0d & 1) == 0) {
    pDhsAJkF7h(0x31b84a,6,&DAT_0029c126,&DAT_0029c124,2,0x53);
    DAT_0031bb0d = 1;
  }
  if ((DAT_0031bb0c & 1) == 0) {
    HlupqL3ZAR(0x31b846,4,&DAT_0029c3c4,&DAT_0029b6ff,4,0x41);
    DAT_0031bb0c = 1;
  }
  if ((DAT_0031bb0b & 1) == 0) {
    HlupqL3ZAR(0x31b833,0x13,&DAT_0029c111,&DAT_0029c10f,2,0x45);
    DAT_0031bb0b = 1;
  }
  if ((DAT_0031bb0a & 1) == 0) {
    HlupqL3ZAR(0x31b823,0x10,&DAT_0029c040,&DAT_0029c10c,3,0x87);
    DAT_0031bb0a = 1;
  }
  if ((DAT_0031bb09 & 1) == 0) {
    pDhsAJkF7h(0x31b811,0x12,&DAT_0029c0fa,&DAT_0029c0f7,3,0x81);
    DAT_0031bb09 = 1;
  }
  if ((DAT_0031bb08 & 1) == 0) {
    pDhsAJkF7h(0x31b807,10,&DAT_0029c0ed,&DAT_0029c0ea,3,0xdd);
    DAT_0031bb08 = 1;
  }
  if ((DAT_0031bb07 & 1) == 0) {
    HlupqL3ZAR(0x31b801,6,&DAT_0029c0e4,&DAT_0029b6fb,4,0x97);
    DAT_0031bb07 = 1;
  }
  if ((DAT_0031bb06 & 1) == 0) {
    HlupqL3ZAR(0x31b7ff,2,&DAT_0029c0e2,&DAT_0029b6f7,4,0xfd);
    DAT_0031bb06 = 1;
  }
  if ((DAT_0031bb05 & 1) == 0) {
    HlupqL3ZAR(0x31b7f8,7,&DAT_0029c0db,&DAT_0029c0d8,3,0xb9);
    DAT_0031bb05 = 1;
  }
  if ((DAT_0031bafb & 1) == 0) {
    HlupqL3ZAR(&DAT_0031b7ac,10,&DAT_0029c092,&DAT_0029c090,2,0xfb);
    DAT_0031bafb = 1;
  }
  puVar5 = (undefined8 *)(**(code **)(param_1 + 0x80))(param_1);
  if ((puVar5 == (undefined8 *)0x0) || (*(int *)(puVar5 + 1) == 0)) {
    puVar6 = (undefined8 *)0x0;
    lVar14 = 0;
  }
  else {
    if ((*(long *)(param_1 + 8) == 0) || (*(int *)(*(long *)(param_1 + 8) + 0x10) < 1)) {
      local_2088 = (long *)0x0;
      puVar6 = (undefined8 *)0x0;
    }
    else {
      puVar6 = (undefined8 *)(**(code **)(param_1 + 0x78))(param_1);
      if (puVar6 != (undefined8 *)0x0) {
        (*(code *)puVar6[2])(puVar6,0x31b7f8);
        (*(code *)puVar6[5])(puVar6,*(undefined4 *)(*(long *)(param_1 + 8) + 0x10));
        (*(code *)puVar6[2])(puVar6,0x31b7ff);
        (*(code *)puVar6[2])(puVar6,&DAT_0031b7ac);
      }
      local_2088 = (long *)(**(code **)(param_1 + 0x70))(param_1);
      if (local_2088 == (long *)0x0) {
        local_2088 = (long *)0x0;
      }
      else {
        (*(code *)local_2088[2])(local_2088,0x31b801);
        (*(code *)local_2088[2])(local_2088,0x31b7f8);
        (*(code *)local_2088[5])(local_2088,*(undefined4 *)(*(long *)(param_1 + 8) + 0x10));
        (*(code *)local_2088[2])(local_2088,0x31b7ff);
        (*(code *)local_2088[2])(local_2088,&DAT_0031b7ac);
      }
    }
    if (_DAT_0031b7f0 != 0) {
      _DAT_0035f410 = FUN_00251360(3,&LAB_00154230);
      uVar13 = 0;
      lVar14 = 0;
      iVar4 = 0;
      local_206c = 0;
      do {
        memset(&local_1068,0,0x1000);
        pcVar15 = (char *)(&DAT_0030a2e0)[uVar13];
        snprintf((char *)&local_1068,0x1000,(char *)0x31b833,*puVar5,pcVar15);
        iVar3 = access((char *)&local_1068,0);
        if (iVar3 == 0) {
          iVar4 = iVar4 + 1;
          plVar7 = (long *)FUN_00248254(&local_1068,&local_206c);
          if ((((plVar7 == (long *)0x0) || (local_206c != 0)) || (*plVar7 == 0)) ||
             (((int)plVar7[1] == 0 || (puVar8 = malloc(0x10), puVar8 == (undefined8 *)0x0)))) {
            FUN_00251e20(plVar7);
            if (lVar14 == 0) goto LAB_00150ecc;
            goto LAB_00150ec0;
          }
          *puVar8 = 0;
          puVar8[1] = 0;
          pcVar9 = strdup(pcVar15);
          *puVar8 = pcVar9;
          pcVar9 = (char *)0x0;
          if ((char *)*plVar7 != (char *)0x0) {
            pcVar9 = strdup((char *)*plVar7);
          }
          lVar11 = _DAT_0035f410;
          puVar8[1] = pcVar9;
          (**(code **)(lVar11 + 0x18))(lVar11,puVar8);
          puVar8 = malloc(0x28);
          if (puVar8 != (undefined8 *)0x0) {
            puVar8[4] = 0;
            puVar8[1] = 0;
            *puVar8 = 0;
            puVar8[3] = 0;
            puVar8[2] = 0;
          }
          puVar8[1] = plVar7;
          uVar10 = FUN_00251b6c(pcVar15);
          *puVar8 = uVar10;
          lVar11 = FUN_00251b6c(*puVar5);
          puVar8[3] = lVar11;
          (**(code **)(lVar11 + 0x10))(lVar11,0x31b7ff);
          (**(code **)(puVar8[3] + 0x10))(puVar8[3],pcVar15);
          (**(code **)(puVar8[3] + 0x10))(puVar8[3],0x31b846);
          if (puVar6 != (undefined8 *)0x0) {
            memset(local_2068,0,0x1000);
            snprintf(local_2068,0x1000,(char *)0x31b84a,pcVar15);
            if (((local_2088 == (long *)0x0) || (*local_2088 == 0)) || ((int)local_2088[1] == 0)) {
              plVar7 = (long *)0x0;
LAB_00150e2c:
              FUN_00251e20(plVar7);
              plVar7 = (long *)FUN_0015426c(param_1,*puVar6,local_2068,1);
              if (plVar7 == (long *)0x0) goto LAB_00150e78;
            }
            else {
              plVar7 = (long *)FUN_0015426c(param_1,*local_2088,local_2068,1);
              if (((plVar7 == (long *)0x0) || (*plVar7 == 0)) || ((int)plVar7[1] == 0))
              goto LAB_00150e2c;
            }
            puVar8[2] = plVar7;
            lVar11 = FUN_00251b6c(*puVar6);
            puVar8[4] = lVar11;
            (**(code **)(lVar11 + 0x10))(lVar11,0x31b7ff);
            (**(code **)(puVar8[4] + 0x10))(puVar8[4],local_2068);
          }
LAB_00150e78:
// ... truncated (138 more lines)

// === FUN_00152ea0 @ 00152ea0 (2684 bytes) [cookie_sm4_init] ===

int FUN_00152ea0(long param_1,undefined8 *param_2)

{
  long lVar1;
  int iVar2;
  int iVar3;
  undefined4 uVar4;
  undefined8 uVar5;
  long lVar6;
  long lVar7;
  long *plVar8;
  undefined8 *puVar9;
  long *plVar10;
  long *plVar11;
  long lVar12;
  long *plVar13;
  long *plVar14;
  undefined8 *puVar15;
  undefined8 *puVar16;
  ulong uVar17;
  undefined8 uVar18;
  undefined8 uVar19;
  void *__s;
  long lVar20;
  long *plVar21;
  long *plVar22;
  long *plVar23;
  long *plVar24;
  undefined8 *__ptr;
  char *local_80;
  char *local_78;
  char *local_70;
  long local_68;
  
  lVar1 = tpidr_el0;
  local_68 = *(long *)(lVar1 + 0x28);
  if ((DAT_0031bb28 & 1) == 0) {
    HlupqL3ZAR(0x31b958,0xb,&DAT_0029c224,&DAT_0029c222,2,0x2d);
    DAT_0031bb28 = 1;
  }
  if ((DAT_0031bb27 & 1) == 0) {
    J2qaFN4xHz(0x31b94f,9,&DAT_0029c219,&DAT_0029c216,3,0x29);
    DAT_0031bb27 = 1;
  }
  if ((DAT_0031bb26 & 1) == 0) {
    pDhsAJkF7h(0x31b947,8,&DAT_0029c000,&DAT_0029c3d0,4,0xf5);
    DAT_0031bb26 = 1;
  }
  if ((DAT_0031bb25 & 1) == 0) {
    J2qaFN4xHz(0x31b93b,0xc,&DAT_0029c20a,&DAT_0029c208,2,0xb9);
    DAT_0031bb25 = 1;
  }
  if ((DAT_0031bb24 & 1) == 0) {
    pDhsAJkF7h(0x31b932,9,&DAT_0029c1ff,&DAT_0029c1fc,3,0xe7);
    DAT_0031bb24 = 1;
  }
  if ((DAT_0031bb23 & 1) == 0) {
    J2qaFN4xHz(0x31b922,0x10,&DAT_0029c070,&DAT_0029c1f9,3,0x6f);
    DAT_0031bb23 = 1;
  }
  if ((DAT_0031bb22 & 1) == 0) {
    HlupqL3ZAR(0x31b912,0x10,&DAT_0029c060,&DAT_0029c1f7,2,0x39);
    DAT_0031bb22 = 1;
  }
  if ((DAT_0031bb21 & 1) == 0) {
    pDhsAJkF7h(0x31b907,0xb,&DAT_0029c1ec,&DAT_0029c1ea,2,0xdf);
    DAT_0031bb21 = 1;
  }
  if ((DAT_0031bb20 & 1) == 0) {
    pDhsAJkF7h(0x31b8fe,9,&DAT_0029c1e1,&DAT_0029c1de,3,3);
    DAT_0031bb20 = 1;
  }
  if ((DAT_0031bb1f & 1) == 0) {
    HlupqL3ZAR(0x31b8f0,0xe,&DAT_0029c1d0,&DAT_0029c1ce,2,0x9f);
    DAT_0031bb1f = 1;
  }
  if ((DAT_0031bb1e & 1) == 0) {
    J2qaFN4xHz(0x31b8e0,0x10,&DAT_0029c050,&DAT_0029c1cc,2,0xe5);
    DAT_0031bb1e = 1;
  }
  if ((DAT_0031bb1d & 1) == 0) {
    HlupqL3ZAR(0x31b8d6,10,&DAT_0029c1c2,&DAT_0029bafb,4,0x49);
    DAT_0031bb1d = 1;
  }
  if ((DAT_0031bb19 & 1) == 0) {
    HlupqL3ZAR(0x31b8a5,0xd,&DAT_0029c18e,&DAT_0029baef,4,0xed);
    DAT_0031bb19 = 1;
  }
  if ((DAT_0031bb18 & 1) == 0) {
    pDhsAJkF7h(0x31b892,0x13,&DAT_0029c17b,&DAT_0029c179,2,0x7d);
    DAT_0031bb18 = 1;
  }
  if ((DAT_0031bb17 & 1) == 0) {
    HlupqL3ZAR(0x31b888,10,&DAT_0029c16f,&DAT_0029c16d,2,0x9b);
    DAT_0031bb17 = 1;
  }
  if ((DAT_0031bb0c & 1) == 0) {
    HlupqL3ZAR(0x31b846,4,&DAT_0029c3c4,&DAT_0029b6ff,4,0x41);
    DAT_0031bb0c = 1;
  }
  if (*(long *)(param_1 + 0x10) == 0) {
    uVar5 = FUN_002517c8(FUN_001549f8);
    *(undefined8 *)(param_1 + 0x10) = uVar5;
  }
  lVar6 = FUN_00242f84(*param_2);
  if (lVar6 == 0) {
    plVar10 = (long *)0x0;
    puVar9 = (undefined8 *)0x0;
    uVar5 = 0;
    puVar15 = (undefined8 *)0x0;
    plVar14 = (long *)0x0;
    plVar13 = (long *)0x0;
    plVar8 = (long *)0x0;
    iVar2 = 0;
    plVar11 = (long *)0x0;
    goto LAB_00153580;
  }
  lVar7 = FUN_002435f4(lVar6,0x31b8d6);
  if (lVar7 == 0) {
    puVar9 = (undefined8 *)0x0;
    uVar5 = 0;
    puVar15 = (undefined8 *)0x0;
    plVar14 = (long *)0x0;
    plVar13 = (long *)0x0;
    plVar8 = (long *)0x0;
    iVar2 = 1;
    plVar11 = (long *)0x0;
    plVar10 = (long *)0x0;
    goto LAB_00153580;
  }
  plVar8 = (long *)FUN_002448a4(lVar7,0x31b888,0);
  puVar9 = (undefined8 *)FUN_002448a4(lVar7,0x31b907,0);
  plVar10 = (long *)FUN_002448a4(lVar7,0x31b912,0);
  plVar11 = (long *)FUN_002448a4(lVar7,0x31b922,0);
  if (((((puVar9 != (undefined8 *)0x0) && ((char *)*puVar9 != (char *)0x0)) &&
       (plVar10 != (long *)0x0)) &&
      (((*(int *)(puVar9 + 1) != 0 && (*plVar10 != 0)) &&
       ((plVar11 != (long *)0x0 && (((int)plVar10[1] != 0 && (*plVar11 != 0)))))))) &&
     ((int)plVar11[1] != 0)) {
    local_80 = strdup((char *)*puVar9);
    local_78 = (char *)0x0;
    if ((char *)*plVar10 != (char *)0x0) {
      local_78 = strdup((char *)*plVar10);
    }
    local_70 = (char *)0x0;
    if ((char *)*plVar11 != (char *)0x0) {
      local_70 = strdup((char *)*plVar11);
    }
    lVar12 = FUN_00242634();
    (**(code **)(lVar12 + 0x10))(1,0x21,0,0,&local_80,0);
  }
  plVar13 = (long *)FUN_002448a4(lVar7,0x31b8a5,0);
  plVar14 = (long *)FUN_002448a4(lVar7,0x31b892,0);
  if (plVar8 == (long *)0x0) {
LAB_00153550:
    uVar5 = 0;
    puVar15 = (undefined8 *)0x0;
LAB_00153558:
    iVar2 = 0;
  }
  else {
    if (*plVar8 != 0) {
      iVar2 = 0;
      uVar5 = 0;
      if ((plVar13 == (long *)0x0) || ((int)plVar8[1] == 0)) {
        puVar15 = (undefined8 *)0x0;
        goto LAB_00153580;
      }
      if ((*plVar13 != 0) && ((int)plVar13[1] != 0)) {
        iVar2 = FUN_00251050(plVar8,0);
        if (iVar2 < 1) goto LAB_00153550;
        puVar15 = (undefined8 *)FUN_002448a4(lVar7,0x31b8e0,0);
        if ((((puVar15 == (undefined8 *)0x0) || ((char *)*puVar15 == (char *)0x0)) ||
            (*(int *)(puVar15 + 1) == 0)) || (*(char *)*puVar15 != '1')) {
          uVar5 = FUN_002448a4(lVar7,0x31b8f0,0);
          uVar17 = FUN_00251050(uVar5,0);
          iVar3 = (int)uVar17;
          if (((0 < iVar3) && (lVar12 = FUN_002435f4(lVar7,0x31b8fe), lVar12 != 0)) &&
             (puVar16 = malloc(0x58), puVar16 != (undefined8 *)0x0)) {
            memset((void *)((long)puVar16 + 0xc),0,0x4c);
            uVar18 = FUN_00251b6c(*plVar8);
            puVar16[2] = uVar18;
            uVar19 = FUN_00251b6c(*plVar13);
            uVar18 = DAT_0029a210;
            puVar16[3] = uVar19;
            *(undefined4 *)(puVar16 + 1) = 1;
            *puVar16 = uVar18;
            uVar4 = FUN_00251050(plVar8,0);
            uVar17 = -(uVar17 >> 0x1f & 1) & 0xfffffff800000000 | (uVar17 & 0xffffffff) << 3;
            *(undefined4 *)(puVar16 + 5) = uVar4;
            *(int *)((long)puVar16 + 0x2c) = iVar3;
            __s = malloc(uVar17);
            if (__s != (void *)0x0) {
              memset(__s,0,uVar17);
            }
            uVar17 = 0;
            iVar2 = 0;
            puVar16[6] = __s;
            do {
// ... truncated (106 more lines)

// === FUN_00160124 @ 00160124 (2548 bytes) [cookie_sm4_init] ===

void FUN_00160124(uint param_1,undefined4 *param_2)

{
  long lVar1;
  undefined8 uVar2;
  undefined4 local_3c;
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  if ((DAT_0031c32f & 1) == 0) {
    J2qaFN4xHz(0x31c2e6,0x20,&DAT_0029cbfb,&DAT_0029cbf0,3,0x71);
    DAT_0031c32f = 1;
  }
  if ((DAT_0031c32e & 1) == 0) {
    J2qaFN4xHz(0x31c2d6,0x10,&DAT_0029c910,&DAT_0029cbed,3,0x6f);
    DAT_0031c32e = 1;
  }
  if ((DAT_0031c32d & 1) == 0) {
    HlupqL3ZAR(0x31c2c8,0xe,&DAT_0029cbdf,&DAT_0029cbdd,2,0x39);
    DAT_0031c32d = 1;
  }
  if ((DAT_0031c32c & 1) == 0) {
    pDhsAJkF7h(0x31c2b9,0xf,&DAT_0029cbce,&DAT_0029cbcc,2,0xdf);
    DAT_0031c32c = 1;
  }
  if ((DAT_0031c32b & 1) == 0) {
    pDhsAJkF7h(0x31c2a4,0x15,&DAT_0029cbb7,&DAT_0029cbb4,3,3);
    DAT_0031c32b = 1;
  }
  if ((DAT_0031c32a & 1) == 0) {
    HlupqL3ZAR(0x31c28f,0x15,&DAT_0029cb9f,&DAT_0029cb9d,2,0x9f);
    DAT_0031c32a = 1;
  }
  if ((DAT_0031c329 & 1) == 0) {
    J2qaFN4xHz(0x31c281,0xe,&DAT_0029cb8f,&DAT_0029cb8d,2,0xe5);
    DAT_0031c329 = 1;
  }
  if ((DAT_0031c328 & 1) == 0) {
    HlupqL3ZAR(0x31c272,0xf,&DAT_0029cb7e,&DAT_0029bafb,4,0x49);
    DAT_0031c328 = 1;
  }
  if ((DAT_0031c327 & 1) == 0) {
    J2qaFN4xHz(0x31c25b,0x17,&DAT_0029cb67,&DAT_0029baf7,4,0x81);
    DAT_0031c327 = 1;
  }
  if ((DAT_0031c326 & 1) == 0) {
    J2qaFN4xHz(0x31c246,0x15,&DAT_0029cb52,&DAT_0029baf3,4,0x9f);
    DAT_0031c326 = 1;
  }
  if ((DAT_0031c325 & 1) == 0) {
    pDhsAJkF7h(0x31c234,0x12,&DAT_0029cb40,&DAT_0029cb3d,3,0x1f);
    DAT_0031c325 = 1;
  }
  if ((DAT_0031c324 & 1) == 0) {
    HlupqL3ZAR(0x31c217,0x1d,&DAT_0029cb20,&DAT_0029baef,4,0xed);
    DAT_0031c324 = 1;
  }
  if ((DAT_0031c323 & 1) == 0) {
    pDhsAJkF7h(0x31c209,0xe,&DAT_0029cb12,&DAT_0029cb10,2,0x7d);
    DAT_0031c323 = 1;
  }
  if ((DAT_0031c322 & 1) == 0) {
    HlupqL3ZAR(0x31c1fb,0xe,&DAT_0029cb02,&DAT_0029cb00,2,0x9b);
    DAT_0031c322 = 1;
  }
  if ((DAT_0031c321 & 1) == 0) {
    pDhsAJkF7h(0x31c1ed,0xe,&DAT_0029caf2,&DAT_0029caef,3,0x73);
    DAT_0031c321 = 1;
  }
  if ((DAT_0031c320 & 1) == 0) {
    J2qaFN4xHz(0x31c1e1,0xc,&DAT_0029cae3,&DAT_0029baeb,4,0xa1);
    DAT_0031c320 = 1;
  }
  if ((DAT_0031c31f & 1) == 0) {
    HlupqL3ZAR(0x31c1cd,0x14,&DAT_0029cacf,&DAT_0029cacc,3,0xf3);
    DAT_0031c31f = 1;
  }
  if ((DAT_0031c31e & 1) == 0) {
    J2qaFN4xHz(0x31c1ba,0x13,&DAT_0029cab9,&DAT_0029cab6,3,0xb);
    DAT_0031c31e = 1;
  }
  if ((DAT_0031c31d & 1) == 0) {
    HlupqL3ZAR(0x31c1b0,10,&DAT_0029caac,&DAT_0029caaa,2,0xd9);
    DAT_0031c31d = 1;
  }
  if ((DAT_0031c31c & 1) == 0) {
    HlupqL3ZAR(0x31c19b,0x15,&DAT_0029ca95,&DAT_0029ca92,3,0x45);
    DAT_0031c31c = 1;
  }
  if ((DAT_0031c31b & 1) == 0) {
    J2qaFN4xHz(0x31c186,0x15,&DAT_0029ca7d,&DAT_0029bf2e,4,0xdb);
    DAT_0031c31b = 1;
  }
  if ((DAT_0031c31a & 1) == 0) {
    HlupqL3ZAR(0x31c178,0xe,&DAT_0029ca6f,&DAT_0029b703,4,9);
    DAT_0031c31a = 1;
  }
  if ((DAT_0031c319 & 1) == 0) {
    HlupqL3ZAR(0x31c163,0x15,&DAT_0029ca5a,&DAT_0029ca57,3,0x29);
    DAT_0031c319 = 1;
  }
  if ((DAT_0031c318 & 1) == 0) {
    pDhsAJkF7h(0x31c14a,0x19,&DAT_0029ca3e,&DAT_0029ca3c,2,0x53);
    DAT_0031c318 = 1;
  }
  if ((DAT_0031c317 & 1) == 0) {
    HlupqL3ZAR(0x31c139,0x11,&DAT_0029ca2b,&DAT_0029b6ff,4,0x41);
    DAT_0031c317 = 1;
  }
  if ((DAT_0031c316 & 1) == 0) {
    HlupqL3ZAR(0x31c128,0x11,&DAT_0029ca1a,&DAT_0029ca18,2,0x45);
    DAT_0031c316 = 1;
  }
  if ((DAT_0031c315 & 1) == 0) {
    HlupqL3ZAR(0x31c110,0x18,&DAT_0029ca00,&DAT_0029c9fd,3,0x87);
    DAT_0031c315 = 1;
  }
  if ((DAT_0031c314 & 1) == 0) {
    pDhsAJkF7h(0x31c0f7,0x19,&DAT_0029c9e4,&DAT_0029c9e1,3,0x81);
    DAT_0031c314 = 1;
  }
  if ((DAT_0031c313 & 1) == 0) {
    pDhsAJkF7h(0x31c0dc,0x1b,&DAT_0029c9c6,&DAT_0029c9c3,3,0xdd);
    DAT_0031c313 = 1;
  }
  if ((DAT_0031c312 & 1) == 0) {
    HlupqL3ZAR(0x31c0c8,0x14,&DAT_0029c9af,&DAT_0029b6fb,4,0x97);
    DAT_0031c312 = 1;
  }
  if ((DAT_0031c311 & 1) == 0) {
    HlupqL3ZAR(0x31c0b6,0x12,&DAT_0029c99d,&DAT_0029b6f7,4,0xfd);
    DAT_0031c311 = 1;
  }
  if ((DAT_0031c310 & 1) == 0) {
    HlupqL3ZAR(0x31c0a6,0x10,&DAT_0029c900,&DAT_0029c99a,3,0xb9);
    DAT_0031c310 = 1;
  }
  if ((DAT_0031c30f & 1) == 0) {
    HlupqL3ZAR(0x31c095,0x11,&DAT_0029c989,&DAT_0029b50c,4,0x47);
    DAT_0031c30f = 1;
  }
  if ((DAT_0031c30e & 1) == 0) {
    pDhsAJkF7h(0x31c084,0x11,&DAT_0029c978,&DAT_0029c976,2,0xbb);
    DAT_0031c30e = 1;
  }
  if ((DAT_0031c30d & 1) == 0) {
    J2qaFN4xHz(0x31c080,4,&DAT_0029cbf7,&DAT_0029b508,4,0x43);
    DAT_0031c30d = 1;
  }
  if ((DAT_0031c30c & 1) == 0) {
    J2qaFN4xHz(0x31c07c,4,&DAT_0029cbf3,&DAT_0029c974,2,0xfb);
    DAT_0031c30c = 1;
  }
  if ((DAT_0031c30b & 1) == 0) {
    J2qaFN4xHz(0x31c071,0xb,&DAT_0029c969,&DAT_0029c966,3,0xad);
    DAT_0031c30b = 1;
  }
  if ((DAT_0031c30a & 1) == 0) {
    HlupqL3ZAR(0x31c068,9,&DAT_0029c95d,&DAT_0029b48e,4,7);
    DAT_0031c30a = 1;
  }
  if ((DAT_0031c309 & 1) == 0) {
    J2qaFN4xHz(0x31c065,3,&DAT_0029c95a,&DAT_0029b48a,4,0x41);
    DAT_0031c309 = 1;
  }
  if ((DAT_0031c308 & 1) == 0) {
    HlupqL3ZAR(0x31c063,2,&DAT_0029c958,&DAT_0029b486,4,0xa9);
    DAT_0031c308 = 1;
  }
  local_3c = 0;
  if (param_1 < 0xa3) {
    uVar2 = (*(code *)(&PTR_FUN_0030a690)[(long)(int)param_1 * 2])
                      ((&PTR_LAB_0030a698)[(long)(int)param_1 * 2],&local_3c);
  }
  else {
    uVar2 = 0;
    local_3c = 1;
  }
  if (param_2 != (undefined4 *)0x0) {
    *param_2 = local_3c;
  }
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail(uVar2);
}



// === FUN_00151a10 @ 00151a10 (1812 bytes) [cookie_sm4_init] ===

bool FUN_00151a10(undefined8 param_1,undefined8 param_2)

{
  long lVar1;
  int iVar2;
  long lVar3;
  long *plVar4;
  long lVar5;
  undefined8 uVar6;
  undefined8 *puVar7;
  long lVar8;
  undefined8 uVar9;
  void *__ptr;
  void *__ptr_00;
  void *__ptr_01;
  void *__ptr_02;
  void *__ptr_03;
  bool bVar10;
  long lVar11;
  undefined4 *puVar12;
  char acStack_e8 [128];
  long local_68;
  
  lVar1 = tpidr_el0;
  local_68 = *(long *)(lVar1 + 0x28);
  if ((DAT_0031bb52 & 1) == 0) {
    J2qaFN4xHz(0x31baec,0xf,&DAT_0029c3b5,&DAT_0029c3b2,3,0xdf);
    DAT_0031bb52 = 1;
  }
  if ((DAT_0031bb51 & 1) == 0) {
    HlupqL3ZAR(0x31bae4,8,&DAT_0029c028,&DAT_0029c3b0,2,0x91);
    DAT_0031bb51 = 1;
  }
  if ((DAT_0031bb50 & 1) == 0) {
    pDhsAJkF7h(0x31bad2,0x12,&DAT_0029c39e,&DAT_0029c414,4,0xbb);
    DAT_0031bb50 = 1;
  }
  if ((DAT_0031bb4f & 1) == 0) {
    HlupqL3ZAR(0x31bac6,0xc,&DAT_0029c392,&DAT_0029c390,2,0xf7);
    DAT_0031bb4f = 1;
  }
  if ((DAT_0031bb46 & 1) == 0) {
    J2qaFN4xHz(0x31ba84,2,&DAT_0029c418,&DAT_0029c357,3,0x9d);
    DAT_0031bb46 = 1;
  }
  if ((DAT_0031bb34 & 1) == 0) {
    J2qaFN4xHz(0x31b9f0,2,&DAT_0029c2c1,&DAT_0029c3ec,4,0x37);
    DAT_0031bb34 = 1;
  }
  if ((DAT_0031bb2e & 1) == 0) {
    J2qaFN4xHz(0x31b9b1,9,&DAT_0029c288,&DAT_0029c285,3,0xbb);
    DAT_0031bb2e = 1;
  }
  if ((DAT_0031bb2b & 1) == 0) {
    J2qaFN4xHz(0x31b97b,9,&DAT_0029c249,&DAT_0029c3d8,4,0xfd);
    DAT_0031bb2b = 1;
  }
  if ((DAT_0031bb29 & 1) == 0) {
    HlupqL3ZAR(0x31b963,10,&DAT_0029c231,&DAT_0029c22f,2,0xdf);
    DAT_0031bb29 = 1;
  }
  if ((DAT_0031bb01 & 1) == 0) {
    J2qaFN4xHz(0x31b7ca,3,&DAT_0029c0b0,&DAT_0029c0ae,2,0xfb);
    DAT_0031bb01 = 1;
  }
  if ((DAT_0031bb00 & 1) == 0) {
    J2qaFN4xHz(0x31b7c7,3,&DAT_0029c0ab,&DAT_0029c0a8,3,0xad);
    DAT_0031bb00 = 1;
  }
  if ((DAT_0031baff & 1) == 0) {
    HlupqL3ZAR(0x31b7c4,3,&DAT_0029c0a5,&DAT_0029b48e,4,7);
    DAT_0031baff = 1;
  }
  if ((DAT_0031bafe & 1) == 0) {
    J2qaFN4xHz(0x31b7c1,3,&DAT_0029c0a2,&DAT_0029b48a,4,0x41);
    DAT_0031bafe = 1;
  }
  if ((DAT_0031bafd & 1) == 0) {
    HlupqL3ZAR(0x31b7be,3,&DAT_0029c09f,&DAT_0029b486,4,0xa9);
    DAT_0031bafd = 1;
  }
  lVar3 = FUN_00242634();
  plVar4 = (long *)(**(code **)(lVar3 + 8))(1,6,0x18,0,0);
  if ((plVar4 == (long *)0x0) || ((int)plVar4[1] == 0)) {
    lVar5 = 0;
    lVar3 = 0;
  }
  else {
    lVar3 = FUN_002440f0();
    if (lVar3 == 0) {
      lVar5 = 0;
    }
    else {
      lVar5 = FUN_002440b4();
      if (lVar5 != 0) {
        iVar2 = (int)plVar4[1];
        if (0 < iVar2) {
          lVar11 = 0;
          do {
            puVar12 = *(undefined4 **)(*plVar4 + lVar11 * 8);
            if ((puVar12[4] != 0) && (*(long *)(puVar12 + 2) != 0)) {
              uVar6 = FUN_002440f0();
              if (**(char **)(puVar12 + 2) == '\0') {
                uVar9 = FUN_00244004(0x31bae4);
                FUN_0024373c(uVar6,0x31b9b1,uVar9);
              }
              else {
                puVar7 = (undefined8 *)FUN_00251b6c();
                lVar8 = (*(code *)puVar7[2])(puVar7,0x31b9f0);
                (**(code **)(lVar8 + 0x10))(puVar7,*(undefined8 *)(puVar12 + 2));
                uVar9 = FUN_00244004(*puVar7);
                FUN_0024373c(uVar6,0x31b9b1,uVar9);
                FUN_00251e20(puVar7);
              }
              puVar7 = (undefined8 *)FUN_00251c30();
              lVar8 = (*(code *)puVar7[5])(puVar7,*puVar12);
              lVar8 = (**(code **)(lVar8 + 0x10))(puVar7,0x31ba84);
              (**(code **)(lVar8 + 0x28))(puVar7,puVar12[1]);
              uVar9 = FUN_00244004(*puVar7);
              FUN_0024373c(uVar6,0x31bac6,uVar9);
              FUN_00251e20(puVar7);
              memset(acStack_e8,0,0x80);
              sprintf(acStack_e8,(char *)0x31baec,(ulong)(uint)puVar12[6],(ulong)(uint)puVar12[8],
                      (ulong)(uint)puVar12[0xc],(ulong)(uint)puVar12[10],(ulong)(uint)puVar12[0xe]);
              uVar9 = FUN_00244004(acStack_e8);
              FUN_0024373c(uVar6,0x31bad2,uVar9);
              __ptr = (void *)FUN_00250f9c(puVar12[5]);
              __ptr_00 = (void *)FUN_00250f9c(puVar12[7]);
              __ptr_01 = (void *)FUN_00250f9c(puVar12[0xb]);
              __ptr_02 = (void *)FUN_00250f9c(puVar12[9]);
              __ptr_03 = (void *)FUN_00250f9c(puVar12[0xd]);
              uVar9 = FUN_00244004(__ptr);
              FUN_0024373c(uVar6,0x31b7be,uVar9);
              uVar9 = FUN_00244004(__ptr_00);
              FUN_0024373c(uVar6,0x31b7c1,uVar9);
              uVar9 = FUN_00244004(__ptr_01);
              FUN_0024373c(uVar6,0x31b7c4,uVar9);
              uVar9 = FUN_00244004(__ptr_02);
              FUN_0024373c(uVar6,0x31b7c7,uVar9);
              uVar9 = FUN_00244004(__ptr_03);
              FUN_0024373c(uVar6,0x31b7ca,uVar9);
              if (__ptr != (void *)0x0) {
                free(__ptr);
              }
              if (__ptr_00 != (void *)0x0) {
                free(__ptr_00);
              }
              if (__ptr_01 != (void *)0x0) {
                free(__ptr_01);
              }
              if (__ptr_02 != (void *)0x0) {
                free(__ptr_02);
              }
              if (__ptr_03 != (void *)0x0) {
                free(__ptr_03);
              }
              FUN_0024370c(lVar5,uVar6);
              iVar2 = (int)plVar4[1];
            }
            lVar11 = lVar11 + 1;
          } while (lVar11 < iVar2);
        }
        iVar2 = FUN_0024353c(lVar5);
        bVar10 = 0 < iVar2;
        if (0 < iVar2) {
          uVar6 = FUN_00244550(lVar5,1);
          uVar9 = FUN_00244550(lVar3,1);
          FUN_0024373c(uVar9,0x31b97b,uVar6);
          FUN_0024373c(param_2,0x31b963,uVar9);
        }
        goto LAB_001520cc;
      }
    }
  }
  bVar10 = false;
LAB_001520cc:
  FUN_002427c4(lVar5);
  FUN_002427c4(lVar3);
  FUN_00251880(plVar4);
  if (*(long *)(lVar1 + 0x28) != local_68) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return bVar10;
}



// === FUN_00167e90 @ 00167e90 (1788 bytes) [cookie_sm4_init] ===

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long FUN_00167e90(long param_1)

{
  long lVar1;
  int iVar2;
  long lVar3;
  size_t sVar4;
  long lVar5;
  undefined8 *puVar6;
  ulong uVar7;
  code *pcVar8;
  char *pcVar9;
  undefined4 local_68;
  undefined4 local_64;
  ulong local_60;
  undefined *puStack_58;
  long local_48;
  
  lVar1 = tpidr_el0;
  local_48 = *(long *)(lVar1 + 0x28);
  if ((DAT_0031ca3e & 1) == 0) {
    J2qaFN4xHz(0x31ca20,5,&DAT_0029d1e4,&DAT_0029b48a,4,0x41);
    DAT_0031ca3e = 1;
  }
  if ((DAT_0031ca3d & 1) == 0) {
    HlupqL3ZAR(0x31ca0b,0xb,&DAT_0029d1d9,&DAT_0029b486,4,0xa9);
    DAT_0031ca3d = 1;
  }
  if ((DAT_0031ca3c & 1) == 0) {
    pDhsAJkF7h(0x31ca03,8,&DAT_0029d1ff,&DAT_0029d1d6,3,0x9b);
    DAT_0031ca3c = 1;
  }
  if ((DAT_0031ca3b & 1) == 0) {
    HlupqL3ZAR(0x31ca00,3,&DAT_0029d1d3,&DAT_0029d1d1,2,0xfb);
    DAT_0031ca3b = 1;
  }
  local_68 = 0;
  if (param_1 == 0) {
    lVar3 = 0;
    goto LAB_0016855c;
  }
  lVar3 = FUN_002501f8();
  local_60 = 0;
  sprintf((char *)&local_60,(char *)0x31ca00,(ulong)*(uint *)(param_1 + 4));
  pcVar8 = *(code **)(lVar3 + 0x10);
  sVar4 = strlen((char *)&local_60);
  (*pcVar8)(lVar3,&local_60,sVar4);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  (**(code **)(lVar3 + 0x10))(lVar3,0x31ca03,7);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  local_60 = (ulong)*(uint *)(param_1 + 0x14);
  lVar5 = FUN_00242634();
  puVar6 = (undefined8 *)(**(code **)(lVar5 + 8))(2,0x17,4,&local_60,0);
  if ((puVar6 != (undefined8 *)0x0) && (*(int *)(puVar6 + 1) != 0)) {
    pcVar9 = (char *)*puVar6;
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
  }
  FUN_00250cdc(puVar6);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  lVar5 = FUN_00242634();
  pcVar9 = (char *)(**(code **)(lVar5 + 8))(1,9,2,0x3d,&local_68);
  if (pcVar9 != (char *)0x0) {
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
    free(pcVar9);
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  lVar5 = FUN_00242634();
  pcVar9 = (char *)(**(code **)(lVar5 + 8))(1,9,2,0x3f,&local_68);
  if (pcVar9 != (char *)0x0) {
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
    free(pcVar9);
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  lVar5 = FUN_00242634();
  pcVar9 = (char *)(**(code **)(lVar5 + 8))(1,9,2,0x43,&local_68);
  if (pcVar9 != (char *)0x0) {
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
    free(pcVar9);
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  (**(code **)(lVar3 + 0x10))(lVar3,0x31ca0b,10);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  lVar5 = FUN_00242634();
  pcVar9 = (char *)(**(code **)(lVar5 + 8))(1,9,2,0,&local_68);
  if (pcVar9 != (char *)0x0) {
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
    free(pcVar9);
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  lVar5 = FUN_00242634();
  pcVar9 = (char *)(**(code **)(lVar5 + 8))(1,9,2,1,&local_68);
  if (pcVar9 != (char *)0x0) {
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
    free(pcVar9);
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  if (*(int *)(param_1 + 4) != 8000) {
    local_64 = 0;
    puStack_58 = PTR_DAT_0030b0f8;
    local_60 = _DAT_0030b0f0;
    lVar5 = FUN_00242634();
    puVar6 = (undefined8 *)(**(code **)(lVar5 + 8))(1,6,4,&local_60,&local_64);
    if ((puVar6 == (undefined8 *)0x0) || (*(int *)(puVar6 + 1) == 0)) {
      FUN_00250cdc(puVar6);
      puStack_58 = (undefined *)0x31ca20;
      lVar5 = FUN_00242634();
      puVar6 = (undefined8 *)(**(code **)(lVar5 + 8))(1,6,4,&local_60,&local_64);
      if (puVar6 != (undefined8 *)0x0) goto LAB_00168318;
    }
    else {
LAB_00168318:
      pcVar9 = (char *)*puVar6;
      pcVar8 = *(code **)(lVar3 + 0x10);
      sVar4 = strlen(pcVar9);
      (*pcVar8)(lVar3,pcVar9,sVar4);
    }
    FUN_00250cdc(puVar6);
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  local_60 = 0;
  sprintf((char *)&local_60,(char *)0x31ca00,(ulong)*(uint *)(param_1 + 8));
  pcVar8 = *(code **)(lVar3 + 0x10);
  sVar4 = strlen((char *)&local_60);
  (*pcVar8)(lVar3,&local_60,sVar4);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  pcVar9 = (char *)FUN_00168678();
  if (pcVar9 != (char *)0x0) {
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
    free(pcVar9);
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  puVar6 = (undefined8 *)FUN_0016879c();
  if (puVar6 != (undefined8 *)0x0) {
    pcVar9 = (char *)*puVar6;
    pcVar8 = *(code **)(lVar3 + 0x10);
    sVar4 = strlen(pcVar9);
    (*pcVar8)(lVar3,pcVar9,sVar4);
  }
  FUN_00250cdc(puVar6);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  if (_DAT_0031ca18 == 0) {
    lVar5 = FUN_00242634();
    _DAT_0031ca18 = (**(code **)(lVar5 + 8))(1,1,4,0,0);
  }
  local_60 = 0;
  puStack_58 = (undefined *)0x0;
  if (_DAT_0031ca18 == 0) {
    uVar7 = 1;
  }
  else {
    iVar2 = (**(code **)(_DAT_0031ca18 + 0x40))(_DAT_0031ca18);
    uVar7 = (ulong)(iVar2 != 0);
  }
  sprintf((char *)&local_60,(char *)0x31ca00,uVar7);
  pcVar8 = *(code **)(lVar3 + 0x10);
  sVar4 = strlen((char *)&local_60);
  (*pcVar8)(lVar3,&local_60,sVar4);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  puVar6 = *(undefined8 **)(param_1 + 0xc);
  if (puVar6 != (undefined8 *)0x0) {
    if (*(uint *)(param_1 + 4) >> 1 == 0x1194) {
      pcVar9 = (char *)*puVar6;
      pcVar8 = *(code **)(lVar3 + 0x10);
      sVar4 = strlen(pcVar9);
      (*pcVar8)(lVar3,pcVar9,sVar4);
    }
    else {
      puVar6 = (undefined8 *)FUN_0024b494(puVar6,&local_68);
      pcVar9 = (char *)*puVar6;
      pcVar8 = *(code **)(lVar3 + 0x10);
      sVar4 = strlen(pcVar9);
      (*pcVar8)(lVar3,pcVar9,sVar4);
      FUN_00250cdc(puVar6);
    }
  }
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
  (**(code **)(lVar3 + 0x18))(lVar3,0);
LAB_0016855c:
  if (*(long *)(lVar1 + 0x28) == local_48) {
    return lVar3;
  }
                    /* WARNING: Subroutine does not return */
// ... truncated (4 more lines)

// === FUN_00154ad0 @ 00154ad0 (1648 bytes) [cookie_sm4_init] ===

int * FUN_00154ad0(int param_1,long param_2)

{
  int iVar1;
  int iVar2;
  long *plVar3;
  long *plVar4;
  long *plVar5;
  long *plVar6;
  long *plVar7;
  long *plVar8;
  long *plVar9;
  long lVar10;
  undefined8 uVar11;
  undefined8 *__ptr;
  undefined8 *puVar12;
  long *plVar13;
  int *piVar14;
  long *plVar15;
  
  if ((DAT_0031bb34 & 1) == 0) {
    J2qaFN4xHz(0x31b9f0,2,&DAT_0029c2c1,&DAT_0029c3ec,4,0x37);
    DAT_0031bb34 = 1;
  }
  if ((DAT_0031bb33 & 1) == 0) {
    J2qaFN4xHz(0x31b9eb,5,&DAT_0029c2bc,&DAT_0029c3e8,4,0x81);
    DAT_0031bb33 = 1;
  }
  if ((DAT_0031bb32 & 1) == 0) {
    J2qaFN4xHz(0x31b9e2,9,&DAT_0029c2b3,&DAT_0029c2b1,2,0xa9);
    DAT_0031bb32 = 1;
  }
  if ((DAT_0031bb31 & 1) == 0) {
    pDhsAJkF7h(0x31b9da,8,&DAT_0029c008,&DAT_0029c3e4,4,0x23);
    DAT_0031bb31 = 1;
  }
  if ((DAT_0031bb30 & 1) == 0) {
    J2qaFN4xHz(0x31b9c6,0x14,&DAT_0029c29d,&DAT_0029c3e0,4,0x8f);
    DAT_0031bb30 = 1;
  }
  if ((DAT_0031bb2f & 1) == 0) {
    HlupqL3ZAR(0x31b9ba,0xc,&DAT_0029c291,&DAT_0029c3dc,4,0xf3);
    DAT_0031bb2f = 1;
  }
  if ((DAT_0031bb2e & 1) == 0) {
    J2qaFN4xHz(0x31b9b1,9,&DAT_0029c288,&DAT_0029c285,3,0xbb);
    DAT_0031bb2e = 1;
  }
  if ((DAT_0031bb19 & 1) == 0) {
    HlupqL3ZAR(0x31b8a5,0xd,&DAT_0029c18e,&DAT_0029baef,4,0xed);
    DAT_0031bb19 = 1;
  }
  if ((DAT_0031bb18 & 1) == 0) {
    pDhsAJkF7h(0x31b892,0x13,&DAT_0029c17b,&DAT_0029c179,2,0x7d);
    DAT_0031bb18 = 1;
  }
  if ((DAT_0031bb17 & 1) == 0) {
    HlupqL3ZAR(0x31b888,10,&DAT_0029c16f,&DAT_0029c16d,2,0x9b);
    DAT_0031bb17 = 1;
  }
  if (param_2 == 0) {
    return (int *)0x0;
  }
  plVar3 = (long *)FUN_002448a4(param_2,0x31b9b1,0);
  plVar4 = (long *)FUN_002448a4(param_2,0x31b9ba,0);
  plVar5 = (long *)FUN_002448a4(param_2,0x31b9c6,0);
  plVar6 = (long *)FUN_002448a4(param_2,0x31b888,0);
  plVar7 = (long *)FUN_002448a4(param_2,0x31b8a5,0);
  plVar8 = (long *)FUN_002448a4(param_2,0x31b892,0);
  if ((plVar3 == (long *)0x0) || (*plVar3 == 0)) {
LAB_00154f8c:
    plVar9 = (long *)0x0;
    plVar13 = (long *)0x0;
  }
  else {
    plVar15 = (long *)0x0;
    if (plVar4 == (long *)0x0) {
LAB_00155010:
      plVar15 = (long *)0x0;
      plVar13 = (long *)0x0;
      piVar14 = (int *)0x0;
      plVar9 = plVar15;
      goto LAB_00154f9c;
    }
    plVar13 = (long *)0x0;
    piVar14 = (int *)0x0;
    plVar9 = (long *)0x0;
    if ((int)plVar3[1] == 0) goto LAB_00154f9c;
    if (*plVar4 == 0) goto LAB_00154f8c;
    plVar15 = (long *)0x0;
    if (plVar5 == (long *)0x0) goto LAB_00155010;
    plVar13 = (long *)0x0;
    piVar14 = (int *)0x0;
    plVar9 = (long *)0x0;
    if ((int)plVar4[1] == 0) goto LAB_00154f9c;
    if (*plVar5 == 0) goto LAB_00154f8c;
    plVar15 = (long *)0x0;
    if (plVar6 == (long *)0x0) goto LAB_00155010;
    plVar13 = (long *)0x0;
    piVar14 = (int *)0x0;
    plVar9 = (long *)0x0;
    if ((int)plVar5[1] == 0) goto LAB_00154f9c;
    if (*plVar6 == 0) goto LAB_00154f8c;
    plVar15 = (long *)0x0;
    if (plVar7 == (long *)0x0) goto LAB_00155010;
    plVar13 = (long *)0x0;
    piVar14 = (int *)0x0;
    plVar9 = (long *)0x0;
    if ((int)plVar6[1] == 0) goto LAB_00154f9c;
    if ((((*plVar7 == 0) || ((int)plVar7[1] == 0)) || (iVar1 = FUN_001528d0(), iVar1 == -1)) ||
       (iVar2 = FUN_00251050(plVar5,0), iVar2 < 1)) goto LAB_00154f8c;
    if (param_1 != 1) {
      plVar15 = (long *)0x0;
      plVar9 = (long *)0x0;
      plVar13 = (long *)0x0;
LAB_00155034:
      piVar14 = malloc(0x58);
      if (piVar14 != (int *)0x0) {
        memset(piVar14 + 3,0,0x4c);
        piVar14[1] = 1;
        piVar14[2] = 1;
        *piVar14 = param_1;
        uVar11 = FUN_00251b6c(*plVar6);
        *(undefined8 *)(piVar14 + 4) = uVar11;
        uVar11 = FUN_00251b6c(*plVar7);
        *(undefined8 *)(piVar14 + 6) = uVar11;
        if (((plVar8 != (long *)0x0) && (*plVar8 != 0)) && ((int)plVar8[1] != 0)) {
          uVar11 = FUN_00251b6c();
          *(undefined8 *)(piVar14 + 8) = uVar11;
        }
        piVar14[10] = iVar2;
        uVar11 = FUN_00251b6c(*plVar3);
        *(undefined8 *)(piVar14 + 0xe) = uVar11;
        piVar14[0x10] = iVar1;
        if (param_1 == 1) {
          piVar14[0xb] = 1;
          __ptr = malloc(0x20);
          if (__ptr != (undefined8 *)0x0) {
            __ptr[3] = 0;
            uVar11 = FUN_00251b6c(*plVar15);
            __ptr[1] = uVar11;
            uVar11 = FUN_00251b6c(*plVar9);
            __ptr[2] = uVar11;
            uVar11 = FUN_00251b6c(*plVar13);
            *__ptr = uVar11;
            puVar12 = malloc(8);
            if (puVar12 == (undefined8 *)0x0) {
              piVar14[0xc] = 0;
              piVar14[0xd] = 0;
              free(__ptr);
            }
            else {
              *(undefined8 **)(piVar14 + 0xc) = puVar12;
              *puVar12 = __ptr;
            }
          }
        }
      }
      goto LAB_00154f9c;
    }
    plVar13 = (long *)FUN_002448a4(param_2,0x31b9da,0);
    plVar9 = (long *)FUN_002448a4(param_2,0x31b9e2,0);
    if ((plVar13 != (long *)0x0) && (*plVar13 != 0)) {
      plVar15 = (long *)0x0;
      if ((plVar9 == (long *)0x0) || ((int)plVar13[1] == 0)) {
        piVar14 = (int *)0x0;
        goto LAB_00154f9c;
      }
      if ((*plVar9 != 0) && ((int)plVar9[1] != 0)) {
        plVar15 = (long *)FUN_00251b6c(*plVar3);
        lVar10 = (*(code *)plVar15[2])(plVar15,0x31b9f0);
        lVar10 = (**(code **)(lVar10 + 0x10))(plVar15,*plVar4);
        lVar10 = (**(code **)(lVar10 + 0x10))(plVar15,0x31b9f0);
        lVar10 = (**(code **)(lVar10 + 0x10))(plVar15,*plVar5);
        (**(code **)(lVar10 + 0x10))(plVar15,0x31b9eb);
        goto LAB_00155034;
      }
    }
  }
  plVar15 = (long *)0x0;
  piVar14 = (int *)0x0;
LAB_00154f9c:
  FUN_00251e20(plVar3);
  FUN_00251e20(plVar4);
  FUN_00251e20(plVar5);
  FUN_00251e20(plVar13);
  FUN_00251e20(plVar9);
  FUN_00251e20(plVar6);
  FUN_00251e20(plVar7);
  FUN_00251e20(plVar8);
  FUN_00251e20(plVar15);
  return piVar14;
}



// === FUN_0016640c @ 0016640c (1600 bytes) [cookie_sm4_init] ===

undefined8 FUN_0016640c(long param_1,undefined8 *param_2,int *param_3)

{
  uint uVar1;
  long lVar2;
  long lVar3;
  long lVar4;
  long lVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  undefined8 *puVar8;
  undefined8 uVar9;
  char *__ptr;
  undefined4 uVar10;
  int local_8c;
  undefined8 local_88;
  undefined8 uStack_80;
  undefined8 local_78;
  undefined8 uStack_70;
  long local_68;
  
  lVar2 = tpidr_el0;
  local_68 = *(long *)(lVar2 + 0x28);
  if ((DAT_0031c9e8 & 1) == 0) {
    pDhsAJkF7h(0x31c948,0xf,&DAT_0029d145,&DAT_0029d142,3,0x1f);
    DAT_0031c9e8 = 1;
  }
  if ((DAT_0031c9e7 & 1) == 0) {
    HlupqL3ZAR(0x31c93f,9,&DAT_0029d139,&DAT_0029baef,4,0xed);
    DAT_0031c9e7 = 1;
  }
  if ((DAT_0031c9e6 & 1) == 0) {
    pDhsAJkF7h(0x31c938,7,&DAT_0029d132,&DAT_0029d130,2,0x7d);
    DAT_0031c9e6 = 1;
  }
  if ((DAT_0031c9d6 & 1) == 0) {
    pDhsAJkF7h(0x31c707,5,&DAT_0029cf41,&DAT_0029cf3e,3,0xdd);
    DAT_0031c9d6 = 1;
  }
  if ((DAT_0031c9d5 & 1) == 0) {
    HlupqL3ZAR(0x31c700,7,&DAT_0029cf37,&DAT_0029b6fb,4,0x97);
    DAT_0031c9d5 = 1;
  }
  if ((DAT_0031c9d4 & 1) == 0) {
    HlupqL3ZAR(0x31c6fc,4,&DAT_0029d1c5,&DAT_0029b6f7,4,0xfd);
    DAT_0031c9d4 = 1;
  }
  if ((DAT_0031c9d3 & 1) == 0) {
    HlupqL3ZAR(0x31c6f3,9,&DAT_0029cf2e,&DAT_0029cf2b,3,0xb9);
    DAT_0031c9d3 = 1;
  }
  if ((DAT_0031c9d2 & 1) == 0) {
    HlupqL3ZAR(0x31c6ee,5,&DAT_0029cf26,&DAT_0029b50c,4,0x47);
    DAT_0031c9d2 = 1;
  }
  if ((DAT_0031c9d1 & 1) == 0) {
    pDhsAJkF7h(0x31c6e6,8,&DAT_0029cec8,&DAT_0029cf24,2,0xbb);
    DAT_0031c9d1 = 1;
  }
  if ((DAT_0031c9d0 & 1) == 0) {
    J2qaFN4xHz(0x31c6e2,4,&DAT_0029d1c1,&DAT_0029b508,4,0x43);
    DAT_0031c9d0 = 1;
  }
  if ((DAT_0031c9cc & 1) == 0) {
    J2qaFN4xHz(0x31c6c0,3,&DAT_0029cefd,&DAT_0029b48a,4,0x41);
    DAT_0031c9cc = 1;
  }
  if ((DAT_0031c9c9 & 1) == 0) {
    HlupqL3ZAR(0x31c698,0xf,&DAT_0029ceda,&DAT_0029ced8,2,0xfb);
    DAT_0031c9c9 = 1;
  }
  local_8c = 0;
  if (((param_1 == 0) || (param_2 == (undefined8 *)0x0)) || (*(int *)(param_2 + 1) == 0)) {
    local_8c = 1;
    __ptr = (char *)0x0;
    puVar8 = (undefined8 *)0x0;
    uVar7 = 0;
    uVar6 = 0;
    lVar4 = 0;
    lVar3 = 0;
  }
  else {
    (*(code *)param_2[3])(param_2,0);
    lVar3 = FUN_00242f84(*param_2);
    if (lVar3 == 0) {
LAB_001668cc:
      __ptr = (char *)0x0;
      puVar8 = (undefined8 *)0x0;
      uVar7 = 0;
      uVar6 = 0;
      lVar4 = 0;
    }
    else {
      lVar4 = FUN_002435f4(lVar3,0x31c6e2);
      lVar5 = FUN_002435f4(lVar3,0x31c6e6);
      if ((lVar4 == 0) || (lVar5 == 0)) goto LAB_001668cc;
      uVar6 = FUN_00250a80(*(undefined8 *)(lVar4 + 0x20));
      uVar7 = FUN_00250a80(*(undefined8 *)(lVar5 + 0x20));
      lVar4 = FUN_00242634();
      local_88 = CONCAT44(local_88._4_4_,10);
      (**(code **)(lVar4 + 8))(1,0x11,2,&local_88,0);
      uStack_70 = 0x31c6c0;
      local_78 = 2;
      local_88 = uVar6;
      uStack_80 = uVar7;
      lVar4 = FUN_00242634();
      puVar8 = (undefined8 *)(**(code **)(lVar4 + 8))(1,6,3,&local_88,&local_8c);
      if (((puVar8 == (undefined8 *)0x0) || (*(int *)(puVar8 + 1) == 0)) || (local_8c != 0)) {
        __ptr = (char *)0x0;
        lVar4 = 0;
        goto LAB_001669a4;
      }
      (*(code *)puVar8[3])(puVar8,0);
      lVar4 = FUN_00242f84(*puVar8);
      if ((lVar4 == 0) || (lVar5 = FUN_002435f4(lVar4,0x31c6ee), lVar5 == 0)) {
LAB_001669a0:
        __ptr = (char *)0x0;
      }
      else {
        if (*(int *)(lVar5 + 0x18) != 200) {
          local_8c = 8;
          __ptr = (char *)0x0;
          goto LAB_001669a8;
        }
        if (*(int *)(param_1 + 4) == 100) {
          lVar5 = FUN_002435f4(lVar4,0x31c6f3);
          if ((lVar5 == 0) || (*(int *)(lVar5 + 0x1c) != 3)) goto LAB_001669a0;
          *(undefined4 *)(param_1 + 4) = *(undefined4 *)(lVar5 + 0x18);
          lVar5 = FUN_002435f4(lVar4,0x31c6fc);
          if ((lVar5 == 0) || (*(int *)(lVar5 + 0x1c) != 3)) {
            uVar10 = 3;
          }
          else {
            uVar10 = *(undefined4 *)(lVar5 + 0x18);
          }
          *(undefined4 *)(param_1 + 0x14) = uVar10;
        }
        lVar5 = FUN_002435f4(lVar4,0x31c700);
        if ((lVar5 != 0) && (uVar1 = *(uint *)(param_1 + 0x14), uVar1 < 4)) {
          uVar9 = FUN_002435f4(lVar5,0x31c938);
          FUN_001673e8(uVar9,0x31c698,uVar1);
          uVar9 = FUN_002435f4(lVar5,0x31c93f);
          FUN_001673e8(uVar9,0x31c948,uVar1);
        }
        lVar5 = FUN_002435f4(lVar4,0x31c707);
        if (lVar5 == 0) goto LAB_001669a0;
        if (*(int *)(lVar5 + 0x1c) != 4) {
          if (*(int *)(lVar5 + 0x1c) != 6) goto LAB_001669a0;
          __ptr = (char *)FUN_00243010();
          if (__ptr == (char *)0x0) goto LAB_001669a4;
LAB_00166a34:
          uVar9 = FUN_00250a80(__ptr);
          goto joined_r0x001669b0;
        }
        __ptr = strdup(*(char **)(lVar5 + 0x20));
        if (__ptr != (char *)0x0) goto LAB_00166a34;
      }
    }
LAB_001669a4:
    local_8c = 7;
  }
LAB_001669a8:
  uVar9 = 0;
joined_r0x001669b0:
  if (param_3 != (int *)0x0) {
    *param_3 = local_8c;
  }
  if (__ptr != (char *)0x0) {
    free(__ptr);
  }
  FUN_002427c4(lVar4);
  FUN_00250cdc(puVar8);
  FUN_00250cdc(uVar7);
  FUN_00250cdc(uVar6);
  FUN_002427c4(lVar3);
  if (*(long *)(lVar2 + 0x28) == local_68) {
    return uVar9;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



// === FUN_0016d8b8 @ 0016d8b8 (1440 bytes) [cookie_sm4_init] ===

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined4 FUN_0016d8b8(undefined4 *param_1,undefined *param_2)

{
  undefined4 uVar1;
  undefined4 uVar2;
  long lVar3;
  int iVar4;
  undefined8 uVar5;
  undefined *local_1b0;
  undefined *local_190;
  undefined *local_170;
  undefined *local_150;
  undefined *local_130;
  undefined *local_110;
  long local_d8;
  long local_d0;
  long local_c8;
  long local_c0;
  long local_b8;
  long local_a8;
  long local_98;
  long *local_90;
  undefined4 local_84;
  int local_6c;
  char acStack_68 [64];
  long local_28;
  
  lVar3 = tpidr_el0;
  local_28 = *(long *)(lVar3 + 0x28);
  if ((DAT_0031d3fa & 1) == 0) {
    HlupqL3ZAR(0x31d2d8,3,&DAT_0029d3c5,&DAT_0029d3c3,2,0xfb);
    DAT_0031d3fa = 1;
  }
  local_84 = 0;
  local_90 = (long *)0x0;
  local_6c = 0;
  local_98 = 0;
  local_a8 = 0;
  local_b8 = 0;
  local_c0 = 0;
  local_c8 = 0;
  local_d0 = 0;
  local_d8 = 0;
  if ((((param_1 != (undefined4 *)0x0) && (param_1[2] != 0)) && (iVar4 = FUN_0016d7d8(), iVar4 != 0)
      ) && (local_90 = (long *)FUN_002536f0(&local_6c), local_90 != (long *)0x0)) {
    memset(acStack_68,0,0x40);
    sprintf(acStack_68,(char *)0x31d2d8,(ulong)(uint)param_1[2]);
    local_98 = (**(code **)(*local_90 + 0x538))(local_90,acStack_68);
    uVar1 = param_1[1];
    uVar2 = *param_1;
    if (*(long *)(param_1 + 5) == 0) {
      local_110 = &DAT_0029f8d9;
    }
    else {
      local_110 = *(undefined **)(param_1 + 5);
    }
    local_a8 = (**(code **)(*local_90 + 0x538))(local_90,local_110);
    uVar5 = *(undefined8 *)(param_1 + 3);
    if (*(long *)(param_1 + 7) == 0) {
      local_130 = &DAT_0029f8d9;
    }
    else {
      local_130 = *(undefined **)(param_1 + 7);
    }
    local_c0 = (**(code **)(*local_90 + 0x538))(local_90,local_130);
    if (*(long *)(param_1 + 9) == 0) {
      local_150 = &DAT_0029f8d9;
    }
    else {
      local_150 = *(undefined **)(param_1 + 9);
    }
    local_c8 = (**(code **)(*local_90 + 0x538))(local_90,local_150);
    if (*(long *)(param_1 + 0xb) == 0) {
      local_170 = &DAT_0029f8d9;
    }
    else {
      local_170 = *(undefined **)(param_1 + 0xb);
    }
    local_d0 = (**(code **)(*local_90 + 0x538))(local_90,local_170);
    if (*(long *)(param_1 + 0xd) == 0) {
      local_190 = &DAT_0029f8d9;
    }
    else {
      local_190 = *(undefined **)(param_1 + 0xd);
    }
    local_d8 = (**(code **)(*local_90 + 0x538))(local_90,local_190);
    local_1b0 = param_2;
    if (param_2 == (undefined *)0x0) {
      local_1b0 = &DAT_0029f8d9;
    }
    local_b8 = (**(code **)(*local_90 + 0x538))(local_90,local_1b0);
    local_84 = (**(code **)(*local_90 + 0x408))
                         (local_90,_DAT_0031d2c0,_DAT_0031d2d0,local_98,uVar1,uVar2,local_a8,uVar5,
                          local_b8,local_c0,local_c8,local_d0,local_d8);
  }
  if (local_98 != 0) {
    (**(code **)(*local_90 + 0xb8))(local_90,local_98);
  }
  if (local_a8 != 0) {
    (**(code **)(*local_90 + 0xb8))(local_90,local_a8);
  }
  if (local_b8 != 0) {
    (**(code **)(*local_90 + 0xb8))(local_90,local_b8);
  }
  if (local_c0 != 0) {
    (**(code **)(*local_90 + 0xb8))(local_90,local_c0);
  }
  if (local_c8 != 0) {
    (**(code **)(*local_90 + 0xb8))(local_90,local_c8);
  }
  if (local_d0 != 0) {
    (**(code **)(*local_90 + 0xb8))(local_90,local_d0);
  }
  if (local_d8 != 0) {
    (**(code **)(*local_90 + 0xb8))(local_90,local_d8);
  }
  if (local_6c != 0) {
    FUN_00253d5c();
  }
  lVar3 = tpidr_el0;
  if (*(long *)(lVar3 + 0x28) != local_28) {
                    /* WARNING: Subroutine does not return */
    __stack_chk_fail();
  }
  return local_84;
}



// === FUN_001ae428 @ 001ae428 (2340 bytes) [sgcookiecheck_sm4_uvm] ===

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined4 FUN_001ae428(long *param_1)

{
  undefined4 uVar1;
  long lVar2;
  ulong uVar3;
  long lVar4;
  long lVar5;
  long lVar6;
  
  if ((DAT_0031fed2 & 1) == 0) {
    J2qaFN4xHz(0x31feb0,10,&DAT_0029fc5d,&DAT_0029fc5a,3,0xb);
    DAT_0031fed2 = 1;
  }
  if ((DAT_0031fed1 & 1) == 0) {
    HlupqL3ZAR(0x31fe96,0x1a,&DAT_0029fc40,&DAT_0029fc3e,2,0xd9);
    DAT_0031fed1 = 1;
  }
  if ((DAT_0031fed0 & 1) == 0) {
    HlupqL3ZAR(0x31fe7d,0x19,&DAT_0029fc25,&DAT_0029fc22,3,0x45);
    DAT_0031fed0 = 1;
  }
  if ((DAT_0031fecf & 1) == 0) {
    J2qaFN4xHz(0x31fe3c,0x41,&DAT_0029fbe1,&DAT_0029bf2e,4,0xdb);
    DAT_0031fecf = 1;
  }
  if ((DAT_0031fecd & 1) == 0) {
    HlupqL3ZAR(0x31fe02,0x26,&DAT_0029fba7,&DAT_0029fba4,3,0x29);
    DAT_0031fecd = 1;
  }
  if ((DAT_0031fecc & 1) == 0) {
    pDhsAJkF7h(0x31fdf8,10,&DAT_0029fb9a,&DAT_0029fb98,2,0x53);
    DAT_0031fecc = 1;
  }
  if ((DAT_0031fec8 & 1) == 0) {
    pDhsAJkF7h(0x31fd6b,0x17,&DAT_0029fb43,&DAT_0029fb40,3,0x81);
    DAT_0031fec8 = 1;
  }
  if ((DAT_0031fec7 & 1) == 0) {
    pDhsAJkF7h(0x31fd60,0xb,&DAT_0029fb35,&DAT_0029fb32,3,0xdd);
    DAT_0031fec7 = 1;
  }
  if ((DAT_0031fec6 & 1) == 0) {
    HlupqL3ZAR(0x31fd19,0x3a,&DAT_0029faf8,&DAT_0029b6fb,4,0x97);
    DAT_0031fec6 = 1;
  }
  if ((DAT_0031fec5 & 1) == 0) {
    HlupqL3ZAR(0x31fd08,0x11,&DAT_0029fae7,&DAT_0029b6f7,4,0xfd);
    DAT_0031fec5 = 1;
  }
  if ((DAT_0031fec4 & 1) == 0) {
    HlupqL3ZAR(0x31fce0,0x1d,&DAT_0029faca,&DAT_0029fac7,3,0xb9);
    DAT_0031fec4 = 1;
  }
  if ((DAT_0031fec3 & 1) == 0) {
    HlupqL3ZAR(0x31fccf,4,&DAT_0029fc6b,&DAT_0029b50c,4,0x47);
    DAT_0031fec3 = 1;
  }
  if ((DAT_0031fec2 & 1) == 0) {
    pDhsAJkF7h(0x31fcb8,0x17,&DAT_0029fab0,&DAT_0029faae,2,0xbb);
    DAT_0031fec2 = 1;
  }
  if ((DAT_0031fec1 & 1) == 0) {
    J2qaFN4xHz(0x31fc99,0x16,&DAT_0029fa98,&DAT_0029b508,4,0x43);
    DAT_0031fec1 = 1;
  }
  if ((DAT_0031fec0 & 1) == 0) {
    J2qaFN4xHz(0x31fc88,0x11,&DAT_0029fa87,&DAT_0029fa85,2,0xfb);
    DAT_0031fec0 = 1;
  }
  if ((DAT_0031febf & 1) == 0) {
    J2qaFN4xHz(0x31fc7c,4,&DAT_0029fc67,&DAT_0029fa82,3,0xad);
    DAT_0031febf = 1;
  }
  if ((DAT_0031febe & 1) == 0) {
    HlupqL3ZAR(0x31fc68,0x14,&DAT_0029fa6e,&DAT_0029b48e,4,7);
    DAT_0031febe = 1;
  }
  if ((DAT_0031febd & 1) == 0) {
    J2qaFN4xHz(0x31fc4a,0x15,&DAT_0029fa59,&DAT_0029b48a,4,0x41);
    DAT_0031febd = 1;
  }
  if ((DAT_0031febc & 1) == 0) {
    HlupqL3ZAR(0x31fc38,0x12,&DAT_0029fa47,&DAT_0029b486,4,0xa9);
    DAT_0031febc = 1;
  }
  if ((DAT_0031febb & 1) == 0) {
    pDhsAJkF7h(0x31fbf0,0x3b,&DAT_0029fa0c,&DAT_0029fa09,3,0x9b);
    DAT_0031febb = 1;
  }
  if ((DAT_0031feba & 1) == 0) {
    HlupqL3ZAR(0x31fba8,0x3c,&DAT_0029f9cd,&DAT_0029f9cb,2,0xfb);
    DAT_0031feba = 1;
  }
  lVar2 = (**(code **)(*param_1 + 0x30))(param_1,0x31fba8);
  uVar3 = FUN_00253918(param_1);
  lVar6 = 0;
  uVar1 = 0xffffffff;
  if ((lVar2 != 0) && ((uVar3 & 1) == 0)) {
    _DAT_0031fbe8 = (**(code **)(*param_1 + 0xa8))(param_1,lVar2);
    uVar3 = FUN_00253918(param_1);
    if ((_DAT_0031fbe8 == 0) || ((uVar3 & 1) != 0)) {
      lVar6 = 0;
      _DAT_0031fbe8 = 0;
    }
    else {
      lVar6 = (**(code **)(*param_1 + 0x30))(param_1,0x31fbf0);
      uVar3 = FUN_00253918(param_1);
      uVar1 = 0xffffffff;
      if ((lVar6 == 0) || ((uVar3 & 1) != 0)) goto LAB_001aec0c;
      _DAT_0031fc30 = (**(code **)(*param_1 + 0xa8))(param_1,lVar6);
      uVar3 = FUN_00253918(param_1);
      if ((_DAT_0031fc30 == 0) || ((uVar3 & 1) != 0)) {
        _DAT_0031fc30 = 0;
      }
      else {
        _DAT_0031fc60 = (**(code **)(*param_1 + 0x388))(param_1,_DAT_0031fbe8,0x31fc38,0x31fc4a);
        uVar3 = FUN_00253918(param_1);
        if ((_DAT_0031fc60 == 0) || ((uVar3 & 1) != 0)) {
          _DAT_0031fc60 = 0;
        }
        else {
          _DAT_0031fc80 = (**(code **)(*param_1 + 0x388))(param_1,_DAT_0031fbe8,0x31fc68,0x31fc7c);
          uVar3 = FUN_00253918(param_1);
          if ((_DAT_0031fc80 == 0) || ((uVar3 & 1) != 0)) {
            _DAT_0031fc80 = 0;
          }
          else {
            _DAT_0031fcb0 = (**(code **)(*param_1 + 0x388))(param_1,_DAT_0031fbe8,0x31fc88,0x31fc99)
            ;
            uVar3 = FUN_00253918(param_1);
            if ((_DAT_0031fcb0 == 0) || ((uVar3 & 1) != 0)) {
              _DAT_0031fcb0 = 0;
            }
            else {
              _DAT_0031fcd8 =
                   (**(code **)(*param_1 + 0x388))(param_1,_DAT_0031fbe8,0x31fcb8,0x31fccf);
              uVar3 = FUN_00253918(param_1);
              if ((_DAT_0031fcd8 == 0) || ((uVar3 & 1) != 0)) {
                _DAT_0031fcd8 = 0;
              }
              else {
                _DAT_0031fd00 =
                     (**(code **)(*param_1 + 0x388))(param_1,_DAT_0031fbe8,0x31fce0,0x31fccf);
                uVar3 = FUN_00253918(param_1);
                if ((_DAT_0031fd00 == 0) || ((uVar3 & 1) != 0)) {
                  _DAT_0031fd00 = 0;
                }
                else {
                  _DAT_0031fd58 =
                       (**(code **)(*param_1 + 0x388))(param_1,_DAT_0031fc30,0x31fd08,0x31fd19);
                  uVar3 = FUN_00253918(param_1);
                  if ((_DAT_0031fd58 == 0) || ((uVar3 & 1) != 0)) {
                    _DAT_0031fd58 = 0;
                  }
                  else {
                    _DAT_0031fd88 =
                         (**(code **)(*param_1 + 0x388))(param_1,_DAT_0031fc30,0x31fd60,0x31fd6b);
                    uVar3 = FUN_00253918(param_1);
                    if ((_DAT_0031fd88 != 0) && ((uVar3 & 1) == 0)) {
                      uVar1 = FUN_001b5040(param_1);
                      goto LAB_001aec0c;
                    }
                    _DAT_0031fd88 = 0;
                  }
                }
              }
            }
          }
        }
      }
    }
    uVar1 = 0xffffffff;
  }
LAB_001aec0c:
  lVar4 = (**(code **)(*param_1 + 0x30))(param_1,0x31fe3c);
  if (((lVar4 != 0) &&
      (lVar5 = (**(code **)(*param_1 + 0x388))(param_1,lVar4,0x31fe7d,0x31fe96), lVar5 != 0)) &&
     (lVar4 = (**(code **)(*param_1 + 0x390))(param_1,lVar4,lVar5), lVar4 != 0)) {
    _DAT_0031fde0 = (**(code **)(*param_1 + 0xa8))(param_1,lVar4);
    if ((_DAT_0031fde0 != 0) &&
       (lVar4 = (**(code **)(*param_1 + 0xf8))(param_1,_DAT_0031fde0), lVar4 != 0)) {
      _DAT_0031fde8 = (**(code **)(*param_1 + 0x108))(param_1,lVar4,0x31fdf8,0x31fe02);
      if (_DAT_0031fde8 != 0) {
        _DAT_0031fdf0 = (**(code **)(*param_1 + 0x108))(param_1,lVar4,0x31feb0,0x31fe96);
      }
    }
  }
  FUN_00253918(param_1);
  FUN_00253a08(param_1,lVar2);
  FUN_00253a08(param_1,lVar6);
  return uVar1;
}



// === FUN_001af390 @ 001af390 (224 bytes) [sgcookiecheck_sm4_uvm] ===

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void FUN_001af390(void)

{
  long lVar1;
  long lVar2;
  undefined4 local_44;
  undefined1 *local_40;
  undefined8 uStack_38;
  undefined *local_30;
  long local_28;
  
  lVar1 = tpidr_el0;
  local_28 = *(long *)(lVar1 + 0x28);
  FUN_001b5534();
  if ((DAT_0031fd90 & 1) == 0) {
    local_44 = 0;
    DAT_0031fd90 = 1;
    local_30 = PTR_LAB_00310170;
    uStack_38 = _UNK_00310168;
    local_40 = _DAT_00310160;
    lVar2 = FUN_00242634();
    (**(code **)(lVar2 + 0x10))(1,0x1e,1,0,&local_40,&local_44);
  }
  local_30 = (undefined *)(ulong)_DAT_0031fd94;
  local_44 = 0;
  uStack_38 = 0;
  local_40 = &LAB_001afbf8;
  lVar2 = FUN_00242634();
  (**(code **)(lVar2 + 0x10))(1,8,2,0,&local_40,&local_44);
  if (*(long *)(lVar1 + 0x28) == local_28) {
    return;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}



// === FUN_001aeee0 @ 001aeee0 (200 bytes) [sgcookiecheck_sm4_uvm] ===

undefined8 FUN_001aeee0(long param_1,undefined4 *param_2)

{
  long lVar1;
  int iVar2;
  undefined8 uVar3;
  long lVar4;
  undefined4 local_3c;
  long local_38;
  
  lVar1 = tpidr_el0;
  local_38 = *(long *)(lVar1 + 0x28);
  local_3c = 0;
  iVar2 = FUN_002575dc(*(undefined8 *)(param_1 + 0x10),*(undefined8 *)(param_1 + 0x18),0);
  FUN_001b9e30((long)iVar2,&local_3c);
  if (param_2 != (undefined4 *)0x0) {
    *param_2 = local_3c;
  }
  uVar3 = FUN_002558d4(*(undefined8 *)(param_1 + 0x10),1);
  DAT_003169b8 = iVar2;
  lVar4 = FUN_00242634();
  (**(code **)(lVar4 + 0x10))(1,0x22,0xb,0,iVar2,param_2);
  FUN_001b08fc(iVar2);
  if (*(long *)(lVar1 + 0x28) == local_38) {
    return uVar3;
  }
                    /* WARNING: Subroutine does not return */
  __stack_chk_fail();
}


