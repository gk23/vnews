package cn.vxinwen.crawling;

import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpTools {
    private static Logger logger = Logger.getLogger(HttpTools.class);
    // 支持多线程
    private static HttpClient httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
    static {
        // httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
    }

    public static String getContent(String url,String charset) {
        // 请求的内容
        GetMethod method = null;
        try {
            method = new GetMethod(url);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
            method.getParams().setContentCharset(charset);
            // method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;");
            method.setRequestHeader("Accept-Language", "zh-cn");
            method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
//            method.setRequestHeader("Accept-Encoding", "gzip, deflate");
            // method.setRequestHeader("Keep-Alive", "300");
            // method.setRequestHeader("Connection", "Keep-Alive");
            // method.setRequestHeader("Cache-Control", "no-cache");
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("Method failed: " + method.getStatusLine());
            }
            int length = (int) method.getResponseContentLength();
            if (length <= 0)
                return method.getResponseBodyAsString();
            return method.getResponseBodyAsString(length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return null;
    }

    public static String getContent(String url, Map<String, String> header) {
        // 请求的内容
        GetMethod method = null;
        try {
            method = new GetMethod(url);
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            method.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
            method.setRequestHeader("Accept-Language", "zh-cn");
            method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:14.0) Gecko/20100101 Firefox/14.0.1");
            for (String key : header.keySet()) {
                method.setRequestHeader(key, header.get(key));
            }
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                logger.error("Method failed: " + method.getStatusLine());
            }
            int length = (int) method.getResponseContentLength();
            if (length <= 0)
                return method.getResponseBodyAsString();
            return method.getResponseBodyAsString(length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return null;
    }

}
