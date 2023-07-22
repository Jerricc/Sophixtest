@file:Suppress("SpellCheckingInspection")

import org.gradle.api.Action
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.credentials.HttpHeaderCredentials
import org.gradle.authentication.http.HttpHeaderAuthentication


// @formatter:off
object Versions {
    const val kotlin_version                = "1.6.10"
    const val kotlin_coroutines             = "1.5.2"
    const val appcompat                     = "1.4.0-alpha03"
    const val exifinterface                  = "1.3.1"
    const val core_ktx                      = "1.3.2"
    const val toast_compat                  = "1.1.0"
    const val eventbus                      = "3.1.1"
    const val constraintlayout              = "2.1.4"
    const val swiperefreshlayout            = "1.1.0"
    const val recyclerview                  = "1.1.0"
    const val gson                          = "2.10.1"
    const val live_eventbus                 = "1.7.2"
    const val material                      = "1.4.0"
    const val viewpager2                    = "1.0.0"
    const val multitype                     = "4.2.0"
    const val transition                    = "1.3.1"
    const val webkit                        = "1.3.0"
    const val fragment                      = "1.3.0-beta02"
    const val lifecycle                     = "2.3.0-beta01"
    const val activity_ktx                  = "1.2.0-beta02"
    const val photoview                     = "2.3.0"
    const val cardview                      = "1.0.0"
    const val room                          = "2.5.1"
    const val swipe_reveal_lyt              = "1.4.1"
    const val flexbox                        = "2.0.1"
    const val annotations                   = "1.3.0-alpha01"
    const val inject                        = "1"
    const val leakcanary                    = "2.6"
    const val dynamic_animation             = "1.0.0"
    const val phonenumber                   = "8.12.46"
    const val process_phoenix               = "2.1.2"
    const val navigation                    = "2.3.5"
    const val retrofit                       = "2.9.0"
    const val mmkv                          = "1.2.14"
    const val umeng_common                  = "9.5.0"
    const val umeng_asms                    = "1.4.1"
    const val umeng_apm                     = "1.7.0"
    const val umeng_share                   = "7.2.1"
    const val umeng_verify                  = "2.5.7"
    const val umeng_verify_components       = "2.1.4"
    const val umeng_push                    = "6.5.5"
    const val glide                         = "4.11.0"
    const val lottie                        = "3.4.0"
    const val code_locator                  = "2.0.0"
    const val mojito                        = "1.8.7"
    const val commons_codec                 = "1.6"
    const val zxing                         = "3.5.1"
    const val kotlin_poet                   = "1.8.0"
    const val claymore                      = "1.0.0"
    const val ultron                        = "1.0.0"
    const val markdown                      = "4.6.2"
    const val sophix                        = "3.3.5"
}

@Suppress("unused")
object Deps {
    const val activity_ktx                  = "androidx.activity:activity-ktx:${Versions.activity_ktx}"
    const val android_annotations           = "androidx.annotation:annotation:${Versions.annotations}"
    const val appcompat                     = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val cardview                      = "androidx.cardview:cardview:${Versions.cardview}"
    const val navigation_fragment           = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui                 = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val constraintlayout              = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    const val core_ktx                      = "androidx.core:core-ktx:${Versions.core_ktx}"
    const val dynamic_animation             = "androidx.dynamicanimation:dynamicanimation:${Versions.dynamic_animation}"
    const val exifinterface                 = "androidx.exifinterface:exifinterface:${Versions.exifinterface}"
    const val fragment_ktx                  = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    const val fragment                      = "androidx.fragment:fragment:${Versions.fragment}"
    const val lifecycle_livedata_ktx        = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_runtime_ktx         = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycle_viewmodel_ktx       = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val recyclerview                  = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val room_compiler                 = "androidx.room:room-compiler:${Versions.room}"
    const val room_runtime                  = "androidx.room:room-runtime:${Versions.room}"
    const val swiperefreshlayout            = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"
    const val transition                    = "androidx.transition:transition:${Versions.transition}"
    const val viewpager2                    = "androidx.viewpager2:viewpager2:${Versions.viewpager2}"
    const val swipe_reveal_layout           = "com.chauthai.swipereveallayout:swipe-reveal-layout:${Versions.swipe_reveal_lyt}"
    const val multitype                     = "com.drakeet.multitype:multitype:${Versions.multitype}"
    const val photoview                     = "com.github.chrisbanes:PhotoView:${Versions.photoview}"
    const val material                      = "com.google.android.material:material:${Versions.material}"
    const val flexbox                       = "com.google.android:flexbox:${Versions.flexbox}"
    const val gson                          = "com.google.code.gson:gson:${Versions.gson}"
    const val phonenumber                   = "com.googlecode.libphonenumber:libphonenumber:${Versions.phonenumber}"
    const val processPhoenix                = "com.jakewharton:process-phoenix:${Versions.process_phoenix}"
    const val live_eventbus                 = "com.jeremyliao:live-event-bus-x:${Versions.live_eventbus}"
    const val leakcanary                    = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"
    const val web_kit                       = "androidx.webkit:webkit:${Versions.webkit}"
    const val inject                        = "javax.inject:javax.inject:${Versions.inject}"
    const val zxing                         = "com.google.zxing:core:${Versions.zxing}"
    const val toast_compat                  = "me.drakeet.support:toastcompat:${Versions.toast_compat}"
    const val eventbus                      = "org.greenrobot:eventbus:${Versions.eventbus}"
    const val kotlin_reflect                = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
    const val kotlin_stdlib                 = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
    const val kotlin_stdlib_jdk7            = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin_version}"
    const val kotlin_stdlib_jdk8            = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin_version}"
    const val kotlin_stdlib_common          = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin_version}"
    const val kotlin_coroutines_android     = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
    const val kotlin_coroutines_core        = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}"
    const val retrofit                      = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_converter            = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val mmkv                          = "com.tencent:mmkv:${Versions.mmkv}"
    const val umeng_common                  = "com.umeng.umsdk:common:${Versions.umeng_common}"
    const val umeng_asms                    = "com.umeng.umsdk:asms:${Versions.umeng_asms}"
    const val umeng_apm                     = "com.umeng.umsdk:apm:${Versions.umeng_apm}"
    const val glide                         = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler                = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val lottie                        = "com.airbnb.android:lottie:${Versions.lottie}"
    const val newbieGuide                   = "com.github.huburt-Hu:NewbieGuide:v2.4.4"
    const val umeng_push                    = "com.umeng.umsdk:push:${Versions.umeng_push}"
    const val umeng_uverify                 = "com.umeng.umsdk:uverify:${Versions.umeng_verify}"
    const val umeng_uverify_main            = "com.umeng.umsdk:uverify-main:${Versions.umeng_verify_components}"
    const val umeng_uverify_logger          = "com.umeng.umsdk:uverify-logger:${Versions.umeng_verify_components}"
    const val umeng_uverify_crash           = "com.umeng.umsdk:uverify-crashshield:${Versions.umeng_verify_components}"
    const val umeng_share                   = "com.umeng.umsdk:share-core:${Versions.umeng_share}"
    const val umeng_share_wx                = "com.umeng.umsdk:share-wx:${Versions.umeng_share}"
    const val umeng_share_qq                = "com.umeng.umsdk:share-qq:${Versions.umeng_share}"
    const val phone_input_watcher           = "com.github.jaydroid1024:PhoneTextWatcher:0.0.2"
    const val code_locator                  = "com.bytedance.tools.codelocator:codelocator-core:${Versions.code_locator}"
    const val mojito                        = "com.github.mikaelzero.mojito:mojito:${Versions.mojito}"
    const val mojito_sketch                 = "com.github.mikaelzero.mojito:SketchImageViewLoader:${Versions.mojito}"
    const val mojito_glide                  = "com.github.mikaelzero.mojito:GlideImageLoader:${Versions.mojito}"
    const val common_codec                  = "commons-codec:commons-codec:${Versions.commons_codec}"
    const val smart_refresh_layout          = "io.github.scwang90:refresh-layout-kernel:2.0.5"
    const val wechat_share_sdk              = "com.tencent.mm.opensdk:wechat-sdk-android-without-mta:6.8.0"
    const val umeng_push_huawei_accs        = "com.umeng.umsdk:huawei-umengaccs:1.4.1"
    const val umeng_push_huawei             = "com.huawei.hms:push:6.7.0.300"
    const val umeng_push_xiaomi_accs        = "com.umeng.umsdk:xiaomi-umengaccs:1.3.1"
    const val umeng_push_xiaomi             = "com.umeng.umsdk:xiaomi-push:5.0.8"
    const val umeng_push_vivo_accs          = "com.umeng.umsdk:vivo-umengaccs:1.1.6"
    const val umeng_push_vivo               = "com.umeng.umsdk:vivo-push:3.0.0.4"
    const val umeng_push_oppo_accs          = "com.umeng.umsdk:oppo-umengaccs:1.0.8-fix"
    const val umeng_push_oppo               = "com.umeng.umsdk:oppo-push:3.1.0"
    const val umeng_push_honor_accs         = "com.umeng.umsdk:honor-umengaccs:1.1.0"
    const val umeng_push_honor              = "com.umeng.umsdk:honor-push:7.0.1.103"
    const val xlog                          = "com.elvishew:xlog:1.11.0"
    const val kotlin_poet                   = "com.squareup:kotlinpoet:${Versions.kotlin_poet}"
    const val kotlin_poet_metadata          = "com.squareup:kotlinpoet-metadata:${Versions.kotlin_poet}"
    const val kotlin_poet_metadata_specs    = "com.squareup:kotlinpoet-metadata-specs:${Versions.kotlin_poet}"
    const val apm_insight                   = "com.volcengine:apm_insight:1.4.9.cn-rc.5"
    const val apm_insight_crash             = "com.volcengine:apm_insight_crash:1.4.6-rc.14"
    const val applog                        = "com.bytedance.applog:RangersAppLog-Lite-cn:6.13.3"
    const val applog_debug_tool             = "com.bytedance.applog:RangersAppLog-DevTools:2.1.0"
    const val applog_scheme                 = "com.bytedance.applog:RangersAppLog-All-scheme:6.14.2"
    const val okhttp                        = "com.squareup.okhttp3:okhttp:4.10.0"
    const val markdown                      = "io.noties.markwon:core:${Versions.markdown}"
    const val markdown_linkify              = "io.noties.markwon:linkify:${Versions.markdown}"
    const val markdown_ext_table            = "io.noties.markwon:ext-tables:${Versions.markdown}"
    const val markdown_inline_parser        = "io.noties.markwon:inline-parser:${Versions.markdown}"
    const val markdown_ext_strikethrough    = "io.noties.markwon:ext-strikethrough:${Versions.markdown}"
    const val markdown_syntax_highlight     = "io.noties.markwon:syntax-highlight:${Versions.markdown}"
    const val markdown_image_glide          = "io.noties.markwon:image-glide:${Versions.markdown}"
    const val markdown_ext_tasklist         = "io.noties.markwon:ext-tasklist:${Versions.markdown}"
    const val markdown_prims4j_bundler      = "io.noties:prism4j-bundler:2.0.0"
    const val markdown_table_ext            = "com.atlassian.commonmark:commonmark-ext-gfm-tables:0.13.0"
    const val http_dns                      = "com.aliyun.ams:alicloud-android-httpdns:2.3.0"
    const val pangle                        = "com.pangle.cn:ads-sdk-pro:5.1.0.2"
    const val sensorsdata                   = "com.sensorsdata.analytics.android:SensorsAnalyticsSDK:6.6.1"
    const val realtimeblurview              = "com.github.mmin18:realtimeblurview:1.2.1"
    const val blurview                      = "com.github.Dimezis:BlurView:version-2.0.3"

    @JvmStatic
    val depsMap = Deps::class.java.fields.toList().map {
        it.get(Deps).toString()
    }.filter {
        it.contains(":")
    }.associate { dep ->
        val endIndex = dep.lastIndexOf(":")
        val module = dep.substring(0, endIndex)
        val version = dep.substring(endIndex + 1)
        module to version
    }
}
@Suppress("unused")
object GradlePlugin {
    const val android_gradle_plugin         = "com.android.tools.build:gradle:7.4.2"
    const val kotlin_gradle_plugin          = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    const val huawei_gradle_plugin          = "com.huawei.agconnect:agcp:1.6.0.300"
    const val sensorsdata_gradle_plugin     = "com.sensorsdata.analytics.android:android-gradle-plugin2:3.5.3"
}
// @formatter:on