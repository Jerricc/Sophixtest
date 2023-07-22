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
    private final String RSA = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDHMy53VgfS0Wktaal6k8YLNwt96X50PglvLArTD4jL/Rc/sB4kY0+Y2dRfJFUtYJNf4m92tkVHQdY7vkITnTR4AKcy5kukOZpjOV+7i7cemDpbfCmTpNzSsrhMsh93eiTIkIP58tYjPu+++OyJ8OWFFtI206dH1JgAHdfKz6TdVVRQrgCfuorJ2XRSoNYaBs3dOaWukuhSG5cRo4uEHr1WpaUE73gwX0+skxMLgS5gHcA8gBf7DSUSLA7j+ahT4OzJqMmbVdG8JECaXdwUOBKRPf8h9N0Kb9zCQfprRU/7/erciVAKKhad/iuwDt/ew7e1Qyha/jcAEg/EeBwNbwRlAgMBAAECggEAfI2CFlgSmD0cOKsCr4RKpqSKaPZgfSp1F7BALGxgKrxblxT2I+Z81KQPaFX9dFwYijG5ZE7fzohZ/g0en8kCD67I3Mimr07dPAGZ3Fil7Vld0+o0zP6enJfnKP2PM1OKQGYsP1MZlfw6R/y5Wh7oRGlak7GPvRgu2pPOZB+lhEZEYfWEHQWCAwLjOtdxXBPlQW6iSxNzsakM0h/F6ruQrxeV3Rkd+LttwCmr5enf6Z4Q04KNW6Wi5HHs0t9oVz2ErHEFJSrz7REvx3z7JJQOR+rcZD0PFiYFi5VR7NVvtrN+eGZAk9ILUzU+be0okch4t5GxZn5AMcmZdgpDUsQvGQKBgQD7f190PrAMKYnbPSc6HnXlMBa/05VfW9vvhCYlFYOovG5EQfU3zZ0h6PJOLZvEEarZ5xW56OijrJtOHQDP6DLQ/1b7v++X02nLRcdQ+VXFDV4/idOTy1tKfVt4VrQBvHw1dl0Ds2fk6qtdhMBh7QnX3+EgpDDv7OkphnOmB5cIxwKBgQDKxCAxemg/OdAXCvAeVBG+cvZrTHEt64LfOIxn4NV1XkdKPgRwulx7oeviuABxJORo2pxgp+7c9+76i/8cLJ8HOX9i/YVuP5u0PG5xzm9iqyMauOtGzWxieuil04AMHSBxqsNs+V7n1m3yGJeoHqzAgnEa3fMIIJSE7/Di1/RVcwKBgQDCTbkorVoEO393c7q/y1LYnBunXsez4dt9JEL4ObP7hIFImRDkaSXyUfAKeHA0vlWafk+pz4kVE0D1y7CZ8FJ6aBwC6loombzNwH5qUAh08owTWN+u+tjwJwOpaIswIDhycZ0RQ+WL1cZJvUuJHMKTFzPurQvmAcpuO6NT7d9BdwKBgQCSljU77+V7h/0jG3o6QsbNK2lwasQL0Y48eDIxSv6mqjDZwHoPUPtZOddBgYp4QZaLIzgYPG/X+bogEaOtp6iUXYH1e5jB+mRDacjip74o09Y+FF+rQQlRLNASShPxLgFti2la1/E/o+q6MgM5vGthhSvlo312a69TZ+9632q5dwKBgQDZpIEL5p14OPE48XfG0M55EWT6aV7issUCm8eh9AjRe/IdShydrxgSwZb8IGF8EslONLgKT7KNaPRQbjF4ZrrfH3hO7uI792wNOrWgPXDBNPFJyALziwchY4aBBYeC85y4FGANa97idMa1QziHG3rQtMJl7muBxggIexDmFDAkdQ==";
    private final String SECRET = "15bde3294b4b4ef6892a896b433b373b";
    private final String APP_ID = "333873670-1";
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