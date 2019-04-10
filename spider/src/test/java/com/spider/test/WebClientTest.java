package com.spider.test;

import com.spider.bean.WebClient;
import com.spider.bean.WebClientOld;
import com.spider.bean.WebRequest;
import com.spider.bean.WebResponse;
import com.spider.interceptor.BaseInterceptor;
import com.spider.interfaces.Constants;
import com.spider.test.bean.FirstInterceptor;
import com.spider.test.bean.SecondInterceptor;
import com.spider.test.bean.ThreeInterceptor;
import com.spider.util.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class WebClientTest {
    @Test
    public void test1() throws IOException {
        HttpHost proxyHost = new HttpHost("127.0.0.1", 80);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .setProxy(proxyHost)
                .build();


        String url = "https://www.baidu.com/";
        HttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        System.out.println(get.getConfig());
        HttpResponse response = client.execute(get);

        System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println(response.getStatusLine().getStatusCode());
        HttpPost post = new HttpPost();
//        post
//        System.out.println(IOUtils.parseStream(response.getEntity().getContent(), Charset.defaultCharset().name()));
//        System.out.println(client.execute(get));
    }
    @Test
    public void testSimple() throws IOException {
        String url = "https://www.baidu.com/";
//        url = "https://www.xicidaili.com/nn/";
        WebClient client = WebClient.newClient().init();

        WebRequest request = new WebRequest();
        request.setUrl(url);

        WebResponse response = (WebResponse) client.execute(request);
//        System.out.println(client.getWebContent().getCookie);
//        System.out.println(IOUtils.parseStream(response.getEntity().getContent(), Constants.DEFAULT_CHARSET));
//        System.out.println(IOUtils.parseStream(response.getEntity().getContent(), Constants.DEFAULT_CHARSET));
//        System.out.println(response.getBody());
//        System.out.println(response.getBody());
    }
    @Test
    public void testInterceptor() throws IOException {
        String url = "https://www.baidu.com/";
        BaseInterceptor baseInterceptor1 = new FirstInterceptor();
        BaseInterceptor baseInterceptor2 = new SecondInterceptor();
        BaseInterceptor baseInterceptor3 = new ThreeInterceptor();
        baseInterceptor1.addPreChain(baseInterceptor2);
        baseInterceptor2.addPreChain(baseInterceptor3);
        baseInterceptor1.addPostChain(baseInterceptor2);

        WebClient client = WebClient.newClient().buildInterceptor(baseInterceptor1).init();

        WebRequest request = new WebRequest();
        request.setUrl(url);

        WebResponse response = (WebResponse) client.execute(request);
    }
    public void test(){
        String url = "https://www.baidu.com/";
        WebClientOld client = new WebClientOld().build("60.189.107.215", 9999).init();

        WebRequest request = new WebRequest();
        request.setUrl(url);

        WebResponse response = client.execute(request);
        System.out.println(response.getBody());
    }
}
