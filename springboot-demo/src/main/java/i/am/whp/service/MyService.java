package i.am.whp.service;

import i.am.whp.domain.GetDataParam;
import i.am.whp.model.MyTable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MyService<T> {
    T hi();

    List<MyTable> getData(GetDataParam param);

    int getCount(GetDataParam param);

    int updateAndInsert(MyTable param);

}
