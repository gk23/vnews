package net.vxinwen.crawling;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.vxinwen.bean.Weibo;

public class HotWeiboCrawler {

    private static String url = "http://hot.weibo.com/?v=9999";
    private static String charset = "utf-8";
    private static String SECTION_PATRN = "(?s)<div class=\"WB_feed_type SW_fun type_intimate feed_list\" action-type=\"feed_list_item\" mid=\".*?\">(.*?)<div class=\"hot_feed_top_tit\">";
    private static String LAST_SECTION_PATRN="(?s)<span class=\"txt_top txt_top_gray\">TOP 10</span>(.*?)<div class=\"W_loading\" node-type=\"lazyload\">";
    private static String ICON_PATRN = "<img src=\"(.*?)\" alt=\"(.*?)\" usercard=.*? class=\"W_face_radius\" /></a>";
    private static String CONTENT_PATRN = "(?s)(<div class=\"WB_text\" node-type=\"feed_list_content\">.*?)<ul class=\"WB_media_list clearfix\" node-type=\"feed_list_media_prev\">";
    private static String IMAGE_PATRN = "<img class=\"bigcursor\" alt=\"\" src=\"(.*?)\" node-type=\"feed_list_media_bgimg\">";
    private static String PUBLISH_TIME_PATRN = "<a class=\"S_func2 WB_time\" href=\".*?\" target=\"_blank\">(.*?)</a>";
    private static String REPOST_COUNT_PATRN = "<a class=\"S_func2\" action-data=\".*?\" node-type=\"normalForward\" action-type=\"feed_list_forward\" href=\".*?\">转发\\((.*?)\\)</a>";
    private static String COMMENT_COUNT_PATRN = "<a class=\"S_func2\" action-type=\"feed_list_comment\" href=\".*?\">评论\\((.*?)\\)</a>";
    /**
     * 热度
     */
    private static String TEMPERATURE_PATRN = "<em id=\"dont\" node-type=\"heat\">(.*?°C)</em>";

    public List<Weibo> crawl() {
        List<Weibo> weibos = new ArrayList<Weibo>();
        String pageSource = HttpTools.getContent(url, charset);
        if (pageSource == null)
            return weibos;
        // 获得每个微博的段落内容
        Matcher secMatcher = Pattern.compile(SECTION_PATRN).matcher(pageSource);
        while (secMatcher.find()) {
            String section = secMatcher.group(1);
            Weibo weibo = getBySection(section);
            weibos.add(weibo);
        }
        // 处理第10条
        secMatcher = Pattern.compile(LAST_SECTION_PATRN).matcher(pageSource);
        if(secMatcher.find()){
            String section = secMatcher.group(1);
            Weibo weibo = getBySection(section);
            weibos.add(weibo);
        }
        return weibos;
    }

    private Weibo getBySection(String section){
        
        Weibo weibo = new Weibo();

        // 获取图标和用户名
        Matcher matcher = Pattern.compile(ICON_PATRN).matcher(section);
        if (matcher.find()) {
            weibo.setIcon(matcher.group(1));
            weibo.setUserName(matcher.group(2));
        }

        // 获取正文
        matcher = Pattern.compile(CONTENT_PATRN).matcher(section);
        if (matcher.find()) {
            weibo.setContent(matcher.group(1));
        }

        // 获取大小图片地址
        matcher = Pattern.compile(IMAGE_PATRN).matcher(section);
        if (matcher.find()) {
            String image = matcher.group(1);
            weibo.setImage(image);
            // 地址之间有差别，一个是thumbnail 一个是bmiddle
            weibo.setBigImage(image.replace("thumbnail", "bmiddle"));
        }
        // 获取时间
        matcher = Pattern.compile(PUBLISH_TIME_PATRN).matcher(section);
        if (matcher.find()) {
            weibo.setPublishTime(matcher.group(1));
        }

        // 获取温度
        matcher = Pattern.compile(TEMPERATURE_PATRN).matcher(section);
        if (matcher.find()) {
            weibo.setTemperature(matcher.group(1));
        }

        // 获取转发、评论数和链接
        matcher = Pattern.compile(REPOST_COUNT_PATRN).matcher(section);
        if (matcher.find()) {
            weibo.setRepostCount(matcher.group(1));
        }
        matcher = Pattern.compile(COMMENT_COUNT_PATRN).matcher(section);
        if (matcher.find()) {
            weibo.setCommentCount(matcher.group(1));
        }
        return weibo;
    }


}
