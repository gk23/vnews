package net.vxinwen.service;

import java.util.List;
import java.util.Map;

import net.vxinwen.bean.News;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class UpdateNewsService {
	/**
	 * 同步策略: 1.返回当前的最新新闻， 2.如果点击更多，同步他上次同步的到今天最新的之间的新闻。
	 * 3.每次每个类别返回<=30条最新的，如果最新的不够30，返回实际条数
	 * 
	 * @return JSON格式的新闻列表
	 */
	public String get(long lastId, String category) {
		// dao query returns Map<category,List<News>>
		// convert to JSON
		return "";
	}
	
	public String convertToJson(Map<String,List<News>> newses){
		String res="{}";
		// TODO 
		return res;
	}

	public static void main(String[] args) {
		JSONObject json = new JSONObject();
		json.put("name", "中国");
		JSONObject provinceHlj = new JSONObject();
		provinceHlj.put("name", "黑龙江");
		JSONArray citiesHlj = new JSONArray();
		citiesHlj.add("哈尔滨");
		citiesHlj.add("齐齐哈尔");
		provinceHlj.put("cities", citiesHlj);

		JSONObject provinceBj = new JSONObject();
		provinceBj.put("name", "北京");
		JSONArray citiesBj = new JSONArray();
		citiesBj.add("朝阳");
		citiesBj.add("海淀");
		provinceBj.put("cities", citiesBj);

		JSONArray cities = new JSONArray();
		cities.add(provinceHlj);
		cities.add(provinceBj);
		json.put("provinces", cities);
		System.out.println(json.toJSONString());

		String result = "{\"name\":\"中国\",\"provinces\":[{\"cities\":[\"哈尔滨\",\"齐齐哈尔\"],\"name\":\"黑龙江\"},{\"cities\":[\"朝阳\",\"海淀\"],\"name\":\"北京\"}]}";
		JSONObject json1 = (JSONObject) JSONValue.parse(result);
		System.out.println(json1.toJSONString());
		System.out.println(json1.get("name"));
		System.out.println(((JSONObject)((JSONArray)json.get("provinces")).get(0)).get("name"));
	}
}
