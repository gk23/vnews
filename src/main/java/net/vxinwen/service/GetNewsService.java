package net.vxinwen.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.vxinwen.bean.News;
import net.vxinwen.db.dao.NewsDao;
import net.vxinwen.util.TimestampUtil;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * 为客户端提供新闻获取服务
 * 
 * @author Administrator
 *
 */
public class GetNewsService {
    /**
     * 同步策略: 1.返回当前的最新新闻， 2.如果点击更多，同步他上次同步的到今天最新的之间的新闻。
     * 3.每次每个类别返回<=30条最新的，如果最新的不够30，返回实际条数
     * 
     * @return JSON格式的新闻列表
     */
    public JSONObject get(long[] idslist, String[] tagslist) {
        // dao query returns Map<category,List<News>>
        // convert to JSON
        NewsDao newsDao = new NewsDao();
        JSONObject result = null;
        Map<String, List<News>> newses = null;
        if (idslist.length == tagslist.length) {
            newses = newsDao.getLastNewsBatch(idslist, tagslist);
            result = convertToJson(newses);
        }
        return result;
    }

    /**
     * 格式如下：{cat1:[{news1},{news2}],cat2:[{news1},{news2}]}
     * 
     * @param newses
     * @return
     */
    private JSONObject convertToJson(Map<String, List<News>> newses) {
        String res = "{}";
        Iterator<String> it = newses.keySet().iterator();
        JSONObject json = new JSONObject();
        
        JSONObject newsJson = null;
        while (it.hasNext()) {
            String cat = it.next();
            List<News> newslist = newses.get(cat);
            JSONArray newsJsonArray = new JSONArray();
            for (News news : newslist) {
                newsJson = newsToJson(news);
                if (newsJson != null)
                    newsJsonArray.add(newsJson);
            }
            json.put(cat, newsJsonArray);
        }
        return json;
    }

    @SuppressWarnings("unchecked")
    private JSONObject newsToJson(News news) {
        if (!news.getCategory().equals("段子")&&(news.getSummary() == null || news.getSummary().trim().length() == 0))
            return null;
        JSONObject json = new JSONObject();
        json.put("id", news.getId());
        json.put("title", news.getTitle());
        json.put("category", news.getCategory());
        json.put("url", news.getUrl());
        json.put("publishTime", TimestampUtil.timeStampToString(news.getPublishTime()));
        json.put("image", news.getImage());
        json.put("body", news.getBody());
        json.put("source", news.getSource());
        if(news.getCategory().equals("段子"))
            json.put("summary", news.getBody());
        else
            json.put("summary", news.getSummary());
        return json;
    }

    public static void main(String[] args) {
        // JSONObject json = new JSONObject();
        // json.put("name", "中国");
        // JSONObject provinceHlj = new JSONObject();
        // provinceHlj.put("name", "黑龙江");
        // JSONArray citiesHlj = new JSONArray();
        // citiesHlj.add("哈尔滨");
        // citiesHlj.add("齐齐哈尔");
        // provinceHlj.put("cities", citiesHlj);
        //
        // JSONObject provinceBj = new JSONObject();
        // provinceBj.put("name", "北京");
        // JSONArray citiesBj = new JSONArray();
        // citiesBj.add("朝阳");
        // citiesBj.add("海淀");
        // provinceBj.put("cities", citiesBj);
        //
        // JSONArray cities = new JSONArray();
        // cities.add(provinceHlj);
        // cities.add(provinceBj);
        // json.put("provinces", cities);
        // System.out.println(json.toJSONString());
        //
        // String result =
        // "{\"name\":\"中国\",\"provinces\":[{\"cities\":[\"哈尔滨\",\"齐齐哈尔\"],\"name\":\"黑龙江\"},{\"cities\":[\"朝阳\",\"海淀\"],\"name\":\"北京\"}]}";
        // // String result="{}";
        // JSONObject json1 = (JSONObject) JSONValue.parse(result);
        // System.out.println(json1.toJSONString());
        // System.out.println(json1.get("name"));
        // System.out.println(((JSONObject) ((JSONArray)
        // json.get("provinces")).get(0)).get("name"));

//        String res = "{\"a\":\"a\",\"cate\":[]}";
//        JSONObject json2 = (JSONObject) JSONValue.parse(res);
//        System.out.println(json2);
//        System.out.println(((JSONArray) json2.get("cate")).size());

        String json ="{\"财经\":[]}";
        JSONObject jsonobj =(JSONObject) JSONValue.parse(json);
        Iterator it = jsonobj.keySet().iterator();
        while (it.hasNext()) {
            String category = (String) it.next();
            JSONArray newsArray = (JSONArray) jsonobj.get(category);
            System.out.println(newsArray.size());
            List<News> newsList = new ArrayList<News>(newsArray.size());
            for (int i = 0; i < newsArray.size(); i++) {
                System.out.println(newsList.get(0));
            }
            System.out.println(newsList);
            System.out.println(newsList.size());
        }
        
    }
}
