package cn.vxinwen.crawling;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.client.ClientProtocolException;

public interface Crawler {
	String crawl(String url,String charset) throws ClientProtocolException, IOException;
}
