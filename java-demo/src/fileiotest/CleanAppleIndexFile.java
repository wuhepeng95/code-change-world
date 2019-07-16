package fileiotest;

import java.io.File;
import java.util.List;

/**
 *递归删除指定文件及文件夹
 */
public class CleanAppleIndexFile {

    //目标文件夹
    public static final String TARGET_PATH = "E:\\";
    //目标文件特征
    public static final String TARGET_NAME = ".";
    //排除文件
//    public static final List<String> EXCEPT_NAME = new A{".gitignore"};

    public static void main(String[] args) {
        scan(TARGET_PATH, TARGET_NAME);
    }

    private static void scan(String targetPath, String targetDirName) {
        File file = new File(targetPath);
        //遍历、递归 删除对应文件下的文件
        File[] files = file.listFiles();
        if (files == null) {
            return;
        }
        for (File file1 : files) {
            scan(file1.getAbsolutePath(), targetDirName);
            if (file1.getName().startsWith(targetDirName)) {
                delTargetDir(file1);
            }
        }
    }

    private static void delTargetDir(File file1) {
        File[] files = file1.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println("文件已删除-----------" + file.getAbsolutePath() + file1.isDirectory());
                    file.delete();
                } else {
                    delTargetDir(file);
                }
            }
        }
        System.out.println("文件已删除----------" + file1.getAbsolutePath() + file1.isDirectory());
        file1.delete();
    }
}
