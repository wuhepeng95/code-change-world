package spock;

import spock.lang.Specification
import spock.lang.Unroll

//把每一次调用作为一个单独的测试用例运行
@Unroll
class HelloSpockSpec extends Specification {
    def "length of Spock's and his friends' names"() {
        expect:
        name.size() == length

        where:
        name     | length
        "Spock"  | 5
        "Kirk"   | 3
        "Scotty" | 6
    }
}  
