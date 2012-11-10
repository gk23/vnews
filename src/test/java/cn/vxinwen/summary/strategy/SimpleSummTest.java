package cn.vxinwen.summary.strategy;

import java.nio.charset.Charset;

import org.junit.Test;

import cn.vxinwen.crawling.NewsCrawler;


public class SimpleSummTest {
	private SimpleSumm summ;
	
	@Test
	public void testSummrize()throws Exception{
		summ = new SimpleSumm();
		String url = "http://news.sina.com.cn/c/2012-11-10/051925548414.shtml";
		String content = new NewsCrawler().crawl(url,"gb2312");
		
	}

}
