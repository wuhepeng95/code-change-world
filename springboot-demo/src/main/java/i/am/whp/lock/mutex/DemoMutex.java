package i.am.whp.lock.mutex;

import i.am.whp.lock.zookeeper.Mutex;

/**
 * @author wuhepeng
 * @date 2019/10/16
 */
public abstract class DemoMutex<T> extends Mutex<T> {
    private final static String path = "demo1/set/";

    private String id;

    public DemoMutex(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getResourcePath() {
        return path + id;
    }

}
