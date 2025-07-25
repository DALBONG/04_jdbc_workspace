package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	
	// 실행되자마자 메모리 영역에 다 올라감
	  // 싱글톤 패턴 : 메모리 영역에 단 한번 올려두고 재사용하는 개념
	/**
	 * 1. Connection 객체 생성. (DB와 접속) 한 후 해당 Connection객체 반환해주는 메소드
	 * @return
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
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
