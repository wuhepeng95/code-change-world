package i.am.whp.service.impl;

import i.am.whp.service.MyService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MyServiceImpl implements MyService<HashMap<String, String>> {

    @Override
    public HashMap<String, String> hi() {
        HashMap<String, String> model = new HashMap<>();
        model.put("code", "200");
        model.put("msg", "hello");
        return model;
    }
}
