package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Member;

//DAO 데이터 액세스 오브젝트 : DB에 직접적으로 접근해서 사용자의 요청에 맞는 SQL문 실행 후 결과 받기 (jdbc)
//						Controller로 다시 리턴
public class MemberDao {
	
		// m 타입을 모르므로 어디서 가져왔는지 

	public int insertMember(Connection conn, Member m) {
		// insert문 => 처리된 행수(int) : 트랜젝션 처리
		
		// 필요 변수 먼저 셋팅
		int result = 0; // 처리된 결과를 받아줄 변수 
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
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//conn객체는 아직 반납 X (트랜젝션 처리 서비스 가서 해야함, stmt는 가능)
			close(stmt);
		}
		return result;
	}
	
	public ArrayList<Member> selectList(Connection conn) {
		
		ArrayList<Member> list = new ArrayList<Member>(); //현재 비어있는 상태
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER";
		
		try {
			
			stmt = conn.createStatement();
			
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(stmt);
			// close(conn); -- 이왕, conn만든곳에서 반납 (Service)
		}
		return list; // 텅빈 리스트 | 뭐라도 담겨있는 list

	}
	
	/**
	 * 사용자의 아이디로 회원검색 요청 처리해주는 메소드 
	 * @param userId : 사용자가 입력한 검색하고자 하는 회원 아이디
	 * @return :검색 결과 있을시 생성된 Member객체 | 결과 없을시  null
	 */
	public Member selectByUserId(Connection conn, String userId) {
		//select문 (한 행 or X -> ResultSet 객체)
		   			//ㄴ 최대 한명 조회이므로  ArrayList 굳이 필요X
		//필요 변수 세팅
		Member m = null;   //조회결과가 있을수도, 없을 수도 있음
		
		// jdbc용 객체
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) { // 한 행이라도 조회되었을 때
				// 조회되었다면 해당 조회 컬럼값을 뽑아 하나의 Member객체의 각 필드에 담기
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
			//위의 조건문이 끝난 시점에 조회 데이터 없었을 시 m == null
			// 있었을 경우 m이 생성되고, 데이터가 담겨있을 것. 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			//close(conn);
		}
		return m;
		
	}
	

	public ArrayList<Member> selectByUserName(Connection conn, String keyword) {
		// select문 수행 (여러행) -> ResultSet ArrayList
		
		ArrayList<Member> list = new ArrayList<Member>();
		
		Statement stmt = null;
		ResultSet rset = null;
		
		String sql = "SELECT * from member where username LIKE '%" + keyword + "%'";
		
		try {
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("USERNO"),
						   rset.getString("USERID"),
						   rset.getString("USERPWD"),
						   rset.getString("USERNAME"),
						   rset.getString("GENDER"),
						   rset.getInt("AGE"),
						   rset.getString("EAMIL"),
						   rset.getString("PHONE"),
						   rset.getString("ADDRESS"),
						   rset.getString("HOBBY"),
						   rset.getDate("ENROLLDATE")));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
			//close(conn);
		}
		return list; // 텅 비었거나, 담겨있거나.
	}
			
	/**
	 * 사용자가 입력한 아이디의 정보 변경 요청 처리해주는 메소드
	 * @param m
	 * @return : 처리된 행 수
	 */
			// result의 반환형은 int
	public int updateMember(Connection conn, Member m) {
		//update 문 -> 처리된 행 수(int) -> 트랜젝션 처리
		
		int result = 0;
		
		Statement stmt = null;
		
		String sql = "UPDATE MEMBER "
				    + "SET USERPWD = " + "'" + m.getUserPwd() + "'"
				    + ", EAMIL = " + "'" + m.getEmail() + "'"
				    + ", PHONE = " + "'" + m.getPhone() + "'"
				    + ", ADDRESS = " + "'" + m.getAddress() + "'"
				    + "WHERE USERID = " + "'" + m.getUserId() + "'";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			
			}catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(stmt);
			
		}
		return result;
		
	}

	public int deleteMember(Connection conn, String userId) {
		// rset 필요 없음.
		// DELETE 테이블명 컬럼 WHERE USERID 
		
		int result = 0; 
		
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = '" + userId + "'";
		
		try {
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(sql);
			}catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			close(stmt);
			
		}
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
