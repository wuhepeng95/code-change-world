package pythontest;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * Created by wuhp on 2018/2/23
 */
public class Second {
    public static void main(String args[]) {
        /*将python里的函数带入java中*/
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("/Users/whp/workspace-idea/fxiaoke-demo/java-test/src/pythontest/second.py");
        PyFunction func = interpreter.get("adder", PyFunction.class);
        int a = 2010, b = 2;
        PyObject pyobj = func.__call__(new PyInteger(a), new PyInteger(b));
        System.out.println("answer = " + pyobj.toString());
    }
}
