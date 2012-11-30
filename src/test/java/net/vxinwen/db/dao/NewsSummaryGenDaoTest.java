package net.vxinwen.db.dao;

import java.sql.Timestamp;
import java.util.List;

import junit.framework.Assert;

import net.vxinwen.bean.NewsSummaryGen;

import org.junit.Test;

public class NewsSummaryGenDaoTest {

	@Test
	public void testGetLastTime(){
		NewsSummaryGenDao dao = new NewsSummaryGenDao();
		String url  ="select * from news_summary_gen limit 1";
		List<NewsSummaryGen> list = dao.queryList(url);
		NewsSummaryGen nsg = list.get(0);
		Assert.assertNotNull(nsg.getLastTime());
		System.out.println(nsg.getLastTime());
	}
	
}
