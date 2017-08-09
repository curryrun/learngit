package com.example.demo.util;

import java.io.*;

/**
 * Created by finup on 2017/7/26.
 */
public class FileUtil {

    private static final String FILE_PATH = "D:/test/";

    public static void saveFile(String fileName, byte[] content) throws IOException {
        String filePath = FILE_PATH + fileName;
        BufferedOutputStream bos = null;
        try {
            File file = new File(filePath);
            //判断文件路径是否存在
            if (!file.getParentFile().exists()) {
                //文件路径不存在时，创建保存文件所需要的路径
                file.getParentFile().mkdirs();
            }
            //创建文件（这是个空文件，用来写入上传过来的文件的内容）
            file.createNewFile();
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(content);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("文件不存在。");
        } finally {
            if (null != bos) {
                bos.close();
            }
        }
    }
}
