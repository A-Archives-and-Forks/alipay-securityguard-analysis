# Audio Recording Forensic Analysis Report

## APK Version Information

| Version | File | SHA1 | Size | DEX | SO (arm64) |
|---------|------|------|------|-----|------------|
| v10.8.30.8000 | Alipay_10.8.30.8000_APKPure.apk | `239c2cbed30e9790b70802355f55fd395ca4e165` | 210MB | 19 | 225 |
| v10.8.30.9000 | Alipay_10.8.30.9000_APKPure.apk | `209fe7f1bcd6b3a26c7f4268e20e05b25572b015` | 211MB | 19 | 225 |

## Audio Recording Code Inventory

### 1. DexAOP Interception Points (14 total)

All `AudioRecord` and `MediaRecorder` API calls are intercepted by the DexAOP bytecode-level AOP framework:

**AudioRecord (4 points):**
| AOP Point | API | Category | Purpose |
|-----------|-----|----------|---------|
| `android_media_AudioRecord_Builder_build_proxy` | `AudioRecord.Builder.build()` | ACCESS | Intercepts mic instance creation |
| `android_media_AudioRecord_startRecording_proxy` | `AudioRecord.startRecording()` | ACCESS_BEGIN | Intercepts recording start |
| `android_media_AudioRecord_startRecording_secfw_proxy` | `AudioRecord.startRecording()` | ACCESS_BEGIN | Security framework version |
| `android_media_AudioRecord_stop_proxy` | `AudioRecord.stop()` | ACCESS_END | Intercepts recording stop |

**MediaRecorder (10 points):**
| AOP Point | API | Category |
|-----------|-----|----------|
| `MediaRecorder_start_proxy` | `start()` | ACCESS_BEGIN |
| `MediaRecorder_start_secfw_proxy` | `start()` | ACCESS_BEGIN |
| `MediaRecorder_stop_proxy` | `stop()` | ACCESS_END |
| `MediaRecorder_pause_proxy` | `pause()` | ACCESS |
| `MediaRecorder_resume_proxy` | `resume()` | ACCESS |
| `MediaRecorder_prepare_proxy` | `prepare()` | ACCESS |
| `MediaRecorder_release_proxy` | `release()` | ACCESS |
| `MediaRecorder_reset_proxy` | `reset()` | ACCESS |
| `MediaRecorder_setOutputFile_proxy` | `setOutputFile()` | ACCESS |
| `MediaRecorder_setLocation_proxy` | `setLocation()` | ACCESS |

**Additional MediaRecorder monitoring (5 points):**
- `getActiveMicrophones_proxy` — Which microphones are active
- `getActiveRecordingConfiguration_proxy` — Recording config
- `getAudioSourceMax_proxy` — Max audio source
- `getMaxAmplitude_proxy` — Audio amplitude
- `getMetrics_proxy` — Recording metrics

### 2. InterferePoint Registration

**File**: `com/alipay/fusion/interferepoint/point/generated/InterferePointInitHelper.java`

```java
// Line 672: AudioRecord creation — labeled "录音" (recording)
hashMap.put("android_media_AudioRecord_Builder_build_proxy",
    new DefaultInterferePointProperty(..., 7L,
        "p(android.permission.RECORD_AUDIO)", "录音", PointCategory.ACCESS));

// Line 673: Recording start — labeled "录音"
hashMap.put("android_media_AudioRecord_startRecording_proxy",
    new DefaultInterferePointProperty(..., 7L,
        "p(android.permission.RECORD_AUDIO)", "录音", PointCategory.ACCESS_BEGIN));

// Line 674: Security framework recording start
hashMap.put("android_media_AudioRecord_startRecording_secfw_proxy",
    new DefaultInterferePointProperty(..., 0L,
        "p(android.permission.RECORD_AUDIO)", null, PointCategory.ACCESS_BEGIN));

// Line 675: Recording stop
hashMap.put("android_media_AudioRecord_stop_proxy",
    new DefaultInterferePointProperty(..., 6L,
        "p(android.permission.RECORD_AUDIO)", null, PointCategory.ACCESS_END));
```

The `"录音"` label and `ACCESS_BEGIN/ACCESS_END` categorization indicate this is a **privacy compliance framework** that tracks when recording starts and stops.

### 3. Actual Recording Modules (25+ files)

**xmedia audio2 recording module** (`com/alipay/xmedia/audio2/record/`):
| File | Purpose |
|------|---------|
| `AudioRecordService.java` | Main recording service |
| `AudioRecorder.java` | Core recorder implementation |
| `AudioRecordReport.java` | Recording usage reporting |
| `APMAudioRecordConfig.java` | Recording configuration |
| `APMAudioRecordListener.java` | Recording event listener |
| `EncoderSelector.java` | Format selection (WAV/AAC/PCM/MP3) |
| `WAVEncoder.java` + `WAVOutputHandler.java` | WAV encoding |
| `AACEncoder.java` + `AACPts.java` | AAC encoding |
| `PCMEncoder.java` + `PCMOutputHandler.java` | Raw PCM |
| `MP3Encoder.java` + `MP3OutputHandler.java` | MP3 encoding |
| `PathManager.java` | Recording file path management |
| `EncodedDataCollector.java` | Encoded data collection |
| `Utils.java` | Utilities |

**MicrophoneEncoder modules**:
| File | Location | AudioSource |
|------|----------|-------------|
| `MicrophoneEncoder.java` | `tv/danmaku/ijk/media/encode/` | Configurable |
| `FFmpegMicEncoder.java` | `tv/danmaku/ijk/media/encode/` | `5` (VOICE_RECOGNITION) |
| `MicrophoneEncoder.java` | `com/ant/multimedia/encode/` | Configurable |
| `MicrophoneEncoderImpl.java` | `com/ant/multimedia/encode/` | Implementation |

**MP3 encoder library**:
| File | Purpose |
|------|---------|
| `AudioRecorderParams.java` | Recording parameters |
| `MP3EncoderException.java` | Encoder errors |
| `ILibLoader.java` | Native lib loader |

### 4. AudioSource Values Used

From `FFmpegMicEncoder.java` line 237:
```java
AudioRecord audioRecord = new AudioRecord(5, this.config.aSamplerate, 16, 2, this.minBufferSize * 4);
```
`AudioSource = 5` = `MediaRecorder.AudioSource.VOICE_RECOGNITION`

From `MicrophoneEncoder.java` (ijk) line 258:
```java
this.mAudioRecord = new AudioRecord(i3, audioEncoderCore2.mSampleRate,
    audioEncoderCore2.mChannelConfig, 2, minBufferSize * 4);
```
AudioSource is configurable via `i3` parameter.

### 5. TinyApp Scope Mapping

**File**: `com/alipay/fusion/lite/permission/TinyScope.java` line 38:
```java
put("android.permission.RECORD_AUDIO", "scope.audioRecord");
```
The `scope.audioRecord` scope means mini-programs can request recording permission.

## Forensic Verification Tools

### How to verify these findings:

```bash
# 1. Extract and decompile APK
jadx -d output --show-bad-code --threads-count 8 Alipay.apk

# 2. Search for AudioRecord usage
grep -rn "new AudioRecord\|AudioRecord.Builder\|startRecording" output/sources/ --include="*.java" | grep -v import

# 3. Search for DexAOP interception points
grep -n "AudioRecord\|MediaRecorder" output/sources/com/alipay/dexaop/DexAOPPoints.java

# 4. View InterferePoint registration (Chinese labels)
grep -n "录音\|RECORD_AUDIO" output/sources/com/alipay/fusion/interferepoint/point/generated/InterferePointInitHelper.java

# 5. List recording modules
find output/sources/com/alipay/xmedia/audio2/ -name "*.java"

# 6. Check AndroidManifest for RECORD_AUDIO permission
aapt2 dump permissions Alipay.apk | grep RECORD
```

## 3-LLM Cross-Verification Results

### Question: Does the app secretly record audio in the background?

| LLM | Verdict | Reasoning |
|-----|---------|-----------|
| LLM1 (Thinking) | **UNLIKELY** | DexAOP+InterferePoint is a compliance framework, not a stealth recorder. No background Service, no hidden upload channel found. |
| LLM2 (Coder) | **SUSPECT capability, UNLIKELY use** | Has full recording infra but ACCESS_BEGIN/END categorization = standard privacy tracking |
| LLM3 (Reasoning) | **UNLIKELY for stealth recording** | The "say something, see recommendations" phenomenon explained by: household-level intent fusion via shared WiFi/router MAC, not audio eavesdropping |

### Final Verdict: **TECHNICALLY CAPABLE but NO EVIDENCE of background recording**

**What exists:**
- Complete recording infrastructure (25+ files, 4 encoders: WAV/AAC/PCM/MP3)
- DexAOP interception of ALL AudioRecord and MediaRecorder APIs (14 points)
- InterferePoint compliance tracking system (85 audio-related entries)
- MicrophoneEncoder using VOICE_RECOGNITION AudioSource
- Mini-program `scope.audioRecord` permission

**What does NOT exist:**
- Background Service or JobScheduler that initiates recording silently
- Hidden audio data upload channel (separate from user-initiated recording)
- Wake-lock or foreground notification for persistent recording (required by Android 10+)
- Audio data in SecurityGuard's data reporting pipeline (22 events, none are audio)

**Alternative explanation for "talk about something, see it recommended":**
1. Same WiFi network → Router MAC shared → Household-level user profiling
2. Voice assistant/search within app → Text intent extracted → 30-min TTL cache
3. Cross-app behavioral correlation via UTDID/OAID device fingerprinting
4. Confirmation bias — users notice matching ads more than non-matching ones

## Key Files for Independent Verification

| File Path (in jadx output) | What to Look For |
|---------------------------|------------------|
| `com/alipay/dexaop/DexAOPEntry.java:6101-6151` | AudioRecord.startRecording/stop AOP proxy code |
| `com/alipay/dexaop/DexAOPPoints.java:572-575` | AudioRecord AOP point definitions |
| `com/alipay/fusion/interferepoint/point/generated/InterferePointInitHelper.java:672-675` | Recording labeled "录音" + ACCESS_BEGIN/END |
| `com/alipay/xmedia/audio2/record/biz/AudioRecordService.java` | Main recording service |
| `com/alipay/xmedia/audio2/record/biz/AudioRecorder.java` | Core recorder |
| `tv/danmaku/ijk/media/encode/FFmpegMicEncoder.java:237` | AudioSource=5 (VOICE_RECOGNITION) |
| `com/alipay/fusion/lite/permission/TinyScope.java:38` | scope.audioRecord for mini-programs |
