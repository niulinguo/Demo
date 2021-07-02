package com.lingo.dex_parser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class MyClass {

    public static void main(String[] args) throws IOException {
        final ByteBuffer byteBuffer = ByteBuffer.wrap(readFile(getDexFile()));
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

        readMagic(byteBuffer);
        readCheckSum(byteBuffer);
        readSign(byteBuffer);
        readFileSize(byteBuffer);
    }

    private static void readFileSize(ByteBuffer byteBuffer) {
        final int data = byteBuffer.getInt();
        System.out.println("fileSize: " + data);
    }

    private static void readSign(ByteBuffer byteBuffer) {
        final byte[] data = new byte[20];
        byteBuffer.get(data);
        System.out.println("sign: " + Arrays.toString(data));
    }

    private static void readCheckSum(ByteBuffer byteBuffer) {
        final int data = byteBuffer.getInt();
        System.out.println("checkSum: " + data);
    }

    private static void readMagic(ByteBuffer byteBuffer) {
        final byte[] data = new byte[8];
        byteBuffer.get(data);
        System.out.println("magic: " + Arrays.toString(data));
    }

    private static File getDexFile() {
        return new File("./dex_parser/classes.dex");
    }

    private static byte[] readFile(File file) throws IOException {
        try (final FileInputStream fis = new FileInputStream(file);
             final ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            final byte[] buffer = new byte[4096];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toByteArray();
        }
    }
}