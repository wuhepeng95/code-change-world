package pythontest;

import org.python.util.PythonInterpreter;

/**
 * Created by wuhp on 2018/2/23
 */
public class FirstJavaScript {
    public static void main(String args[]) {

        PythonInterpreter interpreter = new PythonInterpreter();
        /*执行语句exec*/
        interpreter.exec("days=('mod','Tue','Wed','Thu','Fri','Sat','Sun'); ");
        interpreter.exec("print days[1];");
    }// main
}