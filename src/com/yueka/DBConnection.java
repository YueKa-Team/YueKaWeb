package com.yueka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
 
	private Connection connect = null;//创建空链接
	private Statement statement = null;//创建空状态
	
	public DBConnection(){//建立链接
        try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			//connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");  //		
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/yueka?user=root&password=123&useUnicode=true&characterEncoding=utf8");
			statement = connect.createStatement();//获得链接状态
			/*System.out.println("db connection ok!");*/
			
        } catch (SQLException e) {
            System.out.println("MySQL Error");//捕获错误
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();//捕获错误
        } 
	}
	
	public ResultSet executeQuery(String strSQL){
		ResultSet resultSet = null;
		try {
			System.out.println(strSQL);
			if(statement==null){
				System.out.println("statement is null!!");
			}
			else
				resultSet = statement.executeQuery(strSQL);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public boolean execute(String strSQL){
		try {
			System.out.println(strSQL);
			int row = statement.executeUpdate(strSQL);
			System.out.println("row="+row);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void close(){
		try {
		    if (statement != null) {
		        statement.close();
		    }
		    if (connect != null) {
		        connect.close();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		DBConnection b =new DBConnection();
		ResultSet rs = b.executeQuery("select * from users where userid = '1705010406'");
		
		while(rs.next()){
			System.out.println(rs.getString(2));
		}
		b.close();
	}
	
}
