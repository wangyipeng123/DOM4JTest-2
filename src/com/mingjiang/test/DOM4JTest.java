package com.mingjiang.test;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * 利用DOM4J技术解析xml文件：需要导入DOM4J的jar包
 */
public class DOM4JTest {

    /**
     * 利用DOM4j解析xml方法
     */
    private void parserXml(){
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

    /**
     * 生成xml文件
     */
    private void createXML(){
        //创建document对象，代表整个xml文档
        Document document = DocumentHelper.createDocument();
        //创建根节点
        Element rss = document.addElement("rss");
        //向rss节点中添加version属性
        rss.addAttribute("version","2.0");
        //根节点下添加子节点channel
        Element channel = rss.addElement("channel");
        //在channel节点下添加子节点title
        Element title = channel.addElement("title");
        //在title节点下添加文本内容
        title.setText("国内最新新闻");
        //设置生成xml文件的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        //设置编码格式，默认的为utf-8
//        format.setEncoding("GBK");
        //创建一个文件,定义文件名
        File file = new File("rssnews.xml");
        //将生成xml文件，写入到文件中
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream(file),format);
            //设置文件遇到特殊符号是否转义，默认转义为true
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        //创建DOM4JTest对象
        DOM4JTest test = new DOM4JTest();
//        test.parserXml();
        test.createXML();

    }

}
