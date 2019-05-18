package com.wqb.website.supports.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


/**
 * HttpClient工具类，支持简易模式和执行器模式
 *
 * @author Shoven
 * @since 2019-04-06 9:31
 */
public class HttpClientUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final int TIMEOUT = 6;
    private static final int MAX_TOTAL = 200;
    private static final int MAX_PER_ROUTE = 100;

    private static final Charset CHARSET = StandardCharsets.UTF_8;
    private static final String HTTP = "http";
    private static final String HTTPS = "https";

    private static RequestConfig requestConfig;
    private static PoolingHttpClientConnectionManager connectionManager;
    private static SSLConnectionSocketFactory sslConnectionSocketFactory;
    private static RetryHandler retryHandler = new RetryHandler();

    static {
        try {
            // 全部信任 不做身份鉴定
            SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(null, new TrustStrategy() {
                        @Override
                        public boolean isTrusted(X509Certificate[] x509Certificates, String s) {
                            return true;
                        }
                    })
                    .build();

            sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslcontext,
                    new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"},
                    null,
                    NoopHostnameVerifier.INSTANCE);

            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, PlainConnectionSocketFactory.INSTANCE)
                    .register(HTTPS, sslConnectionSocketFactory)
                    .build();

            connectionManager = new PoolingHttpClientConnectionManager(registry);
            connectionManager.setMaxTotal(MAX_TOTAL);
            connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

            requestConfig = RequestConfig.custom()
                    .setSocketTimeout(TIMEOUT * 1000)
                    .setConnectTimeout(TIMEOUT * 1000)
                    .setConnectionRequestTimeout(1000)
                    .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                    .build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(retryHandler)
                .setConnectionManager(connectionManager)
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setConnectionTimeToLive(TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * 普通GET请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return execute(httpGet);
    }

    /**
     * 普通GET请求
     *
     * @param url
     * @param query  POJO 、Map 、String
     * @return
     * @throws IOException
     */
    public static String get(String url, Object query) throws IOException {
        String queryString = getQueryString(query);
        HttpGet httpGet = new HttpGet(queryString == null ? url : url  + queryString);
        return execute(httpGet);
    }

    /**
     * 普通GET请求
     *
     * @param query
     * @param headers
     * @param query POJO 、Map 、String
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> headers, Object query) throws IOException {
        String queryString = getQueryString(query);
        HttpGet httpGet = new HttpGet( query  + queryString);
        httpGet.setHeaders(getHeaders(headers));
        return execute(httpGet);
    }

    /**
     * 普通表单提交POST请求
     *
     * @param url
     * @param payload POJO 、Map
     * @return
     * @throws IOException
     */
    public static String post(String url, Object payload) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(getUrlEncodedBody(payload));
        return execute(httpPost);
    }

    /**
     * 普通表单提交POST请求
     *
     * @param url
     * @param headers
     * @param payload POJO 、Map
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> headers, Object payload) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(getUrlEncodedBody(payload));
        httpPost.setHeaders(getHeaders(headers));
        return execute(httpPost);
    }

    /**
     * 以JSON提交方式POST请求
     *
     * @param url
     * @param payload POJO 、Map
     * @return
     * @throws IOException
     */
    public static String jsonPost(String url, Object payload) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(getJsonBody(payload));
        return execute(httpPost);
    }

    /**
     * 以JSON提交方式POST请求
     *
     * @param url
     * @param headers
     * @param payload POJO 、Map
     * @return
     * @throws IOException
     */
    public static String jsonPost(String url, Map<String, String> headers, Object payload) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(getJsonBody(payload));
        httpPost.setHeaders(getHeaders(headers));
        return execute(httpPost);
    }

    /**
     * 发起http请求
     *
     * @param request
     * @return
     * @throws IOException
     */
    private static String execute(HttpRequestBase request) throws IOException {
        CloseableHttpClient httpClient = getHttpClient();
        // 关闭连接,释放资源
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            // 执行请求
            HttpEntity entity = response.getEntity();
            return entity == null ? null : EntityUtils.toString(entity, CHARSET);
        }
    }

    /**
     * 构建url参数
     *
     * @param payload POJO 、Map
     * @return  格式:key1=value1&key2=value2
     */
    public static String getQueryString(Object payload) {
        if (payload instanceof String) {
            return (String)payload;
        }

        Function<? super Map, String> converter = input -> {
            Map<Object, Object> map = (Map<Object, Object>) input;
            if (MapUtils.isEmpty(map)) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (Map.Entry entry : map.entrySet()) {
                String key = String.valueOf(entry.getKey());
                Object value = entry.getValue();
                if (StringUtils.isBlank(key) || value == null || StringUtils.isBlank(value.toString())) {
                    continue;
                }
                sb.append(i++ == 0 ? "?" : "&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            return sb.toString();
        };

        return convertMapOrPojo(payload, converter);
    }

    /**
     * 获取请求头
     *
     * @param header  POJO 、Map
     * @return
     */
    private static Header[] getHeaders(Object header) {
        Function<? super Map, Header[]> converter = input -> {
            Map<Object, Object> map = (Map<Object, Object>) input;
            if (MapUtils.isEmpty(map)) {
                return null;
            }
            Set<Header> headers  = new HashSet<>();
            for (Map.Entry entry : map.entrySet()) {
                String key = String.valueOf(entry.getKey());
                Object value = entry.getValue();
                if (StringUtils.isBlank(key) || entry.getValue() == null ||
                        StringUtils.isBlank(value.toString())) {
                    continue;
                }
                headers.add(new BasicHeader(key, value.toString()));
            }
            return headers.toArray(new Header[]{});
        };

        return convertMapOrPojo(header, converter);
    }

    /**
     * 构建form表单参数
     *
     * @param payload  POJO 、Map
     * @return
     */
    private static StringEntity getUrlEncodedBody(Object payload) {
        Function<? super Map, StringEntity> converter = input -> {
            Map<Object, Object> map = (Map<Object, Object>) input;
            if (MapUtils.isEmpty(map)) {
                return null;
            }

            List<NameValuePair> forms  = new ArrayList<>();
            for (Map.Entry entry : map.entrySet()) {
                String key = String.valueOf(entry.getKey());
                Object value = entry.getValue();
                if (StringUtils.isBlank(key) || value == null || StringUtils.isBlank(value.toString())) {
                    continue;
                }
                forms.add(new BasicNameValuePair(key, value.toString()));
            }
            return new UrlEncodedFormEntity(forms , CHARSET);
        };

        return convertMapOrPojo(payload, converter);
    }

    /**
     * 使用转换器转换输入成统一的map类型
     *
     * @param input  POJO 、Map
     * @param converter 转换器
     * @param <T>
     * @return
     */
    private static <T> T convertMapOrPojo(Object input, Function<? super Map, T> converter) {
        if (input == null) {
            return null;
        }

        Map mapInput;
        if (input instanceof Map) {
            mapInput = (Map)input;
        } else {
            mapInput = new BeanMap(input);
            mapInput.remove("class");
        }
        return converter.apply(mapInput);
    }

    /**
     * 构建form表单参数
     *
     * @param payload POJO 、Map
     * @return StringEntity
     */
    private static StringEntity getJsonBody(Object payload) {
        return new StringEntity(JSONObject.toJSONString(payload), ContentType.APPLICATION_JSON.withCharset(CHARSET));
    }

    /**
     * http请求重试处理器
     */
    private static class RetryHandler implements HttpRequestRetryHandler {
        @Override
        public boolean retryRequest(IOException e, int executionCount, HttpContext context) {
            if (executionCount >= 5) {
                // Do not retry if over max retry count
                return false;
            }
            if (e instanceof ConnectTimeoutException) {
                // Connection refused
                return false;
            }
            if (e instanceof InterruptedIOException) {
                // Timeout
                return false;
            }
            if (e instanceof UnknownHostException) {
                // Unknown host
                return false;
            }
            if (e instanceof SSLException) {
                // SSL handshake exception
                return false;
            }

            HttpRequest request = HttpClientContext.adapt(context).getRequest();
            // Retry if the request is considered idempotent
            return !(request instanceof HttpEntityEnclosingRequest);
        }
    }

    /**
     * 返回执行器
     *
     * @return HttpClientExecutor
     */
    public static HttpClientExecutor executor() {
        return new HttpClientExecutor();
    }

    /**
     * HttpClient执行器
     */
    public static class HttpClientExecutor {

        private String url;

        private Object payload;

        private List<Header> headers = new ArrayList<>();

        /**
         * 设置url
         *
         * @param url
         * @return
         */
        public HttpClientExecutor setUrl(String url) {
            this.url = url;
            return this;
        }

        /**
         * 设置请求头
         *
         * @param name
         * @param value
         * @return
         */
        public HttpClientExecutor setHeader(String name, String value) {
            this.headers.add(new BasicHeader(name, value));
            return this;
        }

        /**
         * 设置一组http请求头
         *
         * @param headers
         * @return
         */
        public HttpClientExecutor setHeaders(List<Header> headers) {
            this.headers = headers;
            return this;
        }

        /**
         * 设置http请求载荷数据
         *
         * @param payload POJO、Map
         * @return
         */
        public HttpClientExecutor setPayload(Object payload) {
            this.payload = payload;
            return this;
        }

        /**
         * 普通http请求头 chrome浏览器
         *
         * @return
         */
        public HttpClientExecutor withCommonHeaders() {
            HashMap<String, String> commonHeaders = new HashMap<>();
            commonHeaders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
            commonHeaders.put("Accept-Language", "zh-CN,zh;q=0.8");
            commonHeaders.put("Accept-Encoding", "gzip, deflate, br");
            commonHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36");
            Collections.addAll(headers, getHeaders(commonHeaders));
            return this;
        }

        /**
         * 普通GET请求
         *
         * @return
         * @throws IOException
         */
        public String get() throws IOException {
            String str = getQueryString(payload);
            HttpGet request = new HttpGet(url + str);
            return execute(request);
        }

        /**
         * 普通表单提交PUT请求
         *
         * @return
         * @throws IOException
         */
        public String put() throws IOException {
            HttpPut request = new HttpPut(url);
            request.setEntity(getUrlEncodedBody(payload));
            return execute(request);
        }

        /**
         * 普通表单提交POST请求
         *
         * @return
         * @throws IOException
         */
        public String post() throws IOException {
            HttpPost request = new HttpPost(url);
            request.setEntity(getUrlEncodedBody(payload));
            return execute(request);
        }

        /**
         * 以JSON提交方式POST请求
         *
         * @return
         * @throws IOException
         */
        public String jsonPost() throws IOException {
            HttpPost request = new HttpPost(url);
            request.setEntity(getJsonBody(payload));
            return execute(request);
        }

        /**
         * 以JSON提交方式PUT请求
         *
         * @return
         * @throws IOException
         */
        public String jsonPut() throws IOException {
            HttpPut request = new HttpPut(url);
            request.setEntity(getJsonBody(payload));
            return execute(request);
        }

        /**
         * delete请求
         *
         * @return
         * @throws IOException
         */
        public String delete() throws IOException {
            String str = getQueryString(payload);
            HttpDelete request = new HttpDelete(url);
            return execute(request);
        }

        /**
         * 执行http请求
         *
         * @param request
         * @return
         * @throws IOException
         */
        private String execute(HttpRequestBase request) throws IOException {
            if (!headers.isEmpty()) {
                request.setHeaders(headers.toArray(new Header[]{}));
            }
            return HttpClientUtils.execute(request);
        }
    }
}
