/*
@author: sws
@software: IntelliJ IDEA
@file: CrawlMoniTHSSpider.java
@time: 3/20/18 4:12 PM
@desc:
*/

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlMoniTHSSpider {

    private String login_url = "http://upass.10jqka.com.cn/login";
    private String stock_price_url = "http://mncg.10jqka.com.cn/cgiwt/delegate/qrystock/";

//    public CrawlMoniTHSSpider(){
//    }

    public Map<String, String> login() throws IOException{

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("origin", "http://upass.10jqka.com.cn");
        map.put("upgrade-insecure-requests", "1");
        map.put("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
        map.put("content-type", "application/x-www-form-urlencoded");
        map.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        map.put("referer", "http://upass.10jqka.com.cn/login?redir=HTTP_REFERER&sign=8fd3cc7c13f0c48fa2ff383a74a7b5b4");
        map.put("accept-encoding", "gzip, deflate");
        map.put("accept-language", "zh-CN,zh;q=0.8,und;q=0.6");
        map.put("cookie", "spversion=20130314; PHPSESSID=vc735kth4mvfjk7t3k6hpstbm5");
        map.put("cache-control", "no-cache");

        Connection.Response res = Jsoup.connect(login_url).data(
                "act", "login_submit",
                "captchaCode", "",
                "longLogin", "",
                "passwd", "RMwRq1lFnJkzIfqyCunoob9Eayz7Rhnm2TJE8jE6Qa175wrE2UJh5zp6rD914XYlKV9UjGT59dd8958522A2Vy/Teqjq9Pbd/YIRP3OsqVpJSjJOu/fAv1lXJNAqWLpcSMj96ONvkTx50KCrAXSKGAYMr4wxkcOnj4r1pCya7F4=",
                "redir", "http://moni.10jqka.com.cn/mncg/index/mncgerror",
                "rsa_version", "default_2",
                "submit", "登　录",
                "uname", "YEeAw/L+6BU45ECA2cn7x2c+tyygbeT8tfCsENBrnUBhJWwd28QCHoznqxs9g9FLEguX9c5D7D6lW8x7STUWl4iedLS+rF78jNDTdfX3/LV4tUaskBYHOnfjQHz11FpNjJdtCkSWBPzJpUkoMxFZJLA5BGlR6b6ON5NSEGBmLcA=")
                .referrer("http://moni.10jqka.com.cn/mncg/index/mncgerror")
                .headers(map)
                .method(Connection.Method.POST)
                .execute();

        System.out.println(res.cookies());
        System.out.println(res.parse().outerHtml());
        return res.cookies();
    }

    public void get_stock_price(String stockId){
        /*
            获取某个特定公司代码的股价
         */
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("accept", "application/json, text/javascript, */*; q=0.01");
        map.put("origin", "http://mncg.10jqka.com.cn");
        map.put("referer", "http://mncg.10jqka.com.cn/cgiwt/index/index");
        map.put("x-requested-with", "XMLHttpRequest");
        map.put("content-type", "application/x-www-form-urlencoded");
        map.put("accept-encoding", "gzip, deflate");
        map.put("accept-language", "zh-CN,zh;q=0.8,und;q=0.6");
        map.put("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");

        
    }

    public void get_stock_price(){
        /*
            缺省的另类实现
         */
        this.get_stock_price("100001");
    }

    public static void main(String[] args) throws IOException{
        CrawlMoniTHSSpider s = new CrawlMoniTHSSpider();
        s.login();
    }
}
