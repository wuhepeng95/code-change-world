package fileiotest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GetPathFileToStringTest {
    public static void test() throws IOException {
        URL url = GetPathFileToStringTest.class.getClassLoader().getResource("/fanxintest/Show.class");
        InputStream resource = url.openStream();
        //相当于   InputStream resource = inputStream.getClass().getClassLoader().getResourceAsStream("/fanxintest/Show.class");
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*  @others.IteratorTest
    public void testfilePath() throws Exception {
        String path = this.getClass().getResource("elasticsearch/employeeMapping.json").getFile();
        System.out.println(path);
        System.out.println(FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8));
    }*/
}
