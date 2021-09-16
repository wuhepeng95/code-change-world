package i.am.whp.controller;

import com.alibaba.fastjson.JSON;
import i.am.whp.domain.GetDataParam;
import i.am.whp.service.MyService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HelloController {

    @Autowired
    MyService myService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @CrossOrigin
    @RequestMapping("/test")
    @ResponseBody
    public String hello(HttpServletResponse response) {
//        ModelAndView modelAndView = new ModelAndView();

        return JSON.toJSONString(myService.hi());
    }

    /**
     * 重定向
     * @return
     */
    @RequestMapping("/test2")
    public String hello2() {
//        ModelAndView modelAndView = new ModelAndView();
        return "redirect:http://10.215.0.64:8080/test1";
    }

    /**
     * 重定向
     * @return
     */
    @RequestMapping("/test3")
    public String hello3() {
//        ModelAndView modelAndView = new ModelAndView();
        return "forward:/test";
    }


    @RequestMapping(value = "/testList", method = RequestMethod.GET)
    @ResponseBody
    public String testList(@RequestParam(name = "employeeCodes", required = false) String[] employeeCodes) {
        System.out.println(employeeCodes);
//        ModelAndView modelAndView = new ModelAndView();
        return JSON.toJSONString(myService.hi());
    }

    @RequestMapping(value = "/getData", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> getData(GetDataParam param) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("data", myService.getData(param));
        response.put("count", myService.getCount(param));
        return response;
    }

    @RequestMapping(value = "/testRollback", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Map<String, Object> testRollback() {
        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        myService.testRollback();
        return response;
    }
}
