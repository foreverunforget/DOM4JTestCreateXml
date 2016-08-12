package com.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

//import com.imooc.entity.Book;
import com.imooc.entity.Include;
import com.imooc.entity.Includedby;

public class DOM4JTest {
//	private static ArrayList<Book> bookList = new ArrayList<Book>();
	private static ArrayList<Include> IncludeList = new ArrayList<Include>();
	private static ArrayList<Includedby> IncludedbyList = new ArrayList<Includedby>();
	/**
	 * @param args
	 */
	public void createXML(String Path,String name){
		// 1创建createXML
				Document document = DocumentHelper.createDocument();
				// 2创建根节点root
				Element root = document.addElement("root");
				root.addAttribute("path", Path);
				root.addAttribute("name", name);
				readList(root);
				// 设置生成xml的格式
				OutputFormat format = OutputFormat.createPrettyPrint();
				format.setEncoding("GBK");
				// 6.生成xml文件
				File file = new File(""+name+".xml");
				XMLWriter writer;
				try {
					writer = new XMLWriter(new FileOutputStream(file), format);
					// 设置是否转义，默认值是true，代表转义
					writer.setEscapeText(false);
					writer.write(document);
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	public void readList(Element root){
		     for(Include include:IncludeList)
		     {
		    	 Element included=root.addElement("includes");
		    	 included.addAttribute("path",include.getPath() );
		    	 included.addAttribute("type",include.getType() );
		    	 included.addAttribute("local",include.getLocal() );
		    	 included.addText(include.getName());
		     }
		     for(Includedby includedby:IncludedbyList)
		     {
		    	 Element included=root.addElement("includedby");
		    	 included.addAttribute("path",includedby.getPath() );
		    	 included.addAttribute("type",includedby.getType() );
		    	 included.addAttribute("local",includedby.getLocal() );
		    	 included.addText(includedby.getName());
		     }
	}
	public void parseXML(String path){
		// 创建SAXReader的对象reader
				SAXReader reader = new SAXReader();
				try {
					// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
					Document document = reader.read(new File(path));
					// 通过document对象获取根节点bookstore
					Element doxygen = document.getRootElement();
					// 通过element对象的elementIterator方法获取迭代器
					Iterator it = doxygen.elementIterator();
					// 遍历迭代器，获取根节点中的信息（书籍）
					while (it.hasNext()) {
						Element compounddef = (Element) it.next();
						//
						Iterator itt = compounddef.elementIterator();
						//
						while(itt.hasNext()){
							Element compounddefchild=(Element)itt.next();
							if(compounddefchild.getName().equals("compoundname"))
								wholename=compounddefchild.getStringValue();
					if (compounddefchild.getName() == "includes") {
						//
						Include include=new Include ();
						List<Attribute> includeAttrs=compounddefchild.attributes();
								for(Attribute attr : includeAttrs){
									if(attr.getName().equals("refid"))
									{
										include.setLocal("yes");
										include.setId(attr.getValue());
										//refid函数 
										search_id(attr.getValue(),include);
										break;
									}
									else
									{
										include.setLocal("no");
									    include.setName(compounddefchild.getStringValue());
									}
								}
						    IncludeList.add(include);
                            //						    
						    include=null;
							}
					//被引用的文件
					if (compounddefchild.getName() == "includedby") {
						//
						Includedby includedby=new Includedby ();
						List<Attribute> includeAttrs=compounddefchild.attributes();
						for(Attribute attr : includeAttrs){
							if(attr.getName().equals("refid"))
							{
								includedby.setLocal("yes");
								includedby.setId(attr.getValue());
								//refid函数 
								search_id(attr.getValue(),includedby);
								break;
							}
							else
							{
								includedby.setLocal("no");
								includedby.setName(compounddefchild.getStringValue());
							}
						}
						IncludedbyList.add(includedby);
						//						    
						includedby=null;
					}
						}
					}
				}
			catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	// 切割路径获取 文件类型，文件名字
	void cutpath(String path,Include include){
		// 将给定的目录进行分割
		String[] PathList = path.split("\\/");
		// 设定文件level的base
		int Pathlength = PathList.length;
		//文件名字
		String Filename=PathList[Pathlength-1];
		include.setName(Filename);
		//文件类型
		String[] TypeList=Filename.split("\\.");
		int Filenamelength=TypeList.length;
		String Typename=TypeList[Filenamelength-1];
		include.setType(Typename);
	}
	void cutpath(String path,Includedby includedby){
		// 将给定的目录进行分割
		String[] PathList = path.split("\\/");
		// 设定文件level的base
		int Pathlength = PathList.length;
		//文件名字
		String Filename=PathList[Pathlength-1];
		includedby.setName(Filename);
		//文件类型
		String[] TypeList=Filename.split("\\.");
		int Filenamelength=TypeList.length;
		String Typename=TypeList[Filenamelength-1];
		includedby.setType(Typename);
	}
//	通过refid值找到include.xml文件的路径，类型
	public void search_id(String refid,Include include){
		//得到doxygen产生的对应xml的文件名
		String filepath=wholepath+refid+".xml";
				//  得到源文件的路径
		File file=new File(filepath);
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
			Document document = reader.read(file);
			// 通过document对象获取根节点bookstore
			Element doxygen = document.getRootElement();
			// 通过element对象的elementIterator方法获取迭代器
			Iterator it = doxygen.elementIterator();
			// 遍历迭代器，获取根节点中的信息（书籍）
			while (it.hasNext()) {
				Element compounddef = (Element) it.next();
				//
				Iterator itt = compounddef.elementIterator();
				//
				while(itt.hasNext()){
					Element compounddefchild=(Element)itt.next();
//					if(compounddefchild.getName().equals("compoundname")){
//						include.setName(compounddefchild.getStringValue());
//						IncludeList.add(include);
//						include=null;
//					}
	//寻找源文件的路径
					if (compounddefchild.getName() == "location") {
				List<Attribute> includeAttrs=compounddefchild.attributes();
						for(Attribute attr : includeAttrs){
							if(attr.getName().equals("file"))
							{
								include.setPath(attr.getValue());
								//得到源文件的名字    cutpath();
								cutpath(attr.getValue(),include);
							}
							else
								include.setPath("不存在");
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
	
	public void search_id(String refid,Includedby includedby){
		//得到doxygen产生的对应xml的文件名
		String filepath=wholepath+refid+".xml";
				//  得到源文件的路径
		File file=new File(filepath);
		SAXReader reader = new SAXReader();
		try {
			// 通过reader对象的read方法加载books.xml文件,获取docuemnt对象。
			Document document = reader.read(file);
			// 通过document对象获取根节点bookstore
			Element doxygen = document.getRootElement();
			// 通过element对象的elementIterator方法获取迭代器
			Iterator it = doxygen.elementIterator();
			// 遍历迭代器，获取根节点中的信息（书籍）
			while (it.hasNext()) {
				Element compounddef = (Element) it.next();
				//
				Iterator itt = compounddef.elementIterator();
				//
				while(itt.hasNext()){
					Element compounddefchild=(Element)itt.next();
//					if(compounddefchild.getName().equals("compoundname")){
//						include.setName(compounddefchild.getStringValue());
//						IncludeList.add(include);
//						include=null;
//					}
	//寻找源文件的路径
					if (compounddefchild.getName() == "location") {
				List<Attribute> includeAttrs=compounddefchild.attributes();
						for(Attribute attr : includeAttrs){
							if(attr.getName().equals("file"))
							{
								includedby.setPath(attr.getValue());
								//得到源文件的名字    cutpath();
								cutpath(attr.getValue(),includedby);
							}
							else
								includedby.setPath("不存在");
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
	//--------------------dxoygen生成的xml文件夹的路径
	private  String wholepath="F:\\study document\\代码测试\\test\\out file relation\\xml\\";            
	//doxygen解析源文件，产生相应的xml，本程序对产生的xml解析，得到源文件的真实名字
	private   String wholename;
	//--------------------
	public static void main(String[] args) {
		// 解析books.xml文件
	   DOM4JTest test=new DOM4JTest();
	   String path=test.wholepath+"b_8h.xml";
		test.parseXML(path);
	    test.createXML(path,test.wholename);
//             for(Include include:IncludeList){
//            	 System.out.println("----------开始本次的include----------");
//            	 System.out.println("include"+"名:"+include.getName());
//            	 System.out.println("include"+"类型:"+include.getType());
//            	 System.out.println("include"+"id:"+include.getId());
//            	 System.out.println("include"+"local:"+include.getLocal());
//            	 System.out.println("include"+"路径:"+include.getPath());
//            	 System.out.println("----------结束本次的include----------");
//             }
	}

}
