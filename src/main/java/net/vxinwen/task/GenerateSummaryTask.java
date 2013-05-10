package net.vxinwen.task;

import java.sql.Timestamp;
import java.util.List;

import net.vxinwen.bean.News;
import net.vxinwen.db.dao.NewsDao;
import net.vxinwen.db.dao.NewsSummaryGenDao;
import net.vxinwen.preprocess.SinaNewsPreProcess;
import net.vxinwen.summary.strategy.SimpleSumm;

import org.apache.log4j.Logger;

public class GenerateSummaryTask {
	private Logger logger=Logger.getLogger(GenerateSummaryTask.class);

	/**
	 * 将数据库的内容生成摘要的任务
	 */
	public void generate() {
	    logger.debug("==========执行生成摘要任务==========");
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
	
	public static void main(String[] args) {
	    String content="<div class=\"articleContent\" id=\"j_articleContent\">　　证监会日前公布的《中国证监会2012年监管信息公开工作年度报告》提出，证监会将完善监管信息公开有关制度，制定《中国证监会监管信息主动公开工作指引》，进一步明确责任分工，优化信息公开程序。<br/>　　《报告》提出，下一步，证监会还将从两个方面进一步推进监管信息公开工作。一是推进信息公开载体建设，完善证监会网站信息公开栏目，充分发挥证监会网站作为信息公开第一平台的作用。加大新闻发布力度，及时向社会发布市场关注度高、涉及面广的重要新闻。二是加强培训交流，建立信息公开常态化培训交流机制，认真总结交流各部门和派出机构好的经验做法，进一步提高全系统信息公开工作人员的政策水平和业务能力。<br/>　　去年证监会网站共主动公开监管信息7459条，其中证监会机关公开2392条，派出机构公开5067条。去年证监会围绕新股发行制度改革、退市制度改革、上市公司分红、场外市场建设、债券市场发展、机构创新发展、并购重组、稽查执法、投资者保护等资本市场改革发展重点工作，组织新闻通气会70余次，安排会机关部门接受采访60余次。<br/>　　去年证监会在《证券期货市场诚信监督管理暂行办法》、《期货公司资产管理业务试点办法》、《非上市公众公司监督管理办法》、《关于进一步深化新股发行体制改革的指导意见》等27件部门规章和规范性文件的制定过程中，通过新闻发布会和互联网站征求社会公众意见，并根据反馈意见对部门规章和规范性文件进行修改，意见的采纳情况及时向媒体进行披露。(毛建宇)<br/>";
        SinaNewsPreProcess pro = new SinaNewsPreProcess();
        String[][] sec= pro.getSections(content);
        System.out.println(sec.length);
        SimpleSumm summ = new SimpleSumm();
        String result = summ.summarize(sec);
        System.out.println(result);
    }
}
