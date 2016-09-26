package javajoinpython;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.python.util.InterpreterTest;
import org.python.util.PythonInterpreter;

//import org.python;
//测试的例子，在项目中没有实际作用
/*
 * configureFrame.java中line213体现。
 */
public class pythonDoxygen {
	public void jointopy()throws IOException{
		String[] args2={"arg1","arg2"};
		PythonInterpreter.initialize(null, null, args2);
	PythonInterpreter interpreter = new PythonInterpreter(); 
	 InputStream filepy = new FileInputStream("F:\\worksapce\\eclipse\\PyDev\\src\\pytest.py"); 
	 interpreter.execfile(filepy); ///执行python py文件
	 filepy.close();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
        pythonDoxygen doxygen=new pythonDoxygen();
        doxygen.jointopy(); 
	}

}
