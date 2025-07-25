package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	/**
	 * 1. Connection 객체 생성
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driave.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
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
