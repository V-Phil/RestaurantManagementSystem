package com.mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author wang zifan
 * @version 1.0
 * @date 2022/9/12  22:44
 */
public class JDBCUtilsByDruid {
    private static DataSource ds;
    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src\\mysql.properites"));
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static Connection getConnection() throws SQLException{
        return  ds.getConnection();
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
