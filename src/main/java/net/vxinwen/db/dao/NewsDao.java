package net.vxinwen.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vxinwen.bean.News;
import net.vxinwen.db.DataSourceFactory;
import net.vxinwen.preprocess.SinaNewsPreProcess;
import net.vxinwen.summary.SummaryGenerator;

public class NewsDao extends BaseDao<News> {

	@Override
	public News getObjectByResult(ResultSet result) {
		News news = new News();
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

	/**
	 * 同步某一个类别的最新新闻，例如体育新闻，category="体育"
	 * 
	 * @param lastId
	 * @param category
	 * @return
	 */
	public List<News> getLastNews(long lastId, String category) {
		if (lastId < 0 || category == null || category.trim().length() == 0)
			return null;
		String sql = "select * from news where id>{lastId} and category={category} order by modify_time desc limit 30";
		sql = sql.replace("{lastId}", String.valueOf(lastId)).replace(
				"{category}", category);
		return queryList(sql);
	}

	/**
	 * 同步多个类别的新闻
	 * 
	 * @param lastIds
	 * @param categories
	 * @return
	 */
	public Map<String, List<News>> getLastNewsBatch(long[] lastIds, String[] categories) {
		String sql = "select * from news where id>? and category=? order by modify_time desc limit 30";
		Connection conn =new DataSourceFactory().getConnection();
		Map<String, List<News>> result = new HashMap<String, List<News>> ();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);

			for(int i=0;i<categories.length;i++){
				ps.setLong(1, lastIds[i]);
				ps.setString(2, categories[i]);
				ResultSet rs = ps.executeQuery();
				// TODO 重写这个方法，分别查询各类后，存入一个Map中 （category:List<News>）
				rs.next()
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 更新摘要
	 * 
	 * @return
	 */
	public boolean update(List<News> newsList) {
		// 更新数据库
		String updateSql = "update news set summary=? where id=?";
		Connection conn = new DataSourceFactory().getConnection();
		String content = null;
		String[][] sections = null;
		SummaryGenerator generator = null;
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(updateSql);
			for (News news : newsList) {
				content = news.getContent();
				sections = SinaNewsPreProcess.getSections(content);
				generator = new SummaryGenerator();
				String summary = generator.summarize(sections);
				ps.setString(1, summary);
				ps.setLong(2, news.getId());
				ps.addBatch();
				ps.clearParameters();
			}
			ps.executeBatch();
			conn.commit();
			conn.setAutoCommit(true);
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			DataSourceFactory.closeConnection(conn);
		}
	}

	/**
	 * 获得所有没有生成摘要的新闻
	 * 
	 * @param lastTime
	 * @return
	 */
	public List<News> getNewsToBeSummarized(Timestamp lastTime) {
		String newNewsSql = "select * from news where modify_time>='"
				+ lastTime.toString()
				+ "' and summary is null order by modify_time desc";
		return queryList(newNewsSql);
	}
}
