package fileiotest;

import java.io.File;
import java.util.Random;

/**
 * Created by wuhp on 2018/2/27
 */
public class BatchRenameFile {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\wuhp\\AppData\\Local\\Packages\\Microsoft.Windows.ContentDeliveryManager_cw5n1h2txyewy\\LocalState\\Assets");
        File[] listFiles = file.listFiles();
        assert listFiles != null;
        for (File oneFile : listFiles) {
            if (oneFile.length() > 100000) {
                Random random = new Random();
                System.out.println(oneFile.getName() + "重命名....");
                oneFile.renameTo(new File(oneFile.getParent() + File.separator + "windows聚焦壁纸-" + random.nextInt(34567) + ".jpg"));
            }
        }
    }
}
