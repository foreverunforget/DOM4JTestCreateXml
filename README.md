# DOM4JTestCreateXml
1 根据路径生成了目录结构保存在一个xml中
2 从一个xml文件中提取include内容到自建的xml中
#directory==com.imooc.doo4jtest
readdeirectory.java    将目录结构读入到F:\study document\java document\java sax dom4j\dom4j\DOM4JTestCreateXml\outputxml中outputxml文件夹在scanner_directory.java下建立
#directory==com.test      
scanner_directory.java 将doxygen的文件类型xml文档整理为相应依赖型的xml文档，在F:\study document\java document\java sax dom4j\dom4j\DOM4JTestCreateXml\下新建了outputxml文件夹。
输出的xml文件存放在F:\study document\java document\java sax dom4j\dom4j\DOM4JTestCreateXml\outputxml下
#directory==javajoinpython
启动doxygen，根据配置文件，生成相应的xml
#directory=drawpicture
drawfile.java  文件间的初级依赖关系
#program==PyDev
pytest.py 通过相应的python生成doxygen的启动命令
#drawfile java文件
第156行 fufferedwriter可能会因为文件太大，缓存不够。
存在内存不够的问题 