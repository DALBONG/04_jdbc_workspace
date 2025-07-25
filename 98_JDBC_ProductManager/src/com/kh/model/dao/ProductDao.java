package com.kh.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.model.vo.Product;

public class ProductDao {
//		12. properties 변수 생성
		Properties prop = new Properties();
	
//					8. service에서 conn 받음
	public void selectPd(Connection conn) {
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
				
//		16. 데이터가 담긴 p를 만든 객체(배열)에 담기.
				list.add(p);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
//		17. 사용한 jdbc 변수들 반납
		} finally {
			close(rset);
		}
		
	}
	
	

	
	
	
	
}
