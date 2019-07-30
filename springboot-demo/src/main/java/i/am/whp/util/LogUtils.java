package i.am.whp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author wuhepeng
 * @date 2019/7/30
 */
public class LogUtils {
    protected static final Logger logger = LoggerFactory.getLogger(LogUtils.class);


//    public final static String ENV_TYPE = "ENV_TYPE";
//    public final static String HOSTNAME = "HOSTNAME";
//    public final static String IS_CONTAINER = "IS_CONTAINER";
    public final static String GUID = "guid";

    private static final Map<String, String> tags = new HashMap<>();

    static {
        try {
            Properties prop = System.getProperties();

//            tags.put(LogUtils.ENV_TYPE, prop.getProperty(LogUtils.ENV_TYPE, ""));
//            tags.put(LogUtils.HOSTNAME, prop.getProperty(LogUtils.HOSTNAME, ""));
//            tags.put(LogUtils.IS_CONTAINER, prop.getProperty(LogUtils.IS_CONTAINER, ""));
            Properties envProperties = new Properties();
            envProperties.load(LogUtils.class.getClassLoader().getResourceAsStream("env.properties"));
            Set<Map.Entry<Object, Object>> entries = envProperties.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                tags.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
            }
        } catch (Exception ex) {
            throw new RuntimeException("init mdc tags error.", ex);
        }
    }

    public static void initGlobalMDC() {
        for (Map.Entry<String, String> entry : tags.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }
    }

    public static final void setMdcGuid(String guid) {
        MDC.put(GUID, guid);
    }

    public static final void clearMdcGuid() {
        MDC.remove(GUID);
    }
}
