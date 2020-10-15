package spock

import com.alibaba.fastjson.JSON
import i.am.whp.DemoApplication
import i.am.whp.domain.GetDataParam
import i.am.whp.service.MyService
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringTest extends Specification {

    @Autowired
    MyService myService;

    @Test
    def "getData"() {
        given: "设置请求参数"
        def param = new GetDataParam()
        param.setKeyword("1")
        param.setPageSize(10)
        param.setCurrentPage(1)

        when: "调用service方法"
        def data = myService.getData(param)

        then: "验证返回结果是否符合预期值"
        data != null
        println JSON.toJSONString(data)
    }
}
