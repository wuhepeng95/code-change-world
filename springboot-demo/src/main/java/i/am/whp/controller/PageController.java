package i.am.whp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String index1() {
        return "index";
    }

    @RequestMapping("/dialog")
    public String test() {
        return "dialog";
    }
}
