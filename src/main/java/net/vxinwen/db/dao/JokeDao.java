package net.vxinwen.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.vxinwen.bean.Joke;
import net.vxinwen.db.DataSourceFactory;


public class JokeDao extends BaseDao<Joke> {
    /**
     * 根据lastId获取最新的搞笑段子
     * 
     * @param lastId
     * @return
     */
    public List<Joke> getLastNewsBatch(long lastId){
        String sqlTemplate = "select * from joke where id>? and body is not null order by publish_time desc limit 30";
        List<Joke> jokes = new ArrayList<Joke>();
        Connection conn = null;
        PreparedStatement stmt;
        try {
            conn = DataSourceFactory.getConnection();
            stmt = conn.prepareStatement(sqlTemplate);
            stmt.setLong(1, lastId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Joke joke = getObjectByResult(rs);
                jokes.add(joke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DataSourceFactory.closeConnection(conn);
        }
        return jokes;
    }

    @Override
    public Joke getObjectByResult(ResultSet result) {
        Joke joke = new Joke();
        try {
            joke.setId(result.getLong("id"));
            joke.setBody(result.getString("body"));
            joke.setTitle(result.getString("title"));
            joke.setImage(result.getString("image"));
            joke.setUrl(result.getString("url"));
            joke.setPublishTime(result.getTimestamp("publish_time"));
            joke.setSource(result.getString("source"));
            return joke;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
