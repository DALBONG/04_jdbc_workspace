package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

import static com.kh.common.JDBCTemplate.*;

public class ProductService {
	
	public void selectPd() {
		// 전체 조회, commit 필요X, conn 반납
		//6. DB와 연동 작업, Template에서 만든 Connection 생성 객체 가져오기
		 					//7.JDBCT쓰기 귀찮음. import 에서 .* 작업
		Connection conn = getConnection();
		//7. 커넥션 한 것 Dao로 보내기
		ArrayList<Product> list = new ProductDao().selectPd(conn);
		
		
	}
		
	

}
