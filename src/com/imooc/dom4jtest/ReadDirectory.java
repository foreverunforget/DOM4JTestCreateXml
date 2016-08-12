package com.imooc.dom4jtest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.imooc.entity.Member;

public class ReadDirectory {
	// 文件所在的层数
	private  int fileLevel;

	/**
	 * 生成输出格式
	 * 
	 * @param name
	 *            输出的文件名或目录名
	 * @param level
	 *            输出的文件名或者目录名所在的层次
	 * @return 输出的字符串
	 */
	public String createPrintStr(String name, int level) {
		// 输出的前缀
		String printStr = "";
		// 按层次进行缩进
		for (int i = 0; i < level; i++) {
			printStr = printStr + "  ";
		}
		printStr = printStr + "- " + name;
		return printStr;
	}

	/**
	 * 输出初始给定的目录
	 * 
	 * @param dirPath
	 *            给定的目录
	 */
	public void printDir(String dirPath) {
		// 将给定的目录进行分割
		String[] dirNameList = dirPath.split("\\\\");
		// 设定文件level的base
		fileLevel = dirNameList.length;

		// 按格式输出
		for (int i = 0; i < dirNameList.length; i++) {
			System.out.println(createPrintStr(dirNameList[i], i));

		}
	}

	// 分析文件类型
	public String analyseType(String dirPath) {
		// 将给定的目录进行分割
		String[] dirNameList = dirPath.split("\\.");
		// 设定文件level的base
		fileLevel = dirNameList.length;
		// 判断类型
		String type1 = "no";
		// if(fileLevel>=0)
		// System.out.println(fileLevel);
		String type = dirNameList[fileLevel - 1].trim();
		if (type.equals("h") || type.equals("c") || type.equals("cpp")) {
			return type;
		}
		// 按格式输出
		// for (int i = 0; i < dirNameList.length; i ++) {
		// System.out.println(createPrintStr(dirNameList[i], i));
		//
		// }

		return type1;
	}

	/**
	 * 输出给定目录下的文件，包括子目录中的文件
	 * 
	 * @param dirPath
	 *            给定的目录
	 */
	public void createXML(String Path) {
		// 1创建createXML
		Document document = DocumentHelper.createDocument();
		// 2创建根节点root
		Element root = document.addElement("root");
		String dirPath = Path;
		root.addAttribute("path", dirPath);
		readFile(dirPath, root);
		// 设置生成xml的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("GBK");
		// 6.生成xml文件
		File file = new File("index.xml");
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
	//设置文件夹总共有50层
	private static int[] intarray=new int[50];

	public void readFile(String dirPath, Element root) {
		File file = new File(dirPath);
		// 取得代表目录中所有文件的File对象数组
		File[] list = file.listFiles();
		// 备份root
		Element store = root;
		// 建立一个member对象
		for (int i = 0; i < list.length; i++) {
			root = store;
			Member member = new Member();
			member.setId(String.valueOf(i+1));//***************************************
			member.setLevel(Integer.toString(fileLevel+1));
			member.setName(list[i].getName());
			// 设置第一层的xml
			Element mem = root.addElement("member");
			mem.addAttribute("level", member.getLevel());
			// 设置id值
			// 字符串转成整形再转换成字符串
			int temp=intarray[Integer.parseInt(member.getLevel())]+Integer.parseInt(member.getId());
			mem.addAttribute("id", Integer.toString(temp));
            //更新intarray數組
			if(i==list.length-1){
			intarray[Integer.parseInt(member.getLevel())]=intarray[Integer.parseInt(member.getLevel())]+Integer.parseInt(member.getId());
			}
			//判断文件类型，是否为文件夹
			if (list[i].isDirectory()) {
				// 设置member的值
				member.setType("directory");
				// 完善第一层属性
				mem.addAttribute("type", member.getType());
				mem.addAttribute("name", member.getName());
				// 更新root
				root = mem;
				fileLevel++;
				// 递归子目录
				readFile(list[i].getPath(), root);
				fileLevel--;
			} else {
				// 设置 （非文件夹 ）文件类型
				String filetype = analyseType(list[i].getName());
				switch (filetype) {
				case "h":
					// 设置member的值
					member.setType("h");
					break;
				case "cpp":
					member.setType("cpp");
					break;
				case "c":
					member.setType("c");
					break;
				default:
					member.setType("file");
				}
				// 设置文件路径
				member.setPath(list[i].getPath());
				// 完善每一层属性
				mem.addText(member.getName());
				mem.addAttribute("type", member.getType());
				mem.addAttribute("path", member.getPath());
				// System.out.println(createPrintStr(list[i].getName(),
				// fileLevel));
			}
			// 清空member;
			member = null;
		}
	}

	public void readFile(String dirPath) {
		// 建立当前目录中文件的File对象
		File file = new File(dirPath);
		// 取得代表目录中所有文件的File对象数组
		File[] list = file.listFiles();
		// 遍历file数组 应该属于深度遍历
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				System.out.println(createPrintStr(list[i].getName(), fileLevel));
				fileLevel++;
				// 递归子目录
				readFile(list[i].getPath());
				fileLevel--;
			} else {
				System.out.println(createPrintStr(list[i].getName(), fileLevel));
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String path = "F:\\daily document";
		// readFile(path);
		ReadDirectory rd = new ReadDirectory();
		rd.createXML(path);
		// rd.printDir(path);
		int i=1;
		System.out.println(intarray[1]);
	}
}
