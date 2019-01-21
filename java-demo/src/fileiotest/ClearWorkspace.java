package fileiotest;

import java.io.File;

public class ClearWorkspace {

    /**
     * 主方法
     * * @param args
     */
    public static void main(String[] args) {
        String filePath = "D:\\workspace-idea";
        File fileDic = new File(filePath);
        if (fileDic.isDirectory()) {
            System.out.println("begin Scan");
        }
        scan(fileDic);
    }

    /**
     * 扫描文件
     *
     * @param f
     */
    private static void scan(File f) {
        if (f.isDirectory()) {
            if (f.getName().equals("target")) {
                System.out.println(f.getName());
                deleteDir(f);
            }
            File[] fileArray = f.listFiles();
            if (fileArray != null) {
                for (File aFileArray : fileArray) {
                    scan(aFileArray);
                }
            }
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     * deletion fails, the method stops attempting to delete and returns
     * "false".
     */
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();//递归删除目录中的子目录下
            assert children != null;
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
}
