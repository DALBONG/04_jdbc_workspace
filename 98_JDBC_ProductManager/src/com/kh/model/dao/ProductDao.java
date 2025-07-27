package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
// 18. 임포트 후, static, .*처리
import static com.kh.common.JDBCTemplate.*;
import com.kh.model.vo.Product;

public class ProductDao {
//		12. properties 변수 생성
		Properties prop = new Properties();
		
//		12-2 기본생성자 생성 후, sql문 담은 xml 파일 경로지정 후 예외처리
		public ProductDao() {
			try {
				prop.loadFromXML(new FileInputStream("resources/query.xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	
// 		21. Service 클래스에 보낼 list의 객체와 자료형 (ArrayList) 맞춤
//		8. service에서 conn 받음
	public ArrayList<Product> selectPd(Connection conn) {
//		9. 직접적으로 DB와 연결해서 DB에 있는 데이터 받고 담을 객체(배열) 만들기. 
					  // 데이터를 받고, 담을 곳이 필요하므로 ResultSet 필요
		ArrayList<Product> list = new ArrayList<Product>();
//		10. conn을 받아 작업할 Statement, ResultSet 변수 만들기.
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
// 		11.DB의 데이터를 받기위한 쿼리문을 담을 변수 만들기 --> 외부파일(Properties | xml) 이용!
			// SELECT * FROM PRODUCT; 
		String sql = prop.getProperty("selectPd");
		
// 	 	13. conn활용 pstmt, rset 작업
//      14. 예외 처리
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
//		15. 모든 컬럼을 담을 데이터들을 한 객체에 담기.
				// 불러올 객체 (담을 자료형(컬럼명))
			while(rset.next()) {
				Product p = new Product();
				
				p.setProduct_Id(rset.getString("PRODUCT_ID"));
				p.setP_Name(rset.getString("P_NAME"));
				p.setPrice(rset.getInt("PRICE"));
				p.setDescription(rset.getString("DESCRIPTION"));
				p.setStock(rset.getInt("STOCK"));
				
//		16. 데이터가 담긴 p를 만든 객체(배열) list에 담기.
				list.add(p);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
//		17,19. 사용한 jdbc 변수들 반납. 템플릿 활용, import 후 삭제JDBCTemplate.* 삭제
		} finally {
			close(rset);
			close(pstmt);
		}
//		 20. 데이터가 담긴 p를 담은 list객체를 호출한곳(service)에 리턴; 
		return list;
	}
	
	
//		insert 14. service에서 받아 처리할 메소드 생성
//			insert 15. service에서 받은 객체 담기 (자료형 추가)
//		insert 24. 반환형을 return할 값의 자료형으로 수정 (int) 
	public int insertPdList(Connection conn, Product p) {

//		insert 16. 처리 결과 받아줄 변수 생성
		int result = 0;
// 		insert 17. sql문 전달시 실행후 결과 받을 pstmt 객체 생성
		PreparedStatement pstmt = null;

//		insert 18. xml파일에 작성한 sql문을 담을 객체 생성
//			insert 19. xml파일에 sql문 작성
		String sql = prop.getProperty("insertPdList");
		
// 		insert 20. service에서 받은 conn을 pstmt활용하여 sql문 실행
		try {
			pstmt = conn.prepareStatement(sql);
//		++ insert 20-1. 바인딩 꼭 해줘야함. 
		//	안해주니, java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1 오류 뜸
		pstmt.setString(1,	p.getProduct_Id());
		pstmt.setString(2, p.getP_Name());
		pstmt.setInt(3, p.getPrice().intValue());	// int로 변환
		pstmt.setString(4, p.getDescription());
		pstmt.setInt(5, p.getStock().intValue());
// 		insert 21. sql문을 담은 pstmt의 결과값을 위에 생성한 result 변수에 담기.
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
// 		insert 22. 사용한 pstmt 미리 생성한 Template 사용하여 반납
		} finally {
			close(pstmt);
		}
//		insert 23. pstmt의 '결과값'을 호출한 service에 보내기
		return result;

	}
	
	
// 		update 16. update처리할 메소드 생성
//			update 17. 요청받은 conn, p 담기
//		update 26. 반환형을 result 값과 일치시키기 
	public int updatePd(Connection conn, Product p) {
		// update문. 처리, 결과: 행 int, 트랜젝션 처리
//		update 18. 결과 처리할 변수 생성
		int result = 0;
		
//		update 19. 받은 conn에 sql문 처리할 pstmt 생성
		PreparedStatement pstmt = null;

// 		update 20. sql문을 담을 변수 생성
//			update 21. xml파일에 update 쿼리 작성 후, 변수 담기
		String sql = prop.getProperty("updatePd");
		
//		update 22. service에서 받은 conn 활용 pstmt로 sql문 실행 후 예외처리
		try {
			pstmt = conn.prepareStatement(sql);

//		++ dudate 22_2) xml 파일에 담긴 sql문에 각각 담을 객체 바인딩
			// java.sql.SQLException: 인덱스에서 누락된 IN 또는 OUT 매개변수:: 1
			pstmt.setString(1, p.getP_Name());
			pstmt.setInt(2, p.getPrice().intValue());
			pstmt.setString(3, p.getDescription());
			pstmt.setInt(4, p.getStock().intValue());		
			pstmt.setString(5, p.getProduct_Id());

			
//		update 23. sql문 실행 결과 result 변수에 담기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
//		update 24. pstmt 반납
		} finally {
			close(pstmt);
		}
//		update 25. result에 담긴 sql문 실행결과, 호출한 service에 리턴
		return result;
	}
	
	
	
// 		delete 12. 요청 처리할 메소드 생성, service에서 받은 객체 담기
//		delete 21. result의 반환값에 맞게 반환형 수정 
	public int deletePd(Connection conn, String product_Id) {

//		delete 13. 수행 결과 처리할 변수 생성
		int result = 0;

//		delete 14. db연동 정보 넘겨받아 sql문 수행할 pstmt 객체 생성 
		PreparedStatement pstmt = null;
		
// 		delete 15. sql문을 담을 객체 생성 후, sql문 작성
		String sql = prop.getProperty("deletePd");
		
//		delete 16. 연동정보를 pstmt에 담고 예외처리
		try {
			pstmt = conn.prepareStatement(sql);
			
//		delete 17. xml파일에 작성한 sql문 바인딩
		pstmt.setString(1, product_Id);
			
//		delete 18. sql문 수행결과 담기
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
//		delete 19. pstmt 반납
		} finally {
			close(pstmt);
		}
//		delete 20. sql문 수행결과 호출한 service로 반환
		return result;
		
	}
	
	public ArrayList<Product> selectPdName(Connection conn, String inputPdName) {
		//정보 조회, arrayList - rset 처리
//		select name 7. 조회 결과 담을 ArrayList 생성
		ArrayList<Product> list = new ArrayList<Product>();
	
//		select name 8. sql문 실행하고 처리할 pstmt, rest 객체 생성
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
//		select name 9. sql문 담을 객체 생성 및 sql문 작성(xml파일)
		String sql = prop.getProperty("selectPdName");
		
//		select name 10. 연동정보 pstmt 담고 rset에 결과물 담기
		try {
			pstmt = conn.prepareStatement(sql);

//		select name 10-2. 입력한 값으로 바인딩, Like함수 사용시 % 붙이기		
			pstmt.setString(1, "%" + inputPdName + "%");
			
			rset = pstmt.executeQuery();

// 		select name 11. sql문에 담을 정보 바인딩
			while(rset.next()) {
				list.add(new Product(rset.getString("product_Id")
								   , rset.getString("p_Name")
								   , rset.getInt("price")
								   , rset.getString("description")
								   , rset.getInt("stock")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
//		select name 12. rset, pstmt 반납
		} finally {
			close(rset);
			close(pstmt);
		}
//		select name 13. 담은 정보들 호출한 service에 반환
		return list;
		
		
		
	}
	
	
}
