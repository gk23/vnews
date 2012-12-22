package net.vxinwen.db.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;
import net.vxinwen.db.DataSourceFactory;

import org.junit.Test;

public class DbUtilsTest {

	@Test
	public void testConvertTime(){
		long currtime=System.currentTimeMillis();
		Timestamp ts = new Timestamp(currtime);
		System.out.println(ts);
		Calendar cal = DbUtils.convertTime(ts);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTimeInMillis(currtime);
		Assert.assertEquals(cal,cal2);
		System.out.println(cal.getTimeInMillis());
	}
	
	/**
	 * 测试性能
	 * @throws Exception 
	 */
	@Test
	public void testFetchBigData() throws Exception{
		Connection conn = new DataSourceFactory().getConnection();
		Statement stmt = conn.createStatement();
		long s = System.currentTimeMillis();
		ResultSet rs = stmt.executeQuery("select * from apps");
		rs.last();
		int rowCount = rs.getRow();
		rs.beforeFirst();
		System.out.println(rowCount);
		long e = System.currentTimeMillis();
		System.out.println("query costs "+(e-s)+"ms");
		List list =new ArrayList<Integer>();
		s = System.currentTimeMillis();
		int count = 0;
		while(rs.next()){
			list.add(rs.getLong("id"));
			count++;
		}
		e = System.currentTimeMillis();
		System.out.println("Fetching "+count+" records costs "+(e-s)+"ms");
	}
}
