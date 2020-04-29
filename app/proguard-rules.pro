# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class com.hjq.permissions.** {*;}
-dontwarn com.bqs.**

-keep class com.linkface.** { *; }


-keep class com.umeng.** {*;}

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

-dontwarn com.google.**
-keep class com.google.gson.** {*;}
-keep class com.google.protobuf.** {*;}


-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-keep class com.github.lzyzsd.** {*;}
-dontwarn  com.github.lzyzsd.**

-keep class android.webkit.** {*;}
-dontwarn  android.webkit.**

-keep class com.github.lzyzsd.** {*;}
-dontwarn  com.github.lzyzsd.**

-keep class top.zibin.luban.** {*;}
-dontwarn  top.zibin.luban.**

 -keep class com.smh.szyproject.** {*;}


-keep public class com.smh.szyproject.R$*{
    public static final int *;
 }

-keepclassmembers class * {
    public <init> (org.json.JSONObject);
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

  -keepnames class * implements android.os.Parcelable {
      public static final ** CREATOR;
  }

-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**




-keep  class   android.support.v7.** {*;}
-keep class me.zhouzhuo810.** {*;}
-dontwarn  me.zhouzhuo810.**


#glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-keep class com.ucloud.** { *; }
-keep class merge.tv.danmaku.ijk.media.player.** { *; }
#Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.* { *;}
-dontwarn com.google.gson.**
#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
# 保护注解
-keepattributes *Annotation*
-keep class * extends java.lang.annotation.Annotation {*;}

# 泛型与反射
-keepattributes Signature
-keepattributes EnclosingMethod

#忽略警告
-ignorewarnings
#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}



#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 保留在Activity中的方法参数是view的方法，
# 这样以来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
# 保留继承的
-keep public class * extends android.support.v4.**
-keep public class * extends android.support.v7.**
-keep public class * extends android.support.annotation.**
# 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keep class me.jessyan.autosize.** { *; }
 -keep interface me.jessyan.autosize.** { *; }

# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}





#友盟混淆
-dontwarn com.umeng.**
-dontwarn com.taobao.**
 -dontwarn anet.channel.**
 -dontwarn anetwork.channel.**
 -dontwarn org.android.**
 -dontwarn org.apache.thrift.**
 -dontwarn com.xiaomi.**
 -dontwarn com.huawei.**
 -dontwarn com.meizu.** -keepattributes *Annotation* -keep class com.taobao.** {*;}
 -keep class org.android.** {*;}
 -keep class anet.channel.** {*;}
 -keep class com.umeng.** {*;}
 -keep class com.xiaomi.** {*;}
 -keep class com.huawei.** {*;}
 -keep class com.meizu.** {*;}
 -keep class org.apache.thrift.** {*;}
 -keep class com.alibaba.sdk.android.**{*;}
 -keep class com.ut.**{*;}
 -keep class com.ta.**{*;}
 -keep public class **.R$*{ public static final int *; }



#微信混淆
-dontwarn com.tencent.mm.**

 -keep class com.tencent.mm.**{*;}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
