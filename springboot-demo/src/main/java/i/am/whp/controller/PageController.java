package i.am.whp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/dialog")
    public String dialog() {
        return "dialog";
    }

    @RequestMapping("/expend")
    public String expend() {
        return "expend";
    }

    @RequestMapping("/cascader")
    public String cascader() {
        return "cascader";
    }
}
