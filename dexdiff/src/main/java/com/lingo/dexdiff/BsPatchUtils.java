package com.lingo.dexdiff;

public class BsPatchUtils {

    static {
        System.loadLibrary("bspatch_utils");
    }

    public static native int patch(String oldApk, String newApk, String patch);
}
