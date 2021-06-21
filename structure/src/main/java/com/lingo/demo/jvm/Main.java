package com.lingo.demo.jvm;

/**
 * -Xms30m -Xmx30m -XX:+UseConcMarkSweepGC -XX:-UseCompressedOops
 * -Xss1m
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Teacher t1 = new Teacher();
        t1.setName("Mark");
        t1.setSex(Teacher.SEX_MAN);
        t1.setAge(36);

        for (int i = 0; i < 15; i++) {
            System.gc();
        }

        Teacher t2 = new Teacher();
        t2.setName("King");
        t2.setSex(Teacher.SEX_MAN);
        t2.setAge(18);

        Thread.sleep(Integer.MAX_VALUE);
    }
}
