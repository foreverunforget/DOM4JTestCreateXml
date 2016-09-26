package srcdesEntity;

public class SrcDes {
	
	private String doxyfile;
    public String getDoxyfile() {
		return doxyfile;
	}
	public void setDoxyfile(String doxyfile) {
		this.doxyfile = doxyfile;
	}
	private String srcPath;
    private String desXMLPath;
	public String getSrcPath() {
		return srcPath;
	}
	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}
	public String getDesXMLPath() {
		return desXMLPath;
	}
	public void setDesXMLPath(String desXMLPath) {
		this.desXMLPath = desXMLPath;
	}
 public SrcDes(String a,String b,String c)
	{
		srcPath=a;
		desXMLPath=b;
		doxyfile=c;
	}
 public SrcDes()
 {
 }
}
