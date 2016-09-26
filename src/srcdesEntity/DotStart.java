//生成的result.png太大无法打开
package srcdesEntity;

public class DotStart {
	private String dotinputpath;
   public String getDotinputpath() {
		return dotinputpath;
	}
	public void setDotinputpath(String dotinputpath) {
		this.dotinputpath = dotinputpath;
	}
public DotStart(){
	   Runtime r=Runtime.getRuntime();
	   Process p=null;
		try{
//			String s="E:\\study software\\graphviz\\release\\bin\\dot.exe  E:\\test\\1.dot Tpng -o result.png";
			String s="dot  E:\\test\\1.dot Tpng -o result.png";
//          当前目录都是项目的的目录	即--》F:\study document\java document\java sax dom4j\dom4j\DOM4JTestCreateXml
			String s1="cmd echo %cd%";
			p=r.exec(s); 
		}catch(Exception e){ 
			System.out.println("错误:"+e.getMessage()); 
			e.printStackTrace(); 
		}
   }
}
