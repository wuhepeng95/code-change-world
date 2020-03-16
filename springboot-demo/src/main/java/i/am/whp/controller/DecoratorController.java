package i.am.whp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 公共目录，可以放在这里，方便一些，否则每个方法都要加这个目录
 */
@Controller
@RequestMapping("/decorator")
public class DecoratorController {
    //多个模板就加入多个请求处理方法
    @RequestMapping("/decorator")
    public String defaultDecorator() {
        return "/decorator/decorator";
    }
}
