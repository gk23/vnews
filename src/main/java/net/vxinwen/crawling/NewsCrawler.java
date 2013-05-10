package net.vxinwen.crawling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.vxinwen.bean.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class NewsCrawler extends AbstractCrawler {
    private String[] websites = { "http://news.sina.com.cn", "news.163.com", "http://v.tom.com/" };
    private String[] charsets = { "gb2312", "gb2312", "utf-8" };

    public News get(String url, String charset) {
        String content = crawl(url, charset);
        return new News();
    }

    /**
     * 抽取的news的内容包含sections.length=0的情况，表示没有整篇文章没有包含句号。这样的没有做摘要的必要。
     * 如'第十八届中央委员会委员名单' http://news.sina.com.cn/c/2012-11-14/183925581264.shtml 
     * 
     * @param url
     * @param charset
     * @return news
     */
    public News getSina(String url, String charset) {
        String body = HttpTools.getContent(url, charset);
        // System.out.println(body);
        Document doc = Jsoup.parse(body);
        Element mainBody = doc.getElementById("J_Article_Wrap");
        // System.out.println(mainBody.text());
        String title = mainBody.getElementById("artibodyTitle").text();
        News sinaNews = new News();
        sinaNews.setTitle(title);

        // 切割正文为段落，保留标签，
        Element content = mainBody.getElementById("artibody");
        Elements ps = content.getElementsByTag("p");
        String[][] sections = new String[ps.size()][];
        int offsize = 0;
        StringBuilder sb;
        char c;
        List<String> sentences;
        for (Element section : ps) {
            // 添加每句，以句号或叹号结尾
            String sectionContent = section.text();
            if (sectionContent.contains("。") || sectionContent.contains("！")) {
                sentences = new ArrayList<String>();
                sb = new StringBuilder();
                for (int i = 0; i < sectionContent.length(); i++) {
                    c = sectionContent.charAt(i);
                    if (c != '。' && c != '！') {
                        sb.append(c);
                    } else {
                        sb.append(c);
                        sentences.add(sb.toString().trim().replace("　", ""));
                        sb = new StringBuilder();
                    }
                }
                sections[offsize++] =sentences.toArray(new String[0]);
            }
        }
        // 只保存包含句号的段落，sections[offsize+1]之后的数组都为null，所以舍去
        sinaNews.setSections(Arrays.copyOf(sections, offsize));

        // 获得图片地址
        Elements divs = mainBody.getElementsByClass("img_wrapper");
        if (divs.size() > 0) {
            Elements imgs = divs.get(0).getElementsByTag("img");
            if (imgs.size() > 0) {
                sinaNews.setImage(imgs.get(0).attr("src"));
            }
        }
        return sinaNews;
    }

    public String crawl(String url, String charset) {
        return null;
    }

    public String trim(String content){
        return  content.trim().replace(" ", "");
    }
    public static void main(String[] args) {
        List<String> sentences = new ArrayList<String>();
        sentences.add("1");
        sentences.add("good");
        sentences.add("你好");
        System.out.println(sentences.toArray(new String[0]).length);
    }
}
