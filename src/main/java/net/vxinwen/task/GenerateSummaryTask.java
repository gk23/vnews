package net.vxinwen.task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.log4j.Logger;

import net.vxinwen.bean.News;
import net.vxinwen.bean.NewsSummaryGen;
import net.vxinwen.db.DataSourceFactory;
import net.vxinwen.db.dao.NewsDao;
import net.vxinwen.db.dao.NewsSummaryGenDao;
import net.vxinwen.preprocess.SinaNewsPreProcess;
import net.vxinwen.summary.SummaryGenerator;

public class GenerateSummaryTask {
	private Logger logger=Logger.getLogger(GenerateSummaryTask.class);

	/**
	 * 将数据库的内容生成摘要的任务
	 */
	public void generate() {
		// 识别哪些还没有做摘要
		NewsSummaryGenDao nsgDao = new NewsSummaryGenDao();
		NewsSummaryGen nsg = nsgDao.queryList(
				"select * from news_summary_gen limit 1").get(0);
		Timestamp lastTime = nsg.getLastTime();
		String newNewsSql = "select * from news where modify_time>='"
				+ lastTime.toString() + "' and summary is null order by modify_time desc";
//		String newNewsSql = "select * from news where id =2";
		NewsDao newsDao = new NewsDao();
		List<News> newsList = newsDao.queryList(newNewsSql);
		String content = null;
		String[][] sections = null;
		SummaryGenerator generator = null;
		if (newsList==null||newsList.size() == 0)
			return;
		lastTime = newsList.get(0).getModifyTime();
		// 更新数据库
		String updateSql ="update news set summary=? where id=?";
		Connection conn = new DataSourceFactory().getConnection();
		boolean isUpdated =false;
		
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
			isUpdated = true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			DataSourceFactory.closeConnection(conn);
		}
		// 更新最新的lasttime到数据库
		if(isUpdated){
			nsgDao.execute("update news_summary_gen set last_time='"+lastTime.toString()+"'");
		}
	}
}
