package com.spider.bean;

import com.spider.enums.ParamType;
import com.spider.enums.ProxyType;
import com.spider.interceptor.BaseInterceptor;
import com.spider.interceptor.SelftInterceptor;
import com.spider.util.ClientUtils;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WebClient {
    private HttpClient client = null;

    private HttpHost proxy;

    private Integer sotimeout = 3000;

    private Integer connectiontimeout = 10000;

    private Boolean needProxy = true;

    private ResponseHandler responseHandler = new SelfResponseHandler();
    ;

    private RequestConfig.Builder configBuilder = null;

    private HttpClientBuilder builder = null;

    private HttpClientContext webContent = null;

    private Logger logger = LoggerFactory.getLogger(WebClient.class);

    private WebClient() {
        configBuilder = RequestConfig.custom();
        builder = HttpClients.custom();
    }

    public static WebClient newClient() {
        return new WebClient();
    }

    public WebClient buildTimtOut(Integer sotimeout, Integer connectiontimeout) {
        if (!StringUtils.isEmpty(sotimeout)) {
            this.sotimeout = sotimeout;
        }
        if (!StringUtils.isEmpty(connectiontimeout)) {
            this.connectiontimeout = connectiontimeout;
        }
        configBuilder.setSocketTimeout(sotimeout);
        configBuilder.setConnectionRequestTimeout(connectiontimeout);
        return this;
    }

    public WebClient buildProxy(ProxyType proxyType, String proxyHost, int proxyPort) {
        switch (proxyType.getValue()) {
            case "default":
                proxy = getProxy();
                break;
            case "none":
                proxy = null;
                break;
            case "custome":
                proxy = new HttpHost(proxyHost, proxyPort);
                break;
            default:
                proxy = new HttpHost("localhost", 80);
                break;
        }
        configBuilder.setProxy(proxy);
        return this;
    }

    public WebClient buildHandler(ResponseHandler handler) {
        if (handler != null) {
            responseHandler = handler;
        }
        return this;
    }

    public WebClient buildWebContent(Boolean isContent) {
        if (isContent == true) {
            webContent = HttpClientContext.create();
        }
        return this;
    }

    public WebClient buildInterceptor(BaseInterceptor interceptor) {
        if (interceptor != null) {
            SelftInterceptor selftInterceptor = new SelftInterceptor();
            selftInterceptor.setSpiderInterceptor(interceptor);
            builder.addInterceptorLast((HttpRequestInterceptor) selftInterceptor);
            builder.addInterceptorLast((HttpResponseInterceptor) selftInterceptor);
        }
        return this;
    }

    public WebClient init() {

        builder.setDefaultRequestConfig(configBuilder.build());
//        builder.setConnectionManager();
//        builder.setSSLContext();

        this.client = builder.build();
        return this;
    }

    private HttpHost getProxy() {
        if (proxy == null) {
            try {
                String proxyUrl = ClientUtils.getProxyAddress();

                String[] proxys = proxyUrl.split(":");
                logger.info("proxy address:{}, port:{}", proxys[0], proxys[1]);
                if (proxys != null && proxys.length >= 2) {
                    proxy = new HttpHost(proxys[0], Integer.parseInt(proxys[1]));
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return proxy;
    }

    public Object execute(WebRequest request) {
        Object response = null;
        try {
//            webContent = HttpClientContext.create();
            HttpRequestBase method = buildMethod(request);
            buildHeaders(method, request);
            buildMethodParams(method, request);

            response = client.execute(method, responseHandler, webContent);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return response;
    }


    private HttpRequestBase buildMethod(WebRequest request) throws UnsupportedEncodingException, FileNotFoundException {
        HttpRequestBase method = null;
        switch (request.getMethod().getValue()) {
            case "get":
                method = new HttpGet(request.getUrl());
                break;
            case "post":
                method = new HttpPost(request.getUrl());
                dealPostMethodParams(method, request);
                break;
            case "options":
                method = new HttpOptions(request.getUrl());
                break;
            default:
                method = new HttpGet(request.getUrl());
        }

        return method;
    }

    private void dealPostMethodParams(HttpRequestBase method, WebRequest request) throws UnsupportedEncodingException, FileNotFoundException {
        HttpPost postMethod = (HttpPost) method;

        HttpEntity requestEntity = null;
        if (ParamType.STRING.equals(request.getParamType())) {
            StringBuffer params = new StringBuffer();
            for (NameValue nameValue : request.getReqParams()) {
                params.append(nameValue.getName() + "=" + String.valueOf(nameValue.getValue()));
                params.append("&");
            }
            params.deleteCharAt(params.length() - 1);
            requestEntity = new StringEntity(params.toString(), request.getCharset());
        } else if (ParamType.BYTEARRAY.equals(request.getParamType())) {
            StringBuffer params = new StringBuffer();
            for (NameValue nameValue : request.getReqParams()) {
                params.append(nameValue.getName() + "=" + String.valueOf(nameValue.getValue()));
                params.append("&");
            }
            params.deleteCharAt(params.length() - 1);
            requestEntity = new ByteArrayEntity(params.toString().getBytes(request.getCharset()));
        } else if (ParamType.FILE.equals(request.getParamType())) {
            File file = new File(request.getFilePath());
            requestEntity = new FileEntity(file, ContentType.create("text/plain", request.getCharset()));
        } else if (ParamType.UELENCODEFORM.equals(request.getParamType())) {
            List<NameValuePair> formParams = new ArrayList<NameValuePair>();

            for (NameValue nameValue : request.getReqParams()) {
                formParams.add(new BasicNameValuePair(nameValue.getName(), String.valueOf(nameValue.getValue())));
            }
            requestEntity = new UrlEncodedFormEntity(formParams, request.getCharset());
        }

        postMethod.setEntity(requestEntity);
    }

    private void buildHeaders(HttpRequestBase method, WebRequest request) {
        Map<String, String> headers = request.getHeaders();

        if (headers == null || !headers.containsKey("User-Agent")) {
            method.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:63.0) Gecko/20100101 Firefox/63.0");
        }
        if (headers == null || !headers.containsKey("Accept")) {
            method.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        }
        if (headers == null || !headers.containsKey("Accept-Language")) {
            method.addHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        }
        if (headers == null || !headers.containsKey("Accept-Encoding")) {
            method.addHeader("Accept-Encoding", "gzip, deflate, br");
        }
        if (headers == null || !headers.containsKey("Connection")) {
            method.addHeader("Connection", "keep-alive");
        }
        if (headers == null || !headers.containsKey("Pragma")) {
            method.addHeader("Pragma", "no-cache");
        }
        if (headers == null || !headers.containsKey("Cache-Control")) {
            method.addHeader("Cache-Control", "no-cache");
        }

        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                method.addHeader(entry.getKey(), entry.getValue());
            }
        }

        method.addHeader("Cookie", request.getCookie());
    }

    private void buildMethodParams(HttpRequestBase method, WebRequest request) {
        if (!request.getHttpVersion().equals(HttpVersion.HTTP_1_1)) {
            method.getParams().setParameter(HttpMethodParams.PROTOCOL_VERSION, request.getHttpVersion());
        }


        //填充httpMethodParamter参数
        if (request.getMethodParams() != null) {
            for (Map.Entry<String, Object> entry : request.getMethodParams().entrySet()) {
                method.getParams().setParameter(entry.getKey(), entry.getValue());
            }
        }

    }

    public HttpClientContext getWebContent() {
        return webContent;
    }

    public void releaseClient() {
        try {
            ((CloseableHttpClient) client).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
