package i.am.whp.mapper.local;

import i.am.whp.domain.GetDataParam;
import i.am.whp.model.MyTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyTableMapper {

    MyTable getById(Integer id);

    List<MyTable> getList(GetDataParam param);

    int getCount(GetDataParam param);
}
