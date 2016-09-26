package javajoinpython;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;

import org.python.util.PythonInterpreter;

import srcdesEntity.SrcDes;
 
public class ConfigureFrame 
{
	 private  SrcDes srcdestination=new SrcDes();
	 AFrame bframe=new AFrame();
	            
        public static void main( String[] args )
       {    
           JFrame.setDefaultLookAndFeelDecorated(true);
           ConfigureFrame mylogin = new ConfigureFrame( );
        //   mylogin.
           AFrame aframe=new AFrame();
          // SrcDes src=mylogin.printPath();
           aframe.setVisible( true );
           aframe.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );   
           //System.out.println(   "------配置文件路径"+mylogin.srcdes.getDoxyfile());
      }

}
class AFrame extends JFrame implements ActionListener{
	  
	 private  SrcDes srcdes=new SrcDes();
	 //  pythonDoxygen pyth=new pythonDoxygen();
	  // SrcDes srcdes=pyth.srcdes;
	  // private static SrcDes srcdes=new SrcDes();
      private JPanel userJPanel;
      private JLabel doxyfileJLabel,titleJLabel;
      private JButton okJButton,cancelJButton;
      private JLabel nameJLabel,desXMLJLabel,doxygenJLabel;
      private JPasswordField passwordJPasswordField;
           
      JTextField Srctext=new JTextField();
      JTextField Destext=new JTextField();
      JTextField Doxyfiletext=new JTextField();
      //doxygen的启动路径
      //JTextField Doxybintext=new JTextField();
      JButton  srcbut=new JButton("...");
      JButton  desxmlbut=new JButton("...");
      JButton  doxyfilebut=new JButton("...");
//      JButton  DOXYGENbut=new JButton("...");
      JFileChooser jfc = new JFileChooser();// 文件选择器  
      
      //源工程路径 
      private String srccode;
      //doxygen产生的xml的路径名
      private String desxml;
//      doxygen的启动路径
//      private String doxybin;
      private String doxyfile;


   public AFrame()
  {             
     jfc.setCurrentDirectory(new File("f://"));
     createUserInterface(); // 调用创建用户界面方法
  }
      
      //
//  public SrcDes printPath(){
//	   
//	   return srcdes;
//  }
//public void cleanSrcDes(){
//	   
//	    srcdes=null;
//  }
//  
  //
  private void createUserInterface()
  {
     Container contentPane = getContentPane();
     contentPane.setLayout( null );

        userJPanel = new JPanel();
     userJPanel.setBounds( 35, 120, 320, 96 );
     userJPanel.setBorder(BorderFactory.createEtchedBorder() );       //显示一圈边儿
     userJPanel.setLayout( null );
     contentPane.add( userJPanel );



        //图片设置
        titleJLabel=new JLabel("Doxygen配置工具");
        titleJLabel.setBounds(120,50,170,25);
        contentPane.add(titleJLabel);
//------------------------------------------
     nameJLabel=new JLabel("源文件路径：");
        nameJLabel.setBounds(10,12,110,25);
        userJPanel.add(nameJLabel);
        //   nameJComboBox = new JComboBox();
        Srctext.setBounds( 100, 12, 170, 25 );
        userJPanel.add( Srctext);
        //doxygen软件的安装路径
//        doxygenJLabel=new JLabel("doxygen路径：");
//        doxygenJLabel.setBounds(10,68,110,25);
//        userJPanel.add(doxygenJLabel);
        
       Destext.setBounds(100,40,170,25);
        userJPanel.add(Destext);
        
        Doxyfiletext.setBounds(100,68,170,25);
        userJPanel.add(Doxyfiletext);

     desXMLJLabel=new JLabel("生成xml路径：");
     desXMLJLabel.setBounds(10,40,110,25);
     userJPanel.add(desXMLJLabel);
           
     doxyfileJLabel=new JLabel("配置文件路径:");
     doxyfileJLabel.setBounds(10,68,110,25);
     userJPanel.add(doxyfileJLabel);
  //----------------------------------   
     //按钮
     srcbut.setBounds(270, 12, 40, 25);  
     desxmlbut.setBounds(270, 40, 40, 25);  
     doxyfilebut.setBounds(270, 68, 40, 25);  
     srcbut.addActionListener(this); // 添加事件处理  
     desxmlbut.addActionListener(this); //
     doxyfilebut.addActionListener(this); //
     userJPanel.add(srcbut);
     userJPanel.add(desxmlbut);
     userJPanel.add(doxyfilebut);
      
        okJButton=new JButton("确 定");
        okJButton.setBounds(60,250,80,25);
        contentPane.add(okJButton);
        okJButton.addActionListener(
               new ActionListener()
         {
               public void actionPerformed(ActionEvent event)
                {
                      try {
						okJButtonActionPerformed(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }

	
         }
         );

        cancelJButton=new JButton("取  消");
        cancelJButton.setBounds(210,250,80,25);
        contentPane.add(cancelJButton);
        cancelJButton.addActionListener(

        new ActionListener()
        {
           public void actionPerformed( ActionEvent event )
           {
              System.exit(0);        //退出登陆
           }

        }
     );

        setTitle("doxygen配置窗口" );  
     setSize( 380, 350 );
        setResizable( false );           //将最大化按钮设置为不可用
  }

   private void okJButtonActionPerformed( ActionEvent event ) throws IOException
  {
	   
//   	System.out.println("alalalla");
     //okJButton响应事件,检查用户名和密码的匹配
        srccode= Srctext.getText().toString();
        desxml= Destext.getText().toString();
        doxyfile=Doxyfiletext.getText().toString();
        if (srccode.equals("")||srccode==null ){
        JOptionPane.showMessageDialog( this,"源工程目录为空",
       		 "无法执行！", JOptionPane.ERROR_MESSAGE );
        }
        else 
       	 srcdes.setSrcPath(srccode);
        if (desxml.equals("")||desxml==null )
        {
       	 JOptionPane.showMessageDialog( this,"指定的生成xml目录为空",
       			 "无法执行！", JOptionPane.ERROR_MESSAGE );
        }
        else{
        	
        	//警告：不该加入xml
//       	 String temppath=desxml+"\\xml";
       	 String temppath=desxml;
       	 srcdes.setDesXMLPath(temppath);
        }
        if (doxyfile.equals("")||doxyfile==null )
        {
       	 JOptionPane.showMessageDialog( this,"指定的生成doxyfile不存在",
       			 "无法执行！", JOptionPane.ERROR_MESSAGE );
        }
        else{
       	 srcdes.setDoxyfile(doxyfile);
//       	 System.out.println("doxyfile path:"+srcdes.getDoxyfile());
        }
        String[] args2={srcdes.getSrcPath(),srcdes.getDesXMLPath(),srcdes.getDoxyfile()};
        SrcDes srcdesevent = new SrcDes(args2[0], args2[1], args2[2]);  
        SrcDesDispatcher.getInstance().dispatchsrcdeevent(srcdesevent);  
        PythonInterpreter.initialize(null, null, args2);
    	PythonInterpreter interpreter = new PythonInterpreter(); 
    	 InputStream filepy = new FileInputStream("F:\\worksapce\\eclipse\\PyDev\\src\\pytest.py"); 
    	 interpreter.execfile(filepy); ///执行python py文件
    	 filepy.close();
        System.exit(0);
  }





@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource().equals(srcbut)) {// 判断触发方法的按钮是哪个  
       jfc.setFileSelectionMode(1);// 设定只能选择到文件夹  
       int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
       if (state == 1) {  
           return;  
       } else {  
           File f = jfc.getSelectedFile();// f为选择到的目录  
           Srctext.setText(f.getAbsolutePath()); 
       }  
   }  
   // 绑定到选择文件，先择文件事件  
   if (e.getSource().equals(desxmlbut)) {  
       jfc.setFileSelectionMode(1);// 设定只能选择到文件  
       int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
       if (state == 1) {  
           return;// 撤销则返回  
       } else {  
           File f = jfc.getSelectedFile();// f为选择到的文件  
           Destext.setText(f.getAbsolutePath());  
          
       }  
   }  
   if (e.getSource().equals(doxyfilebut)) {  
       jfc.setFileSelectionMode(0);// 设定只能选择到文件  
       int state = jfc.showOpenDialog(null);// 此句是打开文件选择器界面的触发语句  
       if (state == 1) {  
           return;// 撤销则返回  
       } else {  
           File f = jfc.getSelectedFile();// f为选择到的文件  
           Doxyfiletext.setText(f.getAbsolutePath());  
       }  
   }  
   }
	}