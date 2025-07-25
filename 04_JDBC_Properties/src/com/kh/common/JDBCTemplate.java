package com.kh.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	/**
	 * 1. Connection 객체 생성. (DB와 접속) 한 후 해당 Connection객체 반환해주는 메소드
	 * @return
	 */
	public static Connection getConnection() {
		
		/*
		  	기존 방식 : jdbc driver 구문, 접속한 db의 url, 접속 계정명/비번들을 자바 소스코드 내에 작성.(정적코딩방식)
		  	
		  	-> 문제점 : dbms가 변경되었을 경우, 접속 db의 url이나 계정/비번이 변경되었을 경우
		  	 		  코드 자체를 수정해야 함.
		  	 		  후에, 수정내용 반영시키고자 한다면 프로그램을 재구동 해야 함.(프로그램 비정상 종료 후 다시 구동);
		  	 	ㄴ 유지 보수 불편함.
		  	 	
		  	-> 해결법 : db관련 정보들을 별도로 관리하는 외부파일(.properties)로 만들어서 관리.
		  	   		  외부파일로부터 읽어들여 반영시키면 됨. (동적코딩방식)
		  	
		 */
		
		
		Connection conn = null;
		
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("resources/driver.properties")); // prop에 key value 추가 됨
			
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"),
											   prop.getProperty("username"),
											   prop.getProperty("password"));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return conn; 
	}
	
	/**
	 * 2. Commit 처리 메소드 (Connection 전달받아서)
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			// 값이 null이 나왔을 경우 null.commit이 되어버리므로 조건문 제시
			if(conn != null && conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 3. rollback 처리 메소드 (Connection 전달 받아)
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	/**
	 * 4. Statement 반납 메소드
	 * @param stmt
	 */
	// jdbc용 객체를 전달받아 반납처리 메소드
				// Statement가 부모이기에 preparedStatement는 따로 받지 않아도 됨 
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 5. Connection 객체 전달받아 반납해주는 메소드
	 * @param conn
	 */
			// 메소드 명은 같으나, 매개변수가 다름 : 오버로딩 된 것 
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	/**
	 * 6. ResultSet 객체 전달받아 반납해주는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
}
