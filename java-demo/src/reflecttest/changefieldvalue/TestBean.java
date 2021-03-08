package reflecttest.changefieldvalue;

import java.util.List;

public class TestBean {
    private int id;
    private String url;

    List<Long> longList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Long> getLongList() {
        return longList;
    }

    public void setLongList(List<Long> longList) {
        this.longList = longList;
    }

    @Override
    public String toString() {
        return "reflecttest.changefieldvalue.TestBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
