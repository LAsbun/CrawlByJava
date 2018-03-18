/*
@author: sws
@file: XpathDemo.java
@time: 18-3-18
@desc:
*/

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;




public class XpathDemo {

    public void parseByXapth(String pageSource) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream input = new ByteArrayInputStream(pageSource.getBytes("UTF-8"));

//        Document doc = builder.parse(input);


//        XPathFactory xft = XPathFactory.newInstance();
//        XPath xpath = xft.newXPath();

        HtmlCleaner hc = new HtmlCleaner();
        TagNode tn = hc.clean(input);

        String xpath_repression = "//tr";

        Object[] ob_list = null;

        ob_list = tn.evaluateXPath(xpath_repression);

        for(int i = 0; i < 1; ++ i){
            System.out.println(i);
            TagNode tna = (TagNode) ob_list[0];
            System.out.println(tna.toString());
        }

//        XPathExpression expr = xpath.compile("//book/title//text() | //book/price//text()");//某个元素下的所有元素
//        Object result = expr.evaluate(doc, XPathConstants.NODESET);
//        NodeList nodes = (NodeList) result;
//        for (int i = 0; i < nodes.getLength(); i++) {
//            Node item = nodes.item(i);
//            System.out.println("  节点名：   " + item.getNodeName() + "  节点值： " + item.getNodeValue());
//        }
    }

    public static void main(String[] args) {

    }
}
