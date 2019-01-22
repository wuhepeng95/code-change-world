package i.am.whp.dao;

import i.am.whp.bean.WhpTest;
import i.am.whp.bean.WhpTest2;

import java.util.List;
import java.util.Map;

/**
 * Created by whp on 2019-01-21
 */
//@Repository
public interface WhpTestDao {

    WhpTest getId(Integer id);

    List<WhpTest2> getall(Map<String, Object> map);

    Integer count(Map<String, Object> map);

    Integer save(WhpTest whpTest);

    Integer update(WhpTest whpTest);

    Integer delete(WhpTest whpTest);
}
