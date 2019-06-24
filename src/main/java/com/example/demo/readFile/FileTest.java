package com.example.demo.readFile;

import org.apache.commons.io.IOUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhangdongrun
 * @date 2019/6/20 下午4:13
 */
public class FileTest {

    private static final String ENCODING = "UTF-8";

    private static final int NUM = 50000;

    /**
     * 读取大文件
     *
     * @param filePath
     */
    public void readFile(String filePath) {
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(filePath);
            sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     * 读取文件
     *
     * @param filePath
     */
    public void readFile2(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file), 5 * 1024 * 1024);   //如果是读大文件，设置缓存
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                System.out.println(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static List<String> readLine(File file, String encoding, int index, int num) {
        long start = System.currentTimeMillis();
        List<String> pins = new ArrayList<>();
        LineNumberReader reader = null;
        try {
            reader = new LineNumberReader(new InputStreamReader(new FileInputStream(file), encoding));
//            int lines = 0;
//            while (true) {
//                String pin = reader.readLine();
//                if (StringUtils.isEmpty(pin)) {
//                    break;
//                }
//                if (lines >= index) {
//                    if (!StringUtils.isEmpty(pin)) {
//                        pins.add(pin);
//                    }
//                }
//                if (num == pins.size()) {
//                    break;
//                }
//                lines++;
//            }
            long tt = reader.skip(Long.MAX_VALUE);
            System.out.println(tt);
            String pin = reader.readLine();
            pins.add(pin);
            System.out.println(System.currentTimeMillis() - start);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return pins;
    }

    public static long getSize(File file) {
        FileChannel fc = null;
        long size = -1;
        try {
            if (file.exists() && file.isFile()) {
                FileInputStream fis = new FileInputStream(file);
                fc = fis.getChannel();
                System.out.println(fc.size());
                size = fc.size();
            } else {
                System.out.println("file doesn't exist or is not a file");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
            size = -1;
        } catch (IOException e) {
            System.out.println(e);
            size = -1;
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        return size;
    }

    public static void read(String path) {
        try {
            RandomAccessFile aFile = new RandomAccessFile(path, "rw");
            long size = aFile.length();
            FileChannel inChannel = aFile.getChannel();
            // 缓存使用1K 测试
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = inChannel.read(buf);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void readAndWrite(FileInputStream inf, String path) {
        try {
            FileOutputStream outf = new FileOutputStream("/Users/finup/Documents/learngit/res.log");
            FileChannel inFc = inf.getChannel();
            FileChannel outFc = outf.getChannel();
            // 缓存使用1K 测试
            ByteBuffer buf = ByteBuffer.allocate(1024);
            while (true) {
                buf.clear();
                int t = inFc.read(buf);
                if (t == -1) {
                    break;
                }
                buf.flip();
                outFc.write(buf);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
//        try {
//            String path = "/Users/finup/Documents/learngit/ERROR-qianzhan-core-api.log";
//            File file = new File("/Users/finup/Documents/learngit/ERROR-qianzhan-core-api.log");
//            FileInputStream inf = new FileInputStream(path);
//            System.out.println(getSize(file));
//            readAndWrite(inf, path);
//        }catch (Throwable th){
//            System.out.println(th);
//        }

        // 按指定模式在字符串查找
        String line = "10.1.2.18:8080";
//        String pattern = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}";
        String pattern = "\\w+:";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
        } else {
            System.out.println("NO MATCH");
        }


    }

}
