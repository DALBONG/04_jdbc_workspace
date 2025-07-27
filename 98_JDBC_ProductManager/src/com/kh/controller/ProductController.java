package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.service.ProductService;
import com.kh.model.vo.Product;
import com.kh.view.ProductMenu;

public class ProductController {
	
	public void selectPd() {
// 		2. 사원 전체 조회 : arrayLast 사용
		ArrayList<Product> list = new ProductService().selectPd();
		
// 		3. 조회 결과 여부에 따른 메시지 출력
				// isEmpty : 길이가 0일경우 true return
		if(list.isEmpty()){
			new ProductMenu().displayNodata("전체 조회 결과가 없어요");
		}else {
			//System.out.println("조회 결과 : ");
// 		4. 앞으로 조회 할 일 많으므로 Menu클래스에서 메소드 만들기
			new ProductMenu().displayProductList(list);
//		5. 결과 Service 클래스에서 DB 데이터 조회
		} 
	}
	
	
// 		insert 5. insertPdList 메소드 생성
//			insert 6. 매개변수 추가, 이곳엔 자료형이 없으므로 자료형도 추가하여.
	public void insertPdList(String product_Id, String p_Name, int price, String description, int stock) {
//		insert 7. 매개변수 정보를 담을 매개변수 생성자 생성. 
		Product p = new Product(product_Id, p_Name, price, description, stock);
		
// 		insert 8. 매개변수 생성자를 service로 보내 DB와 커넥팅 처리 요청
		//new ProductService().insertPdList(p);

// 		insert 30. service에서 반환해준 값을 담을 변수 생성 (insert 8.에 추가 )
		int result = new ProductService().insertPdList(p);

// 		insert 31. 처리 결과에 따라 나타낼 메시지 표시 - Menu에 미리 생성한 옵션처리 메소드 활용
		if(result > 0) {
			new ProductMenu().displaySucess("제품 정보 추가 성공!");
		}else {
			new ProductMenu().displayFailed("제품 정보 추가 실패");
		}
	}
	
	
	
// 		update 7. updatePd 메소드 생성
//			update 8. menu에서 받을 객체를 담기
	public void updatePd(String product_Id, String p_Name, int price, String description, int stock) {
		// > ArrayList에 담아도 
//		update 9. 받은 각 객체들을 담을 변수 만들기
		Product p = new Product();
//		update 10. 생성한 p 변수에 입력받은 각 객체 값들 담기
		p.setProduct_Id(product_Id);
		p.setP_Name(p_Name);
		p.setPrice(price);
		p.setDescription(description);
		p.setStock(stock);

//		update 11. p에 담은 값들을 service에 Connection 요청
		//new ProductService().updatePd(p);
		
//		update 32. service에서 받은 result 결과 담기		
		int result = new ProductService().updatePd(p);
	
//		update 33. result 결과에 따른 표시할 결과문 작성
		if(result > 0 ) {
			new ProductMenu().displaySucess("제품 정보 수정 완료!");
		}else {
			new ProductMenu().displayFailed("제품 정보 수정 실패!");
		}
	}
	


// 		delete 5. 정보 삭제 메소드 생성
//			delete 6. 받은 제품코드 변수 담기.
	public void deletePd(String product_Id) {

//		delete 7. service로 db와 연결 요청
//		delete 27. 요청 수행한 result 결과에 담기
		int result = new ProductService().deletePd(product_Id);
		
//		delete 28. result 수행 결과에 따른 결과문 작성
		if(result > 0) {
			new ProductMenu().displaySucess("정보 삭제 성공!");
		}else {
			new ProductMenu().displayFailed("정보 삭제 실패!");
		}
	}
	
	

//		select name 3. 제품명 검색 처리 메소드 생성
	public void selectPdName(String inputPdName) {
//		select name 4. service에 db와 연결 요청
		ArrayList<Product> list = new ProductService().selectPdName(inputPdName);
//		select name 15. 받은 list 객체, 결과문 작성
		if (list.isEmpty()) {
			new ProductMenu().displayNodata(inputPdName + "에 해당하는 물품이 없습니다");
		}else {
			new ProductMenu().displayProductList(list);
		}
		
		
	}
	
	
	
	
}
