#include <jni.h>
#include <string>
//JNIEnv是核心类，它是Java和C的桥梁，是个一级指针
//jobject  这个就是外层的java对象，就是那个类
//jstring这个就是Java方法中传入的参数，是一一对应的
//这是java调用C++代码
extern "C" JNIEXPORT jstring JNICALL
Java_com_smh_szyproject_test_ndk_NdkTestActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++，我是C里的啊";
    return env->NewStringUTF(hello.c_str());
}
//JNIEnv是核心类，它是Java和C的桥梁，是个一级指针
//jobject  这个就是外层的java对象，就是那个类
//jstring这个就是Java方法中传入的参数，是一一对应的
//这是java调用C++代码
extern "C" JNIEXPORT jstring JNICALL
Java_com_smh_szyproject_test_ndk_NdkTestActivity_stringFromJNITwo(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "我是第二个方法";
    return env->NewStringUTF(hello.c_str());
}


//C++回调Java代码
//Java 调用 C++ 的方法要被 native 修饰，表明这是一个本地方法，方法体不需要有任何实现。
//这个jstring  和java文件中的那个返回的String是一个意思

//JNIEnv是核心类，它是Java和C的桥梁，是个一级指针
//jobject  这个就是外层的java对象，就是那个类
//jstring这个就是Java方法中传入的参数，是一一对应的
extern "C" JNIEXPORT jstring JNICALL
Java_com_smh_szyproject_test_ndk_NdkTestActivity_stringFromJNIThree(
        JNIEnv *env,
        jobject thiz,
        jstring text) {
    //获取对象 Class
    jclass clazz = env->GetObjectClass(thiz);
    //获取方法的id
    jmethodID methodId = env->GetMethodID(clazz, "callJavaMethod", "(Ljava/lang/String;)V");
    //去调用java的方法
    env->CallVoidMethod(thiz, methodId, text);
    return env->NewStringUTF("成功调用Java方法");
}




extern "C" JNIEXPORT jstring JNICALL
Java_com_smh_szyproject_test_ndk_NdkTestActivity_stringFromJNIFour(
        JNIEnv *env,
        jclass clazz,
        jstring text) {
    //获取方法的id
    jmethodID jmethodId = env->GetStaticMethodID(clazz, "staticJNIFour", "(Ljava/lang/String;)V");
    //去调用java的方法
    env->CallStaticVoidMethod(clazz, jmethodId, text);
    return env->NewStringUTF("成功调用Java的静态方法");
}


extern "C" JNIEXPORT void JNICALL
Java_com_smh_szyproject_test_ndk_NdkTestActivity_changeJavaField(JNIEnv *env,
                                                                 jobject thiz) {
    //获取对象Class
    jclass clazz = env->GetObjectClass(thiz);
    //获取字段 id
    jfieldID fieldId = env->GetFieldID(clazz,"name","Ljava/lang/String;");
    //获取字段对象
    jstring  name = static_cast<jstring >(env->GetObjectField(thiz,fieldId));
    //修改属性值
    jstring  newValue = env->NewStringUTF("我是新属性值");
    env->SetObjectField(thiz,fieldId,newValue);
}

extern "C" JNIEXPORT void JNICALL
Java_com_smh_szyproject_test_ndk_NdkTestActivity_changeJavaStaticField(JNIEnv *env,
                                                                       jobject thiz) {
    //获取对象Class
    jclass clazz = env->GetObjectClass(thiz);
    //获取静态字段 id
    jfieldID  fieldId = env->GetStaticFieldID(clazz,"sMake", "I");
    //获取静态字段对象
    jint value = env->GetStaticIntField(clazz,fieldId);
    //修改静态属性值
    jint newValue = value=1024;
    env->SetStaticIntField(clazz,fieldId,newValue);
}



