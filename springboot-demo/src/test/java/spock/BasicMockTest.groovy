package spock

import i.am.whp.domain.GetDataParam
import i.am.whp.mapper.local.MyTableMapper
import i.am.whp.service.impl.MyServiceImpl
import spock.lang.Specification

class BasicMockTest extends Specification {
    def myService = new MyServiceImpl()
    def myTableMapperMock = Mock(MyTableMapper)

    void setup() {
        myService.myTableMapper = myTableMapperMock
    }

    def "getCount mock test"() {
        given: "设置请求参数"
        def param = new GetDataParam();

        and: "mock掉接口返回的count信息"
        myTableMapperMock.getCount(param) >> 1000

//        可以替代下面写法
//        def response = myService.getCount(param)
//        expect:
//        response==1001

        when: "调用service方法"
        def response = myService.getCount(param)

        then: "验证返回结果是否符合预期值"
        response == 1001
    }
}
