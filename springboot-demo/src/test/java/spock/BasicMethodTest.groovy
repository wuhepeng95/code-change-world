package spock


import i.am.whp.service.impl.MyServiceImpl
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title("测试")
@Unroll
class BasicMethodTest extends Specification {

    def myService = new MyServiceImpl();

    @Unroll
    def "测试基础方法hi"() {

        given:
        "设置请求参数"

        and:
        "myServiceMock.hi()方法"

        def map = myService.hi();

        expect:
        map.size() == 3

    }
}
