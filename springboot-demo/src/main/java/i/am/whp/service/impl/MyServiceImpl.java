package i.am.whp.service.impl;

import i.am.whp.mapper.aws.MyTableMapper;
import i.am.whp.model.MyTable;
import i.am.whp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MyServiceImpl implements MyService<HashMap<String, String>> {

    @Autowired
    MyTableMapper myTableMapper;

    @Override
    public HashMap<String, String> hi() {
        HashMap<String, String> model = new HashMap<>();
        model.put("code", "200");
        model.put("msg", "hello");
        return model;
    }

    @Override
    public List<MyTable> getData(String keyword) {
        return myTableMapper.getList(keyword);
    }
}
