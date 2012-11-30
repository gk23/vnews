package net.vxinwen.task;

import java.sql.Timestamp;
import java.util.List;

import net.vxinwen.bean.News;
import net.vxinwen.db.dao.NewsDao;
import net.vxinwen.db.dao.NewsSummaryGenDao;

import org.apache.log4j.Logger;

public class GenerateSummaryTask {
	private Logger logger=Logger.getLogger(GenerateSummaryTask.class);

	/**
	 * 将数据库的内容生成摘要的任务
	 */
	public void generate() {
		// 识别哪些还没有做摘要
		NewsSummaryGenDao nsgDao = new NewsSummaryGenDao();
		Timestamp lastTime = nsgDao.getLastTime();
		NewsDao newsDao = new NewsDao();
		List<News> newsList = newsDao.getNewsToBeSummarized(lastTime);
		if (newsList==null||newsList.size() == 0)
			return;
		lastTime = newsList.get(0).getModifyTime();
		boolean isUpdated = newsDao.update(newsList);
		
		// 更新最新的lasttime到数据库
		if(isUpdated){
			nsgDao.updateLastTime(lastTime);
		}
	}
}
