#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_smh_szyproject_test_ndk_NdkTestActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++，我是C里的啊";
    return env->NewStringUTF(hello.c_str());
}
