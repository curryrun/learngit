package com.example.demo.base;

/**
 * @author zhangdongrun
 * @date 2019/1/23 下午4:52
 */
public class VolitaleTest {

    public static boolean stop = false;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!stop) {
//                System.out.println("ReaderThread");
            }
            System.out.println("ReaderThread is stop");
        }
    }

    private static class WriterThread extends Thread {
        public void run() {
            stop = true;
            System.out.println("WriterThread is stop");
        }
    }

    public static void main(String args[]) throws Exception {
        new ReaderThread().start();
        Thread.sleep(2000);
        new WriterThread().start();
    }

}
