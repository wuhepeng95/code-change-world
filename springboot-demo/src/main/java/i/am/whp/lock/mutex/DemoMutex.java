package i.am.whp.lock.mutex;

import i.am.whp.lock.zookeeper.Mutex;

/**
 * @author wuhepeng
 * @date 2019/10/16
 */
public abstract class DemoMutex<T> extends Mutex<T> {
    private final static String path = "demo1/set/";

    private Integer userId;

    public DemoMutex(Integer id) {
        this.userId = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String getResourcePath() {
        return path + userId;
    }

}
