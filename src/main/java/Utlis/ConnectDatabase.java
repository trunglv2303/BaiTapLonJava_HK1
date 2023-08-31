//package utils;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectDatabase {
//    public static Connection connection = getConnection();
//
//
//    public static Connection getConnection() {
//        Connection connection1 = null;
//        try {
//            String DB_NAME = "QLTB";
//            String url = "jdbc:mysql://localhost:3306/" +DB_NAME;
//            String USER_NAME = "root";
//            String USER_PASSWORD = "trungdz2004";
//            connection1 = DriverManager.getConnection(url, USER_NAME, USER_PASSWORD);
//            System.out.println("Connected to the MySQL server successfully.");
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return connection1;
//    }
//
//}
package Utlis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
    public final static String DB_NAME = "QUANLYTHIETBICODINHNHATRUONGJAVA";
    public final static String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public final static String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DB_NAME+";encrypt=true;trustServerCertificate=true";
    public final static String USER_NAME = "sa";
    public final static String PASSWORD = "1";


    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
//            System.out.println(" Kết Nối Thành Công ");
            return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Lỗi Kết Nối");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getConnection();
    }

}