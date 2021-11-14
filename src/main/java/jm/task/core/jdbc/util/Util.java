package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private  static final String URL = "jdbc:mysql://localhost:3306/jdbc_table_test";
    private  static final String USER = "root";
    private  static final String PASSWORD = "root";

    public static Connection getConnection()  {
        Connection connection = null;
        Driver driver = null;
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            if (!connection.isClosed()){
                System.out.println("Соединение с БД установлено!");
            }
           // connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
