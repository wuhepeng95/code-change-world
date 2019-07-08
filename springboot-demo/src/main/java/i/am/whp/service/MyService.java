package i.am.whp.service;

import i.am.whp.model.MyTable;

import java.util.List;

public interface MyService<T> {
    T hi();

    List<MyTable> getData(String keyword);
}
