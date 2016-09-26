package javajoinpython;

import srcdesEntity.SrcDes;

public class SrcDesDispatcher {  
	  
    private static SrcDesDispatcher instance = new SrcDesDispatcher();  
//    private ArrayList listeners=new ArrayList();  
   private String srcpath;
    private String desxmlpath  ;
   private String doxyfilepath;
    public String getSrcpath() {
	return srcpath;
}

public void setSrcpath(String srcpath) {
	this.srcpath = srcpath;
}

public String getDesxmlpath() {
	return desxmlpath;
}

public void setDesxmlpath(String desxmlpath) {
	this.desxmlpath = desxmlpath;
}

public String getDoxyfilepath() {
	return doxyfilepath;
}

public void setDoxyfilepath(String doxyfilepath) {
	this.doxyfilepath = doxyfilepath;
}

	public static SrcDesDispatcher getInstance() {  
        return instance;  
    }  
  
    private SrcDesDispatcher() {  
          
//        String[] observers = PropertyMgr.getProperty("observers").split(",");  
//        for(String s : observers) {  
//              
//            System.out.println(s);  
//            try {  
//                this.addListener((EntryListener)(Class.forName(s).newInstance()));  
//            } catch (InstantiationException e) {  
//                e.printStackTrace();  
//            } catch (IllegalAccessException e) {  
//                e.printStackTrace();  
//            } catch (ClassNotFoundException e) {  
//                e.printStackTrace();  
//            }  
//        }  
          
  
    }  
  
//    public synchronized void addListener(EntryListener listener) {  
//        System.out.println(":"+listener);  
//        if (listener == null) {  
//            System.out.println(":"+listener);  
//            throw new NullPointerException();  
//        } else {  
//            listeners.add(listener);  
//            return;  
//        }  
//    }  
  
//    public synchronized void removeListener(EntryListener listener) {  
//        listeners.remove(listener);  
//    }  
  
    public void dispatchsrcdeevent(SrcDes srcdessrcdesevent) {  
        // System.out.println("msgDispatchsrcdessrcdesevent");  
         srcpath=srcdessrcdesevent.getSrcPath();  
         desxmlpath=srcdessrcdesevent.getDesXMLPath();  
         doxyfilepath=srcdessrcdesevent.getDoxyfile();  
         System.out.println(srcdessrcdesevent.getSrcPath()+"srcpath");
         System.out.println(srcpath+"srcpath");
        // Profiler.end("msgDispatchsrcdessrcdesevent");  
    }  
}  