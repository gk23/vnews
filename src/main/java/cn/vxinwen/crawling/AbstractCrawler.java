package cn.vxinwen.crawling;


/**
 * 常用方法
 * 
 * @author Administrator
 * 
 */
public abstract class AbstractCrawler implements Crawler {

//	public String crawl(String url,String charset) throws ClientProtocolException, IOException {
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//		HttpGet httpget = new HttpGet(url);
//		String content =null;
//		try {
//			httpget = new HttpGet(url);
//			httpclient = new DefaultHttpClient();
//			HttpResponse response = httpclient.execute(httpget);
//			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
//				content = EntityUtils.toString(response.getEntity(),charset);
//			}			
//			return content;
//		} finally {
//			httpget.releaseConnection();
//		}

//	}
}
