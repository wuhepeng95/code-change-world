import java.util.ArrayList;
import java.util.List;

/**
 * Created by whp on 2019-01-29
 */
public class TestCheckTreeRecursive {

    //判断自身下面是否有地理信息
    public boolean isMapRefer(String arg) {
        return false;
    }

    //判断自身是否被惠民服务引用
    public boolean isServiceRefer(String arg) {
        return false;
    }

    public int check(String arg) {
        // 1、父级被地理信息引用 2、父级被惠民服务引用 3、父级没被引用，没有子级
        // 4、父级没被引用，有子级，子级被地理信息引用
        // 5、父级没被引用，有子级，子级被惠民服务引用
        // ！！！！！！递归不能实现，因为无法确定具体是哪级有引用！！！！！！！！！

        if (isMapRefer(arg)) {
            //不用继续 父级被地理信息引用
            return 1;
        } else if (isServiceRefer(arg)) {
            //不用继续 父级被惠民服务引用
            return 2;
        } else {
            List<String> argList = new ArrayList<>();
            if (argList.size() > 0) {
                //有子级目录
                for (String argChild : argList) {
                    return check(argChild);
                }
            }
            return 3;
            //没有子级，且父级没被引用
        }

        // FINAL
        // 1、父级或子级被地理信息引用
        // 2、父级或子级被惠民服务引用
        // 3、无引用
    }

}
