package i.am.whp.lock.zookeeper;

import java.util.concurrent.TimeUnit;

public abstract class Mutex<T> {
    public abstract String getResourcePath();

    public abstract T execute();

    public int getTimeout() {
        return 10;
    }

    public TimeUnit getTimeUnit() {
        return TimeUnit.SECONDS;
    }
}
