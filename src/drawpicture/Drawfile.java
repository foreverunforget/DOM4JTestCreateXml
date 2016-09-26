package drawpicture;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.python.util.PythonInterpreter;

import com.imooc.entity.FileRelation;
import com.imooc.entity.Indexitem;

import srcdesEntity.DotStart;

public class Drawfile {
	//生成文件关系表的位置
	      public String filerelationtable="F:\\study document\\java document\\java sax dom4j\\dom4j\\DOM4JTestCreateXml";
	//自己生成的xml位置
	   static   public String xmlpath="F:\\study document\\java document\\java sax dom4j\\dom4j\\DOM4JTestCreateXml\\outputxml";
	      String graphname="file";
	      String drawstart="digraph"+" "+graphname+"\n{\n";
	      String drawend="}";
	      String drawresult;
	      String rootpoint;
	      String endpoint;
	   public static ArrayList<FileRelation> FileRelaList = new ArrayList<FileRelation>();
	   public  Map<String,Object> map = new HashMap<String,Object>();
	   //扫描outputxml文件夹下的xml
	      //遍历F:\\study document\\java document\\java sax dom4j\\dom4j\\DOM4JTestCreateXml\\outputxml下除了index.xml的xml
	      public void scan(String path)
	  	{
	  		File file=new File(path);
	  		File[] list=file.listFiles();
	  		String name="index.xml";
	  		for (int i = 0; i < list.length; i++) {
	  			if (!list[i].getName().trim().equals(name)) {
	  				//System.out.println(list[i].getPath());
	  				parse_outputxml_XML(list[i].getPath(),path);
	  			} 
	  		}
	  	}
	      //解析产生的xml中include和includeby关系
	  	public void parse_outputxml_XML(String path,String fullpath){
			// 创建SAXReader的对象reader
					SAXReader reader = new SAXReader();
					try {
						// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
						Document document = reader.read(new File(path));
						// 通过document对象获取根节点bookstore
						Element root = document.getRootElement();
						List<Attribute> rootAttrs=root.attributes();
						for(Attribute attr : rootAttrs){
							if(attr.getName().equals("name"))
							{
								rootpoint=attr.getValue();
							}
			} 
						// 通过element对象的elementIterator方法获取迭代器
						Iterator it = root.elementIterator();
						// 遍历迭代器，获取根节点中的信息（书籍）
/*						
					  命名空间的问题很严重此处没有考虑！！！！	
	*/					
						while (it.hasNext()) {
							Element compound = (Element) it.next();
							if(compound.getName().equals("includes")){
							List<Attribute> fileAttrs=compound.attributes();
									for(Attribute attr : fileAttrs){
										if(attr.getName().equals("local")&&(!attr.getValue().equals("no")))
						{
											FileRelation fr=new FileRelation();
											fr.setStartpoint(rootpoint);
											fr.setEndpoint(compound.getStringValue());
											check(fr,FileRelaList);
											fr=null;
						} 
						 
					}
							}
							if(compound.getName().equals("includedby")){
								List<Attribute> fileAttrs=compound.attributes();
										for(Attribute attr : fileAttrs){
											if(attr.getName().equals("local")&&(!attr.getValue().equals("no")))
							{
												FileRelation fr=new FileRelation();
												fr.setEndpoint(rootpoint);
												fr.setStartpoint(compound.getStringValue());
												check(fr,FileRelaList);
												fr=null;
							} 
							 
						}
								}
									
						}
					}
				catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
	  	/*
	  	 * 检查关系在关系表中是否重复。
	  	        问题：是否会占用内存过大
	  	                    查找耗时是否过大
	  	        解决方案：是否需要统一的放在文本里，在通过工具甄选重复地
	  	*/
	  	void check(FileRelation fr,ArrayList<FileRelation> FileRelaList)
	  	{
	  		boolean flag=true;
	  	   for(FileRelation fr1:FileRelaList){
	  		   if(fr1.equals(fr)){
	  			   flag=false;
	  			   break;
	  		   }
	  	   }	
	  	   if(flag==true){
	  		   FileRelaList.add(fr);
	  	   }
	  	}
	  	/*
	  	 * 将文件名存入map中
	  	 */
	  	void checkMap(ArrayList<FileRelation> FileRelaList){
	  		 int index=1;
             for(FileRelation fr:FileRelaList){
            	 boolean flag1=false;
            	 boolean flag2=false;
            	 Iterator keys = map.keySet().iterator();
          		while(keys.hasNext()){
        			String key = (String)keys.next();
        		if(key.equals(fr.getStartpoint())){
        				flag1=true;
        			}
        		if(key.equals(fr.getEndpoint())){
          			  flag2=true;
          		    }
        		}
          	   if(flag1==false){
          	       map.put(fr.getStartpoint(), index);
          	   index++;
          	   }
          	    if(flag2==false){
          	        map.put(fr.getEndpoint(), index);
          	   index++;
          	   }
             }
             
	  	}
	  	/*
	  	文件关系表
	  	
	  	*/
	  	public void Printfiletable(ArrayList<FileRelation> FileRelaList)
	  	{
	  		  File file;
	  		  FileWriter fw=null;
	  		  BufferedWriter bw=null;
	  		  boolean flag=false;
	  		  try {
	  			File tablelist = new File(filerelationtable+"\\outputtable");
				if(!tablelist.exists())
				{	//tablelist.createNewFile();
				    tablelist.mkdir();
				}
	  		   file = new File(filerelationtable+"\\outputtable\\filerelation.txt");
	  		   // if file doesnt exists, then create it
	  		   if (!file.exists()) {
	  		    file.createNewFile();
	  		   }
	  		   /*
	  		    * 如果filerelation.txt中有内容就清除了
	  		    */
	  		   File f = new File(filerelationtable+"\\outputtable\\filerelation.txt");
	  		   FileWriter fwclear =  new FileWriter(f);
	  		   fwclear.write("");
	  		   fwclear.close();	  		  
	  		   // get the content in bytes
               fw=new FileWriter(filerelationtable+"\\outputtable\\filerelation.txt",true);
               bw=new BufferedWriter(fw);
               bw.write(drawstart);
               /*
                * 
                */
               checkMap(FileRelaList);
               /*
                * 
                */
               Iterator keys = map.keySet().iterator();
       		  while(keys.hasNext()){
       			String key = (String)keys.next();
                 bw.write("node"+map.get(key)+"[label=\""+key+"\"];\n");
       		  }
               for(FileRelation fr:FileRelaList){
            	   bw.write("node"+map.get(fr.getStartpoint())+"->"+"node"+map.get(fr.getEndpoint())+";\n");
               }
                  flag=true;
                  bw.write(drawend);
               //这句话很重要bw->输出流到文件
                  bw.flush();
                  /*
                   * 调用python pydot模块，但是有错误
                   * */
                  String[] args2={"F:\\study document\\java document\\java sax dom4j\\dom4j\\DOM4JTestCreateXml\\outputtable\\filerelation.txt"," "};
                  PythonInterpreter.initialize(null, null, args2);
              	PythonInterpreter interpreter = new PythonInterpreter(); 
              	 InputStream filepy = new FileInputStream("F:\\worksapce\\eclipse\\PyDev\\src\\pydot.py"); 
              	 interpreter.execfile(filepy); ///执行python py文件
              	 filepy.close();
                  System.exit(0);
//                  DotStart dotinit=new DotStart();
                  
	  		  } catch (IOException e) {
	  		   e.printStackTrace();
	  		   flag=false;
	  		  } finally {
	  		   try {
	  		    if (bw!= null) {
	  		     bw.flush();
	  		     bw.close();
	  		    }
	  		    if(fw!=null)
	  		    {
	  		    	fw.close();
	  		    }
	  		   } catch (IOException e) {
	  		    e.printStackTrace();
	  		   }
	  		  }
	  	}
	  	public void listclean(){
			FileRelaList.clear();
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
