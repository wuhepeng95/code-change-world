package i.am.whp.service;

import i.am.whp.domain.GetDataParam;
import i.am.whp.model.MyTable;

import java.io.FileNotFoundException;
import java.util.List;

public interface MyService<T> {
    T hi();

    List<MyTable> getData(GetDataParam param);

    int getCount(GetDataParam param);

    int updateAndInsert(MyTable param);

    List<MyTable> whereTest(MyTable param);

    boolean testRollbackWrapper();

    boolean testRollback();

    boolean hasReturnException() throws FileNotFoundException;

    void voidReturnException() ;
}
