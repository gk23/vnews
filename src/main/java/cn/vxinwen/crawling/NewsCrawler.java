package cn.vxinwen.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.vxinwen.bean.News;

public class NewsCrawler extends AbstractCrawler{
	private String[] websites={"http://news.sina.com.cn","news.163.com","http://v.tom.com/"};
	private String[] charsets={"gb2312","gb2312","utf-8"};
	public News get(String url,String charset) throws ClientProtocolException, IOException {
		String content = crawl(url,charset);
		
		return new News();
	}
	
	public News getSina(String url, String charset) throws ClientProtocolException, IOException{
		String body = crawl(url,charset);
//		System.out.println(body);
        Document doc = Jsoup.parse(body);
        Element mainBody = doc.getElementById("J_Article_Wrap");
//        System.out.println(mainBody.text());
        String title = mainBody.getElementById("artibodyTitle").text();
        News sinaNews = new News();
        sinaNews.setTitle(title);
        
        // 切割正文为段落，保留标签，
        Element content = mainBody.getElementById("artibody");
        Elements ps =  content.getElementsByTag("p");
        List<String> sections = new ArrayList<String>(ps.size());
        for(Element section:ps){
        	sections.add(section.text());
        	sinaNews.setSections(sections);
        }
        
        // 获得图片地址
        Elements divs = mainBody.getElementsByClass("img_wrapper");
        if(divs.size()>=0){
        	Elements imgs= divs.get(0).getElementsByTag("img");
        	if(imgs.size()>=0){
        		sinaNews.setImageAddress(imgs.get(0).attr("src"));
        	}
        }
        return sinaNews;
	}
}
