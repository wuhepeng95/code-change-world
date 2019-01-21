package fileiotest;

import java.io.File;

public class ClearMyTarget {

    public static void main(String[] args) {
        String targetPath = "D:" + File.separator + "test";
        String targetDirName = "target";
        scan(targetPath, targetDirName);
    }

    private static void scan(String targetPath, String targetDirName) {
        File file = new File(targetPath);
        //遍历、递归 删除对应文件下的文件
        File[] files = file.listFiles();
        for (File file1 : files) {
            delTargetDir(file1);
        }
    }

    private static void delTargetDir(File file1) {
        File[] files = file1.listFiles();
        for (File file : files) {
            if (file.isFile())
                file.delete();
            else delTargetDir(file);
        }
        file1.delete();
    }
}
