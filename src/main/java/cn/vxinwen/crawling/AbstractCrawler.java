package cn.vxinwen.crawling;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 常用方法
 * 
 * @author Administrator
 * 
 */
public abstract class AbstractCrawler implements Crawler {

	public String crawl(String url,String charset) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		String content =null;
		try {
			httpget = new HttpGet(url);
			httpclient = new DefaultHttpClient();
			HttpResponse response = httpclient.execute(httpget);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				content = EntityUtils.toString(response.getEntity(),charset);
			}			
			return content;
		} finally {
			httpget.releaseConnection();
		}

	}
}
