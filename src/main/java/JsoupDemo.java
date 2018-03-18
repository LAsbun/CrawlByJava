/*
@author: sws
@file: JsoupDemo.java
@time: 18-3-18
@desc:
*/

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.IOException;
import java.util.HashMap;

public class JsoupDemo {


    final static String ProxyUser = "";
    final static String ProxyPass = "";

    // 代理服务器
    final static String ProxyHost = "http-pro.abuyun.com";
    final static Integer ProxyPort = 9010;

    // 设置IP切换头
    final static String ProxyHeadKey = "Proxy-Switch-Ip";
    final static String ProxyHeadVal = "yes";


    Proxy getProxy(){
        /*
            设置代理 返回制定Proxy
         */
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ProxyUser, ProxyPass.toCharArray());
            }
        });
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyHost, ProxyPort));

        return proxy;
    }


    public Document getDocument(String url, HashMap<String, String> headers) throws Exception{
        Document doc = Jsoup.connect(url).headers(headers).ignoreContentType(true).
                proxy(getProxy())
                .get();
//        log(doc.title());
        return doc;
    }

    public void parseByJsoup(Document doc){
        /*
            使用jsoup css selector 解析
         */
        String doc_source = doc.outerHtml();
//        System.out.println(doc_source);

        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (int i = 0 ; i < 1; ++ i){
//        for (Element headline : newsHeadlines) {
            Element headline = newsHeadlines.get(i);
//            System.out.println(headline.text());
            log("%s\n\t%s",
                    headline.attr("title"), headline.absUrl("href"));
        }
    }

    public static void main(String[] args) {

        JsoupDemo jsoupDemo = new JsoupDemo();
        try {

            HashMap<String, String> header = new HashMap<String, String>();

            header.put("user-agent", "curl/7.35.0");
            header.put("Accept", "*/*");
            header.put("Proxy", "2121213");
            header.put(ProxyHeadKey, ProxyHeadVal);
            Document doc = jsoupDemo.getDocument("http://httpbin.org/get?show_env=1", header);

            System.out.println(doc.outerHtml());

//            jsoupDemo.parseByJsoup(doc);
//
//            XpathDemo xpathDemo = new XpathDemo();
//
//            xpathDemo.parseByXapth(doc.outerHtml());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void log(String msg, String... vals) {
        System.out.println(String.format(msg, vals));
    }
}
