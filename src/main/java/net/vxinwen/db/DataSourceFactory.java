package net.vxinwen.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * dataSource工厂,配置属性在properties中,获取jdbc连接
 * 
 */
public class DataSourceFactory {

    private static BoneCP connectionPool = null;
    private static Properties property;
    static {   //初始化驱动
        long s =System.currentTimeMillis();
        property = new Properties();
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("dataSource.properties");
        try {
            property.load(is);
            String driverName = property.getProperty("driverName");
            // JDBC驱动注册
            Class.forName(driverName);
            BoneCPConfig config = new BoneCPConfig(property);
            connectionPool = new BoneCP(config);  
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        long e =System.currentTimeMillis();
        System.out.println("建立数据库连接池话费："+(e-s)+"ms");
    }

    /**
     * 使用BoneCP连接池获取JDBC连接
     * 
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionPool.getConnection();
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
    
    public static void init(){
        System.out.println("已创建数据库连接数为"+connectionPool.getTotalCreatedConnections());
        System.out.println("可用连接数为"+connectionPool.getTotalFree());
    }
    public static void main(String[] args) throws SQLException {
        init();
        Connection conn = DataSourceFactory.getConnection();

        Statement stat  =conn.createStatement();
        ResultSet rs = stat.executeQuery("select count(*) from news");
        if(rs.next()){
            System.out.println(rs.getInt(1));
        }
        DataSourceFactory.closeConnection(conn);
        System.out.println("已创建数据库连接数为"+connectionPool.getTotalCreatedConnections());
        System.out.println("可用连接数为"+connectionPool.getTotalFree());
        System.out.println("正在使用"+connectionPool.getTotalLeased());
    }
}
