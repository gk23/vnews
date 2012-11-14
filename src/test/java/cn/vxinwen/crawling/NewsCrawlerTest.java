package cn.vxinwen.crawling;

import org.junit.Test;

import cn.vxinwen.bean.News;

public class NewsCrawlerTest {
	private NewsCrawler crawler = new NewsCrawler();
	@Test
	public void testGetSina() throws Exception{
//		String url = "http://news.sina.com.cn/c/2012-11-10/051925548414.shtml";
		String url = "http://sports.sina.com.cn/nba/2012-11-10/13486290723.shtml";
		String charset = "gb2312";
		News sinanews = crawler.getSina(url, charset);
		System.out.println(sinanews.getTitle());
		System.out.println(sinanews.getImageAddress());
		System.out.println(sinanews.getSections().length);
		int i=1;
		for(String[] sec:sinanews.getSections()){
		    System.out.println("第"+i+++"段：");
			for(String sentence:sec){
			    System.out.println(sentence);
			}
		}
	}
}
