package com.by.sophixtest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Keep;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixApplication;
import com.taobao.sophix.SophixEntry;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Sophix入口类，专门用于初始化Sophix，不应包含任何业务逻辑。
 * 此类必须继承自SophixApplication，onCreate方法不需要实现。
 * 此类不应与项目中的其他类有任何互相调用的逻辑，必须完全做到隔离。
 * AndroidManifest中设置application为此类，而SophixEntry中设为原先Application类。
 * 注意原先Application里不需要再重复初始化Sophix，并且需要避免混淆原先Application类。
 * 如有其它自定义改造，请咨询官方后妥善处理。
 */
public class SophixStubApplication extends SophixApplication {
    private final String TAG = "SophixStubApplication";
    private final String RSA = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBZZbIsqHwGP65C5WdP61t9+G5mmHf0dqcK8W564Vz0oRAHyJHtCjahWV3PiV56swp0Vk6Fi2EtUsQ0LufevN0lvN1jZfdCgHbZKtz07GQPNRBkcJuZeFFzxOxycSxDB1luQ3gL8DuflI0+CM64Rx1r7L4ybRiQJf6Byh8HCnbDFIXjv8d4zQXMap+35xapj7Lysx/G6NbHLoaK9oRw6/lIjEIcxtWFMmg70yG23Krl/MnYhQELRCv8CiYBpztlmsNA04Helt09hXHNLpBf6tq13s0hcJOh4TvkFJXRG3OgYiNf3x7/NLWOKShWEJ6dLPMnsDkQMVMvCxm9e8h0/pNAgMBAAECggEAXXDdCCVnJ5kFKN+itSFsvgLJMFy53QUQFsLNUEsx3cfh1OQmvxzse5DlCs0IBxh1QqV5B6dIS5MXmBcDxlDlSgZP3U3Yorg9Sw9ReJ0gloY2yPrXKx3LJbw5/qMztJ6JBBI0YPZbHl5+Qt4Fotk40guE9H/EEUeP9+//XlCAMTuXgCGSFX8FW/sqKjslOGQBX2edp3icL22obiYY596uOkAt9UZpfTLBwmoYayd17oYaZb6yLhwm1WdgJtryX0WB7rJgieofYRZUw5XDRh76fnxxOjXUTTjbOAGHWdy8c+B1ogRMuEa4v+DTOzjur+6pJoADfUX2qVTGOTGoz1AgCQKBgQC61tR0qiFd6vcHcXh5lhkK1pVBRNo4TQ4xNHMezjKJuDEl4k8DzgBN6xs1NW2EHKGs2FyEj3mC1ke3OUEwHvt5V2bebnU4TkGUFTWOpDGrsASkAAzjLkeeWQNHRZIixhkuOt/zc55/CJim6Lk5jIpNq7cyhvkh2c2htkKEOveRiwKBgQCxS27q9XzR/cr6Q6Eazs0Lb0US1+cFHz+TfWJnhHsOm48wOioG9aj5Tr/2ymoKyMExl4hhB2vPnmdLuGWsicGoSFpDSIP03N6wZkkWzNcT645knzPYpplpSFxtk1iHl4hQRQvWCUT5UDqohk2am46sVbbqdLoGDaP4wjS4oMXuhwKBgCEzl+F7ch58aJV8Boqkr6KLa06D67thhgocZfCtd7Gdd65hvuTQMKGoC9eyvNljNR+/+wDbdh5X8bqPhBQHDW0MqQ0R1+a/kUu9UR0d9G2sA8cY8zFTwgpTiw+ZP1TKmyLp924Nt7afUnEmnfOqE9GlmBJ0hcUMLUx1VPCmgePtAoGALFH+HVBW7quyFRPY7zmpOmWLb+V3A+nG29jpQ5VzqbgreFPh87Fvlzsfc9Mv5RhAa2RFHHRdeB5SFjS3fgVF2wqTPeSf0BNyu8gmF0QHtBwbkX3W2R70lMQZDu9ZnzO7SM2p4syYaHas4zoHplGiveQDgOLDKgY46lOqGElgkXsCgYBRi/oQJSUZIGUUub4Q2pmPFHYLvfwuD8J+HYzdTAfghmmC6krTHkX5CcxxMMhkLlvIaI2hq9dBzt0Nt3+fEIxKlg+QUvLVhwZhyK6wTw2iHOZO5MKLIkSeiJff94LgTRDSg4G76MugV27eEdVisJWBXCLRiOpaYfOXuszlmP7Iew==";
    private final String SECRET = "f7646c29f3c44600ad65dd338104d574";
    private final String APP_ID = "333872563-1";
    // 此处SophixEntry应指定真正的Application，并且保证RealApplicationStub类名不被混淆。
    @Keep
    @SophixEntry(MyApplication.class)
    static class RealApplicationStub {
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//         如果需要使用MultiDex，需要在此处调用。
//         MultiDex.install(this);
        initSophix();
    }

    private void initSophix() {
        String appVersion = "0.0.0";
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0)
                    .versionName;
        } catch (Exception e) {
        }
        SophixManager instance = SophixManager.getInstance();

        instance.setContext(this)
                .setAppVersion(appVersion)
                .setSecretMetaData(APP_ID,SECRET,RSA)
                .setEnableDebug(true)
                .setEnableFullLog()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            Log.i(TAG, "sophix load patch success!");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 如果需要在后台重启，建议此处用SharePreference保存状态。
                            Log.i(TAG, "sophix preload patch success. restart app to make effect.");
                        }
                    }
                }).initialize();
    }
}