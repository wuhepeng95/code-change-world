package reflecttest.changefieldvalue;

public class TestBean {
    private int id;
    private String url;

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

    @Override
    public String toString() {
        return "reflecttest.changefieldvalue.TestBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}