package net.vxinwen.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.vxinwen.db.DataSourceFactory;
/**
 * DAO基类实现
 * @author 陈靖
 *
 * @param <T>
 */
public abstract class BaseDao<T> {

    protected DataSourceFactory dataSource = new DataSourceFactory();

    public boolean execute(String sql) {
        Connection conn = this.getConnection();
        int count = 0;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            count = statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceFactory.closeConnection(conn);
        }
        return count > 0;
    }
    
    /**
     * 批处理sql语句集合
     * 
     * @param sql
     * @return
     */
    public boolean executeBatch(String sql,Object... args){
    	Connection conn  = this.getConnection();
    	return false;
    }

    public Connection getConnection() {
        return dataSource.getConnection();
    }

    public List<Map<String, Object>> query(String sql) {
        Connection conn = dataSource.getConnection();
        ResultSet result = null;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            result = statement.executeQuery(sql);

            // 遍历结果集，放入集合中
            while (result.next()) {
                ResultSetMetaData meta = result.getMetaData();
                Map<String, Object> map = new HashMap<String, Object>();
                int columnCount = meta.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = meta.getColumnName(i);
                    map.put(columnName, result.getObject(columnName));
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceFactory.closeConnection(conn);
        }
        return list;
    }

    public List<T> queryList(String sql) {
        Connection conn = dataSource.getConnection();
        ResultSet result = null;
        List<T> list = new ArrayList<T>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            result = statement.executeQuery(sql);

            // 遍历结果集，放入集合中
            while (result.next()) {
                list.add(getObjectByResult(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DataSourceFactory.closeConnection(conn);
        }
        return list;
    }

    /**
     * 封装对象信息(需要由子类实现)
     * 
     * @param result
     * @return
     */
    public abstract T getObjectByResult(ResultSet result);
}
