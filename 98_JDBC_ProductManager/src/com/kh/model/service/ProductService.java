package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.model.dao.ProductDao;
import com.kh.model.vo.Product;

import static com.kh.common.JDBCTemplate.*;

public class ProductService {
	
//      24. return할 list의 자료형인 ArrayList로 맞추기. -> Template로
	public ArrayList<Product> selectPd() {
		// 전체 조회, commit 필요X, conn 반납
//		6. DB와 연동 작업, Template에서 만든 Connection 생성 객체 가져오기
		 					//7.JDBCT쓰기 귀찮음. import 에서 .* 작업
		Connection conn = getConnection();
//		7. 커넥션 한 것 Dao로 보내기
		ArrayList<Product> list = new ProductDao().selectPd(conn);
//		22. Dao에서 받은 conn을 Template객체 활용하여 반납
		close(conn);
//      23. Dao에서 받은 list를 호출한 controller로 보내기
		return list;
	}

	

//		insert 9. PC(controller)에서 받아 처리할 회원추가 메소드 생성
//			insert 10. PC에서 받은 p를 담기 (자료형 추가하여) 
//		insert 29. 반환형을 반환한 값의 자료형 int로 수정 
	public int insertPdList(Product p) {
// 		insert 11. Template에 생성한 connection 객체 생성
		Connection conn = getConnection();
//		insert 12. conn을 Dao에 보내 추가 요청 처리
//			insert 13. dao에 보낼 conn과 p를 담기
		//new ProductDao().insertPdList(conn, p);
		
//		insert 25. Dao에서 반환해준 값을 변수에 담기	(insert 13에 추가)
		int result = new ProductDao().insertPdList(conn, p);
// 		insert 26. Dao에서 보낸 값을 트랜잭션 처리 - Template 활용 (결과에 따라 커밋 or 롤백)
		if (result > 0 ) {
			commit(conn);
		}else {
			rollback(conn);
		}
//		insert 27. 사용한 conn 반납 - Template 활용
		close(conn);

//		insert 28. 처리를 호출한 pc에 처리값 반환
		return result;
	}
	
	
	
// 		update 12. 요청 받을 update메소드 생성
//			update 13. 받은 p값 담기
//		update 31. 반환한 result값에 맞게 맞환형 수정
	public int updatePd(Product p) {
//		update 14. template로 생성한 Connection 객체 생성
		Connection conn = getConnection();
//		update 15. conn과 p를 dao에 추가 처리 요청
		//new ProductDao().updatePd(conn, p);
		
//		update 27. 15번 활용, dao에서 반환받은 result(sql문)에 연결 및 p객체들 담기		
		int result = new ProductDao().updatePd(conn, p);
		
// 		update 28. result(sql문) 수행 결과에 따른 트랜젝션 처리
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
//		update 29. conn 반납
		close(conn);
//		update 30. 수행 결과를 요청한 pc에 반환
		return result;
		
	}
	
	
	
// 		delete 8. 정보 삭제할 메소드 생성
//			delete 9. 입력받은 정보 담기
//		delete 26. result 반환형 일치
	public int deletePd(String product_Id) {

//		delete 10. db와 연동할 Template로 미리 만든 Connection 객체 생성
		Connection conn = getConnection();

//		delete 11. 받은 Id와 연결정보를 dao에 넘기기
//		delete 22. 정보들을 반환받은 sql문에 담기 
		int result = new ProductDao().deletePd(conn, product_Id);
		
//		delete 23. sql문 수행결과에 따른 트랜젝션 처리
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
//		delete 24. conn 반납
		close(conn);
//		delete 25. 트랜잭션 처리 결과를 호출한 pc에 반환
		return result;
		
	}
	
	

	public ArrayList<Product> selectPdName(String inputPdName) {
//		select name 5. Connection db연동정보 객체 생성
		Connection conn = getConnection();
//		select name 6. 연동정보 Dao에 넘기기
		ArrayList<Product> list = new ProductDao().selectPdName(conn, inputPdName);

//		select name 14. conn 반환
		close(conn);
		return list;

		
	}
	
	
	
}