package fileiotest;

import java.io.File;

public class FileRename {
    public static void main(String[] args) {
        File filedir = new File("e://test");  // 这里写上发替换的文件夹路??,注意使用双斜??
        String[] files = filedir.list();
        File f;
        String filename;

        for (String file : files) {
            f = new File(filedir, file);
            filename = f.getName();
            //System.out.println(filename);
            /*
             * 这里可以反复使用replace替换,
             * 当然也可以使用正则表达式来替换了
             */
            f.renameTo(new File(filedir.getAbsolutePath() + "//" + filename.replace("原文件名", "新文件名")));
        }
    }
}
