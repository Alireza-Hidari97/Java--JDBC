package com.example.jdbcfxexample_comp228_014;
import java.sql.*;
import java.sql.*;
import javax.sql.rowset.*;

public class DBUtil {

    private static Connection connection;
    private static Statement statement;

    public static void dbConnect() throws SQLException{
        String dbUrl = "jdbc:oracle:thin:@localhost:1521:orclcdb";
        String username = "system";
        String password = "oracle";
        connection = DriverManager.getConnection(dbUrl, username, password);
//        System.out.println("DB is connected!");
        statement = connection.createStatement();
    }

    public static void dbDisconnect() throws SQLException{
        if(connection !=null && !connection.isClosed()) connection.close();
//        System.out.println("DB is disconnected!");
    }

    public static void createTable(String tableName) throws SQLException{
        dbConnect();
        String sql = "CREATE TABLE " + tableName + " (s_id INTEGER PRIMARY KEY, s_name VARCHAR2(100))";
        statement.execute(sql);
        System.out.println("Table is created!");
        if (statement != null) statement.close();
        dbDisconnect();
    }

    public static void dropTable(String tableName) throws SQLException{
        dbConnect();
        String sql = "DROP TABLE " + tableName ;
        statement.execute(sql);
        System.out.println("Table is dropped!");
        if (statement != null) statement.close();
        dbDisconnect();
    }

    public static void insertData(String tableName, Integer s_id, String s_name) throws SQLException{
        dbConnect();
        String sql = "INSERT INTO " + tableName + " VALUES("+ s_id + ",'" + s_name + "')" ;
        statement.executeUpdate(sql);
        System.out.println("Data is inserted!");
        if (statement != null) statement.close();
        dbDisconnect();
    }

    public static void deleteData(String tableName, Integer s_id) throws SQLException{
        dbConnect();
        String sql = "DELETE FROM " + tableName + " WHERE s_id = " + s_id ;
        statement.executeUpdate(sql);
        System.out.println("Data is deleted!");
        if (statement != null) statement.close();
        dbDisconnect();
    }

    public static ResultSet query(String tableName, String sql) throws SQLException{
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        dbConnect();
        ResultSet resultSet = statement.executeQuery(sql + " " + tableName);
        crs.populate(resultSet);
        if (statement != null) statement.close();
        dbDisconnect();
        return crs;
    }

    public static void main(String[] args) throws SQLException{
        DBUtil.dbConnect();
        DBUtil.dbDisconnect();
        DBUtil.dropTable("COMP228_014");
        DBUtil.createTable("COMP228_014");
        DBUtil.insertData("COMP228_014", 1, "John" );
        DBUtil.insertData("COMP228_014", 2, "James" );
        DBUtil.insertData("COMP228_014", 3, "Jack" );
        DBUtil.deleteData("COMP228_014", 3);
        DBUtil.query("COMP228_014", "SELECT * FROM");
    }
}
