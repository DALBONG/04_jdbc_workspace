package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

//DAO 데이터 액세스 오브젝트 : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기 (jdbc)
//						Controller로 다시 리턴
public class MemberDao {
	/*
	 Statement 와 PreparedStatement의 특징
	  - 둘 다 sql문 실행하고 결과를 받아내는 객체
	  
	  * 차이점
	   - Statement 같은 경우 sql문 바로 전달하며 실행시키는 객체.
	     (sql문을 완성 형태로 만들어 둬야함, 사용자가 입력한 값이 다 채워진 형태.)
	     	> 기존 방식
	     	  1) connection 객체를 통해 Statement 객체 생성
	     	     : stmt = conn.createStatement();
	     	  2) Statement 객체를 통해 "완성된 sql문" 실행 및 결과 받기
	     	     : 결과 = executeXXXX (완성된 sql);
	   - PreparedStatement : '미완성된 sql문'을 잠시 보관해둘 수 있는 객체
	     (사용자가 입력한 값 채워두지 않고 들어갈 공간을 확보만 미리 해두어도 됨.)
	     단, 해당 sql문을 실행하기 전에는 입력한 값으로 채워서 실행해야 함.
	     	> prearedStatement 방식
	     	 1) Connection 객체를 통해 PreparedStatement 객체 생성
	     	  	 : pstmt = conn.preparedStatement([미]완성된 sql 문); 	 
	     	 2) pstmt에 담긴 sql문이 미완성 상태일 경우 우선 완성시켜야 함.
	     	    pstmt.setXXX(1, "대체할 값");
	     	 3) 해당 완성된 sql문 실행 결과 받기 : 결과 = pstmt.executeXXX();
	 */
	
	
	/**
	 * 회원 추가하는 메소드
	 * @param m
	 * @return
	 */
	public int insertMember(Member m) {
		// insert문 => 처리된 행 수 -> 트랜젝션 처리
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		//실행할 sql문
		String sql = "INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
			// ? = holder, 사용자가 입력한 값들이 들어갈 수 있게 ?로 자리만 뚫어놓는 것.
			
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			pstmt = conn.prepareStatement(sql);
				// pstmt객체 생성 시 sql문을 담은채로 생성
			    // -> 빈 공간을 실제 값으로 채워준 후 실행
			
			// pstmt.setString(폴더순번, 대체값); --> '대체할 값'
			// pstmt.setInt(폴더순번, 대체값);    --> 대체할 값
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			result = pstmt.executeUpdate(); // 위에서 이미 호출했기에 빈칸으로
			
			if(result > 0) {  //트랜젝션 처리
				conn.commit();
				
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	public ArrayList<Member> selectList(){
		//select문 (여러 행) -> resultSet 
		
		ArrayList<Member> list = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("USERNO"),
						   rset.getString("userId"),
						   rset.getString("userpwd"),
						   rset.getString("userName"),
						   rset.getString("gender"),
						   rset.getInt("age"),
						   rset.getString("Eamil"),
						   rset.getString("phone"),
						   rset.getString("address"),
						   rset.getString("Hobby"),
						   rset.getDate("enrolldate")));
				
			}
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return list;
		
	}
	
	
	public Member selectByUserId(String userId) {
		// select 한 행 조회. > ResultSet -> Member객체
		Member m = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USERNO"),
						   rset.getString("userId"),
						   rset.getString("userpwd"),
						   rset.getString("userName"),
						   rset.getString("gender"),
						   rset.getInt("age"),
						   rset.getString("Eamil"),
						   rset.getString("phone"),
						   rset.getString("address"),
						   rset.getString("Hobby"),
						   rset.getDate("enrolldate"));
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return m;
		
	}
	
	
	public ArrayList<Member> selectByUserName(String Keyword) {
		// select ->  여러 행 -> ArrayList
		ArrayList<Member> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		//String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%?%'";
		  // 해결방법 1
		// String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%' || ? || '%'";
		  // 해결방법 2
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";

		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			
			pstmt = conn.prepareStatement(sql);
			//해결방법 1일경우의 Sql 문일경우
			//pstmt.setString(1, Keyword);
			
			// 해결방법 2의 sql문일경우
			pstmt.setString(1, "%" + Keyword + "%");

			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("USERNO"),
						   rset.getString("userId"),
						   rset.getString("userpwd"),
						   rset.getString("userName"),
						   rset.getString("gender"),
						   rset.getInt("age"),
						   rset.getString("Eamil"),
						   rset.getString("phone"),
						   rset.getString("address"),
						   rset.getString("Hobby"),
						   rset.getDate("enrolldate")));
				
			}
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return list;
		
	}
	
	
	public int updateMember(Member m) {
		// id로 조회 후 비번, 메일, 폰, 주소 변경 -> 한 행 int		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE MEMBER SET USERPWD, USEREAMIL, PHONE, ADDRESS WHERE USERID = ?";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","jdbc","jdbc");
			pstmt = conn.prepareStatement(sql);

			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return result;
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	

	
	
	
}
