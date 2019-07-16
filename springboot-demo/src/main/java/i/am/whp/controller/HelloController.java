package i.am.whp.controller;

import com.alibaba.fastjson.JSON;
import i.am.whp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    MyService myService;

    @RequestMapping("/hi")
    @ResponseBody
    public String hello() {
//        ModelAndView modelAndView = new ModelAndView();
        return JSON.toJSONString(myService.hi());
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List getData(@RequestParam("keyword") String keyword) {
        return myService.getData(keyword);
    }

}
