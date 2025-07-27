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
//				1. 메뉴에서 딱히 보일 것 없음 Controller호출하여 바로 실행 명령.
				case 1 : pc.selectPd(); 
						 break;
// 				insert 1. 상품추가 메소드명 설정 -> Menu의 서브메뉴 생성.
				case 2 : insertPd();
						 break;
//				update 1. 상품정보 수정 메소드명 설정 -> 서브메뉴 생성
				case 3 : updatePd();
						 break;
//				delete 1. 상품정보 삭제 메소드 명 설정 -> 서브메뉴 메소드 생성
				case 4 : deletePd();
						 break;
//				select name 1. 제품명으로 검색, pc로 바로 호출
				case 5 : pc.selectPdName(inputPdName());
				case 0 : System.out.println("프로그램 종료!");
			return;
				default : System.out.println("다시 입력!");
			}
		}
	}

//		insert 2. 상품추가하기 서브메뉴 생성
	public void insertPd() {
		System.out.println("\n ** 상품 추가하기 ** ");
		
//      insert 3. 입력받을 정보 생성
		System.out.println("상품 코드 입력 : ");
		String product_Id = sc.nextLine();
		
		System.out.println("상품 명 입력 : ");
		String p_Name = sc.nextLine();
		
		System.out.println("상품 가격 입력 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.println("상품 설명 입력 : ");
		String description = sc.nextLine();
		
		System.out.println("재고 입력 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
//		insert 4. controller로 회원추가 요청
		pc.insertPdList(product_Id, p_Name, price, description, stock);
	}
	

//		update 2. id입력 메소드 생성
//			update 3. return값 자료형에 맞게 반환형 변경(String)
	public String inputProductId() {
		System.out.println("\n 상품 ID 입력 : ");
		return sc.nextLine();
	}
// 		update 3. 서브메뉴 메소드 생성.
		// id로 받아서 정보 수정
	public void updatePd() {
		System.out.println("\n ** 상품 정보 수정 ** ");
	
//		update 4. product_Id 변수에 입력한 id값 담기
		String product_Id = inputProductId();
		
//		update 5. 변경할 상품 정보들 입력 
		System.out.println("변경할 상품 명 : ");
		String p_Name = sc.nextLine();
		
		System.out.println("변경할 상품 가격 : ");
		int price = sc.nextInt();
		sc.nextLine();
		
		System.out.println("변경할 상품 설명: ");
		String description = sc.nextLine();
		
		System.out.println("변경할 재고 : ");
		int stock = sc.nextInt();
		sc.nextLine();
		
//  	update 6. 입력받은 정보들, pc에 처리 요청
		pc.updatePd(product_Id, p_Name, price, description, stock);
	}
			
	

//		delete 2. 정보 삭제 서브메뉴 생성
	public void deletePd() {
		System.out.println("\n ** 탈퇴할 물품 코드 입력 ** ");
		
//		delete 3. 입력받을 id를 담을 변수 생성
		String product_Id = inputProductId();

//	 	delete 4. pc에 처리 요청
		pc.deletePd(product_Id);
	}

//		select name 2. 이름 검색결과 담을 메소드 생성
	 public String inputPdName() {
		 System.out.println("조회할 제품명 (키워드)");
		 return sc.nextLine();
		 
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
