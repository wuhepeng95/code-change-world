package i.am.whp.mapper.local;

import i.am.whp.domain.GetDataParam;
import i.am.whp.model.MyTable;

import java.util.List;
import java.util.Map;

public interface MyTableMapper {

    MyTable getById(Integer id);

    List<MyTable> getList(GetDataParam param);

    int getCount(GetDataParam param);

    Map<Integer, Integer> countByStatus();
}
