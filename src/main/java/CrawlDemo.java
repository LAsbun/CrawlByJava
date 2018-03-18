/*
@author: sws
@file: CrawlDemo.java
@time: 18-3-18
@desc:
*/

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.xml.*;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.*;
import javax.xml.parsers.*;
import java.io.IOException;
import org.xml.sax.SAXException;

public class CrawlDemo
{
    static String SendGet(String url)
    {
        // 定义一个字符串用来存储网页内容
        String result = "";
        // 定义一个缓冲字符输入流
        BufferedReader in = null;
        try
        {
            // 将string转成url对象
            URL realUrl = new URL(url);
            // 初始化一个链接到那个url的连接
            URLConnection connection = realUrl.openConnection();
            // 开始实际的连接
            connection.connect();
            // 初始化 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // 用来临时存储抓取到的每一行的数据
            String line;
            while ((line = in.readLine()) != null)
            {
                // 遍历抓取到的每一行并将其存储到result里面
                result += line;
            }
        } catch (Exception e)
        {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally来关闭输入流
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            } catch (Exception e2)
            {
                e2.printStackTrace();
            }
        }
        System.out.println(result);
        return result;
    }

    static String RegexString(String targetStr, String patternStr)
    {
        // 定义一个样式模板，此中使用正则表达式，括号中是要抓的内容
        // 相当于埋好了陷阱匹配的地方就会掉下去
        Pattern pattern = Pattern.compile(patternStr);
        // 定义一个matcher用来做匹配
        Matcher matcher = pattern.matcher(targetStr);
        // 如果找到了
        if (matcher.find())
        {
            // 打印出结果
            return matcher.group(1);
        }
        return "Nothing";
    }


    public static void main(String[] args) throws XPathExpressionException, IOException, SAXException
    {
        // 定义即将访问的链接
//        String url = "https://www.baidu.com";
        String url = "https://httpbin.org";
        // 访问链接并获取页面内容
        String result = SendGet(url);

        // build
        DocumentBuilderFactory builderFactory =
                DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

//        try {
//
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Document document = builder.parse(new ByteArrayInputStream(result.getBytes()));

        System.out.println(document);



        XPath xPath = XPathFactory.newInstance().newXPath();

        String expression = "//*/@href";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        System.out.println(nodeList);
//         使用正则匹配图片的src内容
//        String imgSrc = RegexString(result, "src=(.+?)\\s");
        // 打印结果
//        System.out.println(imgSrc);
    }
}