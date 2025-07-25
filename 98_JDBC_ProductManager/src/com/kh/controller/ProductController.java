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
			System.out.println("조회 결과가 없습니다.");
		}else {
			//System.out.println("조회 결과 : ");
// 		4. 앞으로 조회 할 일 많으므로 Menu클래스에서 메소드 만들기
			new ProductMenu().displayProductList(list);
//			5. 결과 Service 클래스에서 DB 데이터 조회
		} 
		
		
		
	}
	
	
	

}
