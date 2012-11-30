package net.vxinwen.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.vxinwen.bean.News;

public class NewsDao extends BaseDao<News>{
	
	@Override
	public News getObjectByResult(ResultSet result) {
		News news  =new News();
		try {
			news.setId(result.getLong("id"));
			news.setContent(result.getString("body"));
			news.setTitle(result.getString("title"));
			news.setImageAddress(result.getString("image"));
			news.setSummary(result.getString("summary"));
			news.setCategory(result.getString("category"));
			news.setUrl(result.getString("url"));
			news.setPublishTime(result.getTimestamp("publish_time"));
			news.setModifyTime(result.getTimestamp("modify_time"));
			return news;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
