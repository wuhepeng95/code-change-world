package enumtest;

/**
 * 枚举的实质就是 最终静态类
 * Created by wuhp on 2017/11/2.
 */

/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *          佛祖保佑             永无BUG
 */
public class WeekDayClass {

    final static class Monday {
        public final Integer index = 1;//索引
        public final String cn = "星期一";//中文表示
        public final String en = "monday";//英文表示
    }

    final static class Tuesday {
        public final Integer index = 2;//索引
        public final String cn = "星期二";//中文表示
        public final String en = "tuesday";//英文表示
    }

    final static class Wednesday {
        public final Integer index = 3;//索引
        public final String cn = "星期三";//中文表示
        public final String en = "wednesday";//英文表示
    }

    public static void main(String[] args) {
    }

}
