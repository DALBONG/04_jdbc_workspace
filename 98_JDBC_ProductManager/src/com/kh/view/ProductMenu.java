package com.kh.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.ProductController;
import com.kh.model.vo.Product;

public class ProductMenu {
	
	private Scanner sc = new Scanner(System.in);
	private ProductController pc = new ProductController();
	
	/**
	 * 메인 화면 구성
	 */
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n 상품 조회 프로그램 입니다!");
			
			System.out.println("1. 전체 상품 조회하기");
			System.out.println("2. 상품 추가하기");
			System.out.println("3. 상품 수정하기 (상품 id로 조회)");
			System.out.println("4. 상품 삭제하기 (상품 id로 조회)");
			System.out.println("5. 상품 검색하기 (상품 이름 키워드)");
			System.out.println("0. 프로그램 종료");
			System.out.println(" 메뉴 선택");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu){
				case 1 : pc.selectPd(); //1. 메뉴에서 딱히 보일 것 없음 PC호출하여 바로 실행 명령.
						 break;
				case 2 : break;
				case 3 : break;
				case 4 : break;
				case 5 : break;
				case 0 : System.out.println("프로그램 종료!");
			return;
				default : System.out.println("다시 입력!");
			}
		}
	}
		
	
		
			
	// =================================응============================================
	// =================================답============================================
	// =================================화============================================
	// =================================면============================================
	
	/**
	 * 요청 성공시 보일 메시지
	 */
	public void displaySucess(String message) {
		System.out.println("요청 성공 :" + message);
	}
	
	/**
	 * 요청 실패시 보일 메시지
	 * @param message
	 */
	public void displayFailed(String message) {
		System.out.println("요청 실패 : " + message);
	}
	
	/**
	 * 조회된 데이터 없을 시
	 * @param message
	 */
	public void displayNodata(String message) {
		System.out.println("조회된 데이터 없음 : ");
	}
	
	
	/**
	 * 조회된 데이터 n행일 시
	 * @param list
	 */
	// p 라는 변수가 없으므로 호출해줘야 하는 메소드 만들기
	public void displayProductList(ArrayList<Product> list) {
		System.out.println("조회 데이터 결과 : ");
		for(Product p: list) {
			System.out.println(p);
		}
	}

	/**
	 * @param p
	 */
	public void displayProduct(Product p) {
		System.out.println("조회된 데이터는 다음과 같습니다");
	}
	
	
	
		
		
		
		
		
		
		
		
		
	

}
