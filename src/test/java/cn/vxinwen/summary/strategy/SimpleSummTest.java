package cn.vxinwen.summary.strategy;

import org.junit.Test;

import cn.vxinwen.bean.News;
import cn.vxinwen.crawling.NewsCrawler;


public class SimpleSummTest {
	private SimpleSumm summ;
	
	@Test
	public void testSummrize()throws Exception{
		summ = new SimpleSumm();
		String url = "http://ent.sina.com.cn/s/m/2012-11-14/16543787893.shtml";
//	    String url = "http://sports.sina.com.cn/nba/2012-11-10/13486290723.shtml";
		News news= new NewsCrawler().getSina(url,"gb2312");
        System.out.println(news.getTitle());
        System.out.println(news.getImageAddress());
        System.out.println(news.getSections().length);
        int i=1;
        for(String[] sec:news.getSections()){
            System.out.println("第"+i+++"段：");
            for(String sentence:sec){
                System.out.println(sentence);
            }
        }
        String summary = summ.summarize(news.getSections());
        System.out.println("摘要共"+summary.length()+"个字：\n=================");
		System.out.println(summary);
	}

}
