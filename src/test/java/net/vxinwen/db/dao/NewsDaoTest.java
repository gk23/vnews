package net.vxinwen.db.dao;

import java.util.List;

import junit.framework.Assert;

import net.vxinwen.bean.News;

import org.junit.Test;


public class NewsDaoTest {
	@Test
	public void testQueryAll(){
		NewsDao newsDao = new NewsDao();
		String sql = "select * from news order by id limit 2";
		List<News> newsList = newsDao.queryList(sql);
		Assert.assertEquals(2, newsList.size());
		News news = newsList.get(0);
		Assert.assertEquals("国内新闻", news.getCategory());
		Assert.assertEquals("http://news.sina.com.cn/c/2012-11-20/161525621272.shtml", news.getUrl());
		Assert.assertNotNull(news.getPublishTime());
		System.out.println(news.getPublishTime().getTime());
		System.out.println(news.getPublishTime());
	}
}
