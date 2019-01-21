package fileiotest;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by wuhp on 2018/3/4
 */
public class GetTxtsToOne {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\readme");
        System.out.println(Arrays.toString(file.list()));
        File target = new File("D:\\target.txt");
        if (!target.exists()) {
            System.out.println(target.createNewFile());
        }
        FileWriter fileWriter = new FileWriter(target);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        int read;
        for (String s : Objects.requireNonNull(file.list())) {
            System.out.println(file.getPath() + File.separator + s);
            String filePath = file.getPath() + File.separator + s;
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            while ((read = inputStreamReader.read()) != -1) {
                System.out.print((char) read);
                bufferedWriter.write(read);
            }
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }
    }
}
