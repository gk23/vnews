package net.vxinwen.db.dao;

import java.sql.Timestamp;
import java.util.Calendar;

import junit.framework.Assert;

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
}
