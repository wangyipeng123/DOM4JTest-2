package com.mingjiang.test;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

/**
 * 利用DOM4J技术解析xml文件：需要导入DOM4J的jar包：技术只能在java下进行解析
 * 在解析xml文件时建议使用这一种解析方式
 */
public class DOM4JTest {

    public static void main(String[] args){
        //创建SAXReader的对象reader
        SAXReader reader = new SAXReader();
        try {
            //通过SAXReader下的read方法加载book.xml文件,获取Document对象
            Document document = reader.read("web/WEB-INF/books.xml");
            //通过Document对象获取根节点的bookStore
            Element bookStore = document.getRootElement();
            //通过element对象的elementIterator方法获取迭代器
            Iterator it = bookStore.elementIterator();
            //遍历迭代器，获取根节点中的信息（书籍）
            while(it.hasNext()){
                //获取迭代器中每一个book,迭代器返回的是Object对象
                Element book = (Element) it.next();
                //获取book属性名以及属性值
                List<Attribute> bookAttrs = book.attributes();
                for (Attribute attr : bookAttrs){
                    System.out.println("属性名："+attr.getName()+"---属性值："+attr.getValue());
                }

                //通过element对象的elementIterator方法获取迭代器
                Iterator itt = book.elementIterator();
                //遍历迭代器，获取book节点中的信息
                while(itt.hasNext()){
                    //获取每一个bookChild
                    Element bookChild = (Element) itt.next();
                    //获取每一个bookChild的节点名
                    System.out.println("节点名："+bookChild.getName()+"---节点值："+bookChild.getTextTrim());
                }



            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
