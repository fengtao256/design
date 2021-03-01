package com.example.springbootdemo.websocket01;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName Test.java
 * @Description TODO
 * @createTime 2020年11月12日 16:36:00
 */
public class Test {
    public static void main(String[] args)
    {
        char myChar = 'g';
        String myStr = Character.toString(myChar);
        System.out.println("String is: "+myStr);
        myStr = String.valueOf(myChar);
        System.out.println("String is: "+myStr);
        String str ="编写系统离线部署文档，在203服务器部署一套当前系统";
        System.out.println(str.substring(0,11));
        System.out.println(str.substring(11,18));
        System.out.println(str.substring(18,26));
        System.out.println(str.split(",").length);
        System.out.println("2020-08-24 19:27:46.529".substring(0,"2020-08-24 19:27:46.529".indexOf(" ")));
        ReadXML1.getObject() ;
        try {
            Class c = Class.forName("com.example.springbootdemo.websocket01.B") ;
            Object o = c.newInstance() ;
            System.out.println(o.getClass().getSimpleName()+" " +o.getClass().getSuperclass().getSimpleName());
            Constructor[] fiels = o.getClass().getDeclaredConstructors() ;
            for (Constructor filed: fiels){
                System.out.println(Modifier.toString( filed.getModifiers()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
class ReadXML1 {
    /**
     *
     该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
     */
    public static Object getObject() {
        try {
            //创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("E:/迅雷下载/config.xml"));
            //获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();
            System.out.println("新类名："+cName);
            //通过类名生成实例对象并将其返回
            Class<?> c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class A{

    static {
        System.out.println("1、A的静态代码块");
    }

    {
        System.out.println("2、A的代码块");
    }

    public A(){
        System.out.println("3、A的构造函数");
    }

}
class B extends A{
    static {
        System.out.println("1、B的静态代码块");
    }

    {
        System.out.println("2、B的代码块");
    }

    public B(){
        System.out.println("3、B的构造函数");
    }
}

