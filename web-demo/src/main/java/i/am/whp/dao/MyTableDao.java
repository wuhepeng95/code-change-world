package i.am.whp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import i.am.whp.bean.MyTable;

import java.util.List;
import java.util.Map;

/**
 * Created by whp on 2019-01-21
 */
//@Repository
public interface MyTableDao extends BaseMapper<MyTable> {

    MyTable getId(Integer id);

    List<MyTable> getall(Map<String, Object> map);

    Integer count(Map<String, Object> map);

    Integer save(MyTable whpTest);

    Integer update(MyTable whpTest);

    Integer delete(MyTable whpTest);
}
