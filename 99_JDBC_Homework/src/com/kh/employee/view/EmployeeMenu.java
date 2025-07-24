package com.kh.employee.view;

import java.util.Scanner;

import com.kh.employee.controller.EmployeeController;

public class EmployeeMenu {

	private Scanner sc = new Scanner(System.in);
	private EmployeeController ec = new EmployeeController();	
	
	/**
	 * 메인 화면
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("\n ** 사원 정보 조회 프로그램 ** ");
			System.out.println("\n 1. 사원 추가");
			System.out.println("\n 2. 사원 전체 조회");
			System.out.println("\n 3. 사원 정보 수정");
			System.out.println("\n 4. 사원 정보 삭제");
			System.out.println("\n 0. 프로그램 종료");
			
			System.out.println("** 메뉴 션택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1 : createEmp(); break;
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 0 : System.out.println("프로그램 종료, 감사함다!");
			return;
			default : System.out.println("잘못 누르셨슴다!");
			}
			
		}
		
		
		public void createEmp() {
			
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
