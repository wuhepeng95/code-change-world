package fileiotest;

import java.io.*;

public class CopyFile {
    public static void main(String[] args) throws IOException {
        //新建文件输入流（将管道接到文件上）
        FileInputStream fis = new FileInputStream(new File("D:" + File.separator + "test.wmv"));
        //新建文件输出流（将输出管道接到一个新文件上）
        FileOutputStream fos = new FileOutputStream(new File("D:" + File.separator + "copy.wmv"));//加true会叠加文件

        //使用缓冲流
        BufferedInputStream bif = new BufferedInputStream(fis);
        BufferedOutputStream bof = new BufferedOutputStream(fos);

        long start = System.currentTimeMillis();
        int content;
        byte[] bytes = new byte[5];
        //默认字节为1时，耗时1200ms，5字节时耗时377ms
        while ((content = bif.read(bytes)) != -1) {
            bof.write(bytes, 0, content);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
    }
}
