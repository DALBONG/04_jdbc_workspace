package com.kh.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	/**
	 * 1. Connection 객체 생성
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
//		25. properties 객체 생성
		Properties prop = new Properties();
		
		try {
// 	 	26. prop 객체 경로 지정 후 예외처리
			try {
				prop.load(new FileInputStream("resources/driver.properties"));
// 		27. Properties 파일에 담은 내용 대입 
				Class.forName(prop.getProperty("driver"));
// 		28. Properties 파일에 담은 내용 대입 -> Dao 클래스로		
				conn = DriverManager.getConnection(prop.getProperty("url")
												 , prop.getProperty("username")
												 , prop.getProperty("password"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return conn;
	}
	
	
	/**
	 * commit 객체 생성
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Rollback 객체 생성
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && conn.isClosed())
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * rset 반납 객체
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && rset.isClosed())
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	/**
	 * stmt 반납 객체 
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed())
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * conn 반납 객체
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && conn.isClosed())
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
}
