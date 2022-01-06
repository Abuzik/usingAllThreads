package com.threads;
import com.threads.Mine;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Thread[] threads = new Thread[Runtime.getRuntime().availableProcessors()];
        while (true) {
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(new Runnable()
                {
                    public void run() {
                        Mine mn = null;
                        try {
                            mn = new Mine();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        try {
                            System.out.println(mn.mineBlock(10));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            for (Thread thread : threads) {
                thread.start();
            }
        }
    }
}
