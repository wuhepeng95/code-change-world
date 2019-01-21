package pythontest;

import org.python.util.PythonInterpreter;

/**
 * Created by wuhp on 2018/2/23
 */
public class Third {
    public static void main(String args[]) {
        PythonInterpreter interpreter = new PythonInterpreter();
        /*执行文件execfile*/
        interpreter.execfile("/Users/whp/workspace-idea/code-change-world/j2see-demo/src/pythontest/third.py");
    }
}

