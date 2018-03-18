/*
@author: sws
@file: HttpClientTutorial.java
@time: 18-3-18
@desc:
*/

//import org.apache.http.*;
//import org.apache.commons.*;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public class HttpClientTutorial {

    public static void main(String[] args) {

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet hg = new HttpGet("https://httpbin.org");
        try {
            CloseableHttpResponse response = client.execute(hg);
            System.out.println(response.getEntity());
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
