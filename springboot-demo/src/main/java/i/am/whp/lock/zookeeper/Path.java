package i.am.whp.lock.zookeeper;

import org.springframework.util.StringUtils;

import java.io.File;

public class Path {
    private final static String PATH_SPLIT = "/";

    public static String getDirectoryPath(String path) {
        File file = new File(path);
        return file.getAbsolutePath();
    }

    public static String combine(String path1, String path2) {
        if (StringUtils.isEmpty(path1) && StringUtils.isEmpty(path2))
            throw new RuntimeException("path1 or path2 must be non-empty.");
        else if (StringUtils.isEmpty(path1)) {
            return path2;
        } else if (StringUtils.isEmpty(path2)) {
            return path1;
        } else if (path1.endsWith(PATH_SPLIT) && path2.startsWith(PATH_SPLIT)) {
            return path1 + path2.substring(1);
        } else if (path1.endsWith(PATH_SPLIT) || path2.startsWith(PATH_SPLIT)) {
            return path1 + path2;
        } else {
            return path1 + PATH_SPLIT + path2;
        }
    }
}
