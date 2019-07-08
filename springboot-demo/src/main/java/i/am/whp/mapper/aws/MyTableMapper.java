package i.am.whp.mapper.aws;

import i.am.whp.model.MyTable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyTableMapper {

    MyTable getById(Integer id);

    List<MyTable> getList(@Param("keyword")String keyword);
}
