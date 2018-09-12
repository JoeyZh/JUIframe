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

##忽略警告
-ignorewarnings
-optimizationpasses 5
##【混淆时不会产生形形色色的类名 】
-dontusemixedcaseclassnames
#【指定不去忽略非公共的库类。 】
-dontskipnonpubliclibraryclasses
#【不预校验】
-dontpreverify
#预校验
#-dontoptimize
-verbose
#优化
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
##保证是独立的jar,没有任何项目引用,如果不写就会认为我们所有的代码是无用的,从而把所有的代码压缩掉,导出一个空的jar
#-dontshrink
##保护泛型
#-keepattributes Signature

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#自定义的混淆参数
-keep class com.joey.base.util.**{
    public protected <methods>;
    public static ** valueOf(java.lang.String);
    public static ** valueOf(int);
}
-keep class com.joey.base.util.Permission{
    *;
}

-keep class com.joey.base.util.PermissionManager{
    *;
}

-keep interface com.joey.base.OnLoadingListener{*;}

-keep class com.joey.base.util.DataFormatUtils{
    public static final java.lang.String *;
    public protected <methods>;
}

-keep class com.joey.base.BaseModel{
*;
}


