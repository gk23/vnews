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
}
