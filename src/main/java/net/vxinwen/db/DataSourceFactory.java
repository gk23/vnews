package net.vxinwen.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * dataSource工厂,配置属性在properties中,获取jdbc连接
 * 
 */
public class DataSourceFactory {

    private Properties property;
    {   //初始化驱动
        property = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dataSource.properties");
        try {
            property.load(is);
            String driverName = property.getProperty("driverName");
            // JDBC驱动注册
            Class.forName(driverName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取JDBC连接
     * 
     * @return
     */
    public Connection getConnection() {
        String url = property.getProperty("url");
        String userName = property.getProperty("userName");
        String password = property.getProperty("password");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 关闭JDBC连接
     * 
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
