package net.vxinwen.db.dao;

import java.sql.Timestamp;
import java.util.Calendar;

public class DbUtils {
	public static Calendar convertTime(Timestamp ts){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(ts.getTime());
		return cal;
	}
}
