package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

//DAO 데이터 액세스 오브젝트 : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기 (jdbc)
//						Controller로 다시 리턴
public class MemberDao {
	
		// m 타입을 모르므로 어디서 가져왔는지 
	/**
	 * 사용자가 입력한 정보들을 추가시켜주는 메소드
	 * @param m : 사용자가 입력한 값들이 담겨있는 Member 객체 
	 * @return  : insert문 수행 후 처리된 행수 
	 */
	public int insertMember(Member m) {
		// insert문 => 처리된 행수(int) : 트랜젝션 처리
		
		// 필요 변수 먼저 셋팅
		int result = 0; // 처리된 결과를 받아줄 변수 
		Connection conn = null;  // 연결된 DB의 연결정보를 담는 객체
		Statement stmt = null;   // 완성된 SQL문 전달시 바로 실행 후 결과를 받는 객체
		//실행할 sql문
		// INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL, 'XXX', 'XXX', 'XXX', 'X', XX, 'XXXXXX', 'XXXXX', 'XXXXXXXXXXXX', 'XXX,XXX', SYSDATE);
		
		String sql = "INSERT INTO MEMBER VALUES (SEQ_USERNO.NEXTVAL,"
				+ "'" + m.getUserId() + "' ,"
				+ "'" + m.getUserPwd() + "'," 
				+ "'" + m.getUserName() + "'," 
				+ "'" + m.getGender() + "' ,"
					  + m.getAge()    + ","
				+ "'" + m.getEmail()  + "',"
				+ "'" + m.getPhone()  + "',"
				+ "'" + m.getAddress()  + "',"
				+ "'" + m.getHobby()  + "', SYSDATE)";

		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			
			//3) Statement 객체 생성
			stmt = conn.createStatement();
			
			//4,5) sql문 전달하며 실행 후 결과 받기
			result = stmt.executeUpdate(sql);
			
			// 6) 트랜젝션 처리 
			if(result > 0 ) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			// 다 쓴 jdbc 객체 반납
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	/**
	 * 사용자가 요청한 회원 전체 조회를 처리해주는 메소드
	 * @return 조회된 결과 있을시, 조회결과가 담긴 list, 결과가 없을시 텅 빈 list 
	 */
	public ArrayList<Member> selectList() {
		// SELECT문 (여러행 조회) : ResultSet객체 -> ArrayList에 담기
		
		//필요한 변수 세팅
		ArrayList<Member> list = new ArrayList<Member>(); //현재 비어있는 상태
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 : url, 계정명, 계정비번
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
			
			// 3) conn을 이용한 stmt 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) sql실행 결과 받기
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
			    m.setUserName(rset.getString("USERNAME"));
			    m.setGender(rset.getString("GENDER"));
			    m.setAge(rset.getInt("AGE"));
			    m.setEmail(rset.getString("EAMIL"));
			    m.setPhone(rset.getString("PHONE"));
			    m.setAddress(rset.getString("ADDRESS"));
			    m.setHobby(rset.getString("HOBBY"));
			    m.setEnrollDate(rset.getDate("ENROLLDATE"));
			    //현재 참조하고 있는 행에 대한 모든 컬럼에 대한 데이터들을 한 멤버 객체에 담기 끝
			    
			    list.add(m);
				
			}
			
			// 반복문 끝난 시점에 조회된 데이터가 없었다면 LIST가 텅 빈 상태. 
			// 데이터가 있었다면 list에 뭐라도 담겨있을 것.
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return list; // 텅빈 리스트 | 뭐라도 담겨있는 list
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
