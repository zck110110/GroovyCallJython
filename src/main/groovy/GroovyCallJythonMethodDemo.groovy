import org.python.core.PyFunction
import org.python.core.PyObject
import org.python.core.PyString
import org.python.util.PythonInterpreter
import org.springframework.context.ApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
 * Created by Chikai on 16/9/21.
 */

class GroovyCallJythonMethodDemo {

    public static void main(String[] args)
    {
        //have to read the configuration file first, generate context, whatever whether if use it
        ApplicationContext context = new ClassPathXmlApplicationContext("gconfiguration.xml")

        demoUseSpring(context)

        demoUseJythonMethod(context)

    }

    //Spring bean use demo
    def static demoUseSpring( ApplicationContext context)
    {
        //direct get from spring bean
        Configuration config = context.getBean("democonfig")
        println("user is = " + config.getUser())
        println("password is = " + config.getPassword())

        //other class use ApplicationContextProvider to get bean
        DisplayContextInOtherClassDemo displayContextInOtherClassDemo = new DisplayContextInOtherClassDemo()
        displayContextInOtherClassDemo.displaySpringContextUse()
    }

    // Jython use demo
    def static demoUseJythonMethod(ApplicationContext context)
    {

        /**
         * Here has another issue: if the target python import other lib inside of it?
         *                         this will cause error exception, cuz the jython do not know it
         * Solution: pre-write these related scripts files/ lib into jython path
         * **/

        /**1. import the relevant files into current "python"/"jython" path**/
        // 1. use properties
        Properties properties = new Properties()

        // 2. refer the path to the scripts / libs directory.
        // Note. Unix use ":" -> separate, and Windows use ";" -> separate
        properties.setProperty("python.path", '/Users/Chikai/Mycode/MyGroovy/GroovyCallJython/src')

        //3. insert the path
        String[] argv = {""}
        PythonInterpreter.initialize(System.getProperties(), properties, argv)

        /**read the target jython/python file and use its method**/
        // 1. get interpreter
        PythonInterpreter interpreter = new PythonInterpreter()

        // 2. find the target file
        interpreter.execfile("/Users/Chikai/Mycode/MyGroovy/GroovyCallJython/src/jython/jythonDemo.py")

        // 3. get the method by name (we know the method name right?.....)
        PyFunction func = (PyFunction) interpreter.get("feedback", PyFunction.class)

        // 4. use it.
        PyObject pyObject = func.__call__(new PyString(context.getBean("democonfig").user), new PyString(context.getBean("democonfig").password))
        if (pyObject)
            println("get feedback from jython")
    }
}
