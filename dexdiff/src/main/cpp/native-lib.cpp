#include <jni.h>
#include <string>

extern "C" {
extern int bspatch_main(int argc, char *argv[]);
}

extern "C"
JNIEXPORT jint JNICALL
Java_com_lingo_dexdiff_BsPatchUtils_patch(JNIEnv *env, jclass clazz,
                                          jstring old_apk,
                                          jstring new_apk,
                                          jstring patch) {
    int argc = 4;
    char *argv[argc];
    argv[0] = "bspatch";
    argv[1] = const_cast<char *>(env->GetStringUTFChars(old_apk, 0));
    argv[2] = const_cast<char *>(env->GetStringUTFChars(new_apk, 0));
    argv[3] = const_cast<char *>(env->GetStringUTFChars(patch, 0));

    int result = bspatch_main(argc, argv);

    env->ReleaseStringUTFChars(old_apk, argv[1]);
    env->ReleaseStringUTFChars(new_apk, argv[2]);
    env->ReleaseStringUTFChars(patch, argv[3]);

    return result;
}