package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestRun {

	public static void main(String[] args) {
		/*
		 * 1. JDBC용 객체 - Connection : DB의 연결 정보를 담고있는 객체 
		 * - *[Prepared]Statement : 연결된
		 * DB의 SQL문 전달해서 실행, 결과를 받아내는 객체 
		 * - ResultSet : Select문 실행 후 조회 결과물들이 담겨있는 객체
		 * 
		 * 2. JDBC 과정 (순서 중요!) 
		 * 	 1) jdbc driver 등록 : 해당 DBMS(오라클)가 제공하는 클래스 등록 (ojdbc6) 
		 *   2) Connection 객체 생성. : 연결하고자 하는 DB정보를 입력해서 해당 DB와 연결, 생성 
		 *   3) Statement 생성 : Connection 객체를 이용해서 생성 (sql문 실행 및 결과받는 객체) 
		 *   4) sql문 전달하면서 실행 : Statement 객체 이용해서 sql문 실행 
		 *   5) 결과 받기 
		 *   	ㄴ Select문 실행 => ResultSet 객체 (조회된 데이터들이 담겨있음) -> 6_1) 
		 *   	ㄴ DBL문 실행 => int형 (처리된 행 수) -> 6_2) 
		 *   6_1) ResultSet에 담겨있는 데이터들을 하나씩 뽑아 vo객체에 옮겨 담기 
		 *   		[+ 여러 행 조회시 ArrayList에 담기] 
		 *   6_2) 트랜젝션 처리(성공적 수행시 커밋, 실패시 롤백) 
		 *   7) 다 사용한 JDBC객체를 반드시 자원 반납 (안할 시 db 락걸림. close) => 생성 역순으로 반납
		 */

		/*
		 * 1. 각자 pc(localhost)에 JDBC계정에 연결한 후 TEST 테이블에 INSERT 해보기!

		// inset문 = 처리된 행 수(int) => 트랜잭션 처리
		
		Scanner sc = new Scanner(System.in);
		System.out.println("번호 입력해라 : ");
		int num = sc.nextInt();
		sc.nextLine();
		
		System.out.println("이름 뭐야? : ");
		String name = sc.nextLine();
		
		
		// 필요한 변수 세팅
		int result = 0; // 처리된 행 수 받을 변수
		Connection conn = null; // DB의 연결 정보를 보관할 객체
		Statement stmt = null; // sql문 전달해서 실행 후 결과 받는 객체
		// ResultSet은 select 에서만 필요한 것

		// 앞으로 실행할 sql문 작성 ("완성 형태"로 만들어두기) => "쿼리문 안에 ;이 없어야 함*!!"
		//String sql = "INSERT INTO TEST VALUES (1, '차웅우', SYSDATE)";
		String sql = "INSERT INTO TEST VALUES(" + num + ", '" + name + "', SYSDATE)";

		// 우리가 DB에도 접속정보를 입력하는 것 처럼 자바에서도 접속정보를 입력해야 함.
		try {
			// 1) jdbc driver 등록.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 클래스를 등록하는 메소드 --> 풀 클래스 명을 써야함
			System.out.println("jdbc driver 등록 성공!");
			// OracleDriver는 자바에서 제공하는 클래스 아님
			// 패키지명을 보면 oracle로 시작, ojdb6.jar파일 등록해줘야 함

			// 2) Connection 객체 생성 :DB연결 (url, 계정명, 비번 필요)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");

			// 3) Statement 객체 생성
			stmt = conn.createStatement();

			// 4, 5) sql문 전달하면서 실행 후 결과 받기 (처리된 행 수)
			result = stmt.executeUpdate(sql);
			// 실행할 sql문이 DML문일 경우 -> etmt.executeUpdate("dml문");
			// 실행할 sql문이 select문 일 경우 -> etmt.executeQuery("Select문") : ResultSet;

			// 6)트랜젝션 처리
			if (result > 0) { // 성공시
				conn.commit();
			} else { // 실패시
				conn.rollback();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 7) 다 쓴 JDBC용 객체 자원 반납 (생성된 역순)
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if (result > 0) {
			System.out.println("insert 성공");

		} else {
			System.out.println("insert 실패");
		}
		
		*/

	  // 2. 내 pc에 DB상 jdbc 계정에 TEST 테이블에 있는 모든 데이터 조회 해보기
	  // select문 -> 결과가 ResultSet(조회된 데이터들 담겨있음) 받기 
						   // => ResultSet으로 부터 데이터 뽑기 
	  //필요 변수 세팅
	  Connection conn = null;
	  Statement stmt = null;
	  ResultSet rset = null; // select문 실행하여 조회된 결과값들이 처음 실질적으로 담길 객체
		
	  // 실행할 sql문
	  String sql = "SELECT * FROM TEST";
	  
	  try {
		  //1) jdbc driver 등록
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		  //2) Connction 객체 생성
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "jdbc", "jdbc");
		
		  //3) Statement 객체 생성
		stmt = conn.createStatement();
		
		  // 4, 5) sql문 전달해서 실행 후 결과 받기 (ResultSet 객체)
		rset = stmt.executeQuery(sql);
		
		//rset.next()라는 메소드 사용하면 oracle의 커서를 아래행으로 옮길 수 있음. 
		  // 기본 위치는 컬럼 명이 적혀있는 행에 있음 즉, 한번 이행시 데이터가 있는 행으로 감
		  // 반환형 : boolean -> 다음것이 있으면 true  없으면 false
		while(rset.next()) {
			 // 현재 참조하는 rset으로 부터 어떤 컬럼에 해당하는 값을 어떤 타입으로 뽑을것인지 제시
			int tno = rset.getInt("TNO");
			String tname = rset.getString("TNAME");
			Date tdate = rset.getDate("TDATE");
			
			System.out.println(tno + ", " + tname + ", " + tdate );
			
		}
		
	} catch (ClassNotFoundException e) {

		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
			try {
		 // 6) 반납 
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		
	}

	}

}
