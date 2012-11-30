package net.vxinwen.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import net.vxinwen.bean.NewsSummaryGen;

/**
 * get the last sync time of generating summary of news in database.
 * 
 * @author Administrator
 *
 */
public class NewsSummaryGenDao extends BaseDao<NewsSummaryGen>{

	@Override
	public NewsSummaryGen getObjectByResult(ResultSet result) {
		NewsSummaryGen nsg = null;
		try {
			nsg = new NewsSummaryGen();
			Timestamp lastTime = result.getTimestamp("last_time");
			nsg.setLastTime(lastTime);
			return nsg;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Timestamp getLastTime(){
		NewsSummaryGen nsg = queryList("select * from news_summary_gen limit 1").get(0);
		return nsg.getLastTime();
	}
	
	public boolean updateLastTime(Timestamp lastTime){
		return execute("update news_summary_gen set last_time='"+lastTime.toString()+"'");
	}
}
