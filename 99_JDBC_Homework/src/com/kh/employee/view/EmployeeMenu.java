package com.kh.employee.view;

import java.util.Scanner;

import com.kh.employee.controller.EmployeeController;
import com.kh.employee.model.vo.Employee;

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
			case 1 : insertEmp(); break;
			case 2 : break;
			case 3 : break;
			case 4 : break;
			case 0 : System.out.println("프로그램 종료, 감사함다!");
			return;
			default : System.out.println("잘못 누르셨슴다!");
			}
		}
	}	
		
	public void insertEmp() {
			//직원명, 주민번호, 이메일, 전번, 부서코드, 직급코드, 급여등급, 급여
			System.out.println("\n 추가할 사원정보 입력!");
			
			System.out.println("추가할 직원 이름 : ");
			String emp_Name = sc.nextLine();
			
			System.out.println("주민등록번호 : ");
			String emp_No = sc.nextLine();
			
			System.out.println("이메일 : ");
			String email = sc.nextLine();
			
			System.out.println("휴대폰 번호 : ");
			String phone = sc.nextLine();
			
			System.out.println("부서 코드 : ");
			String dept_Code = sc.nextLine();
			
			System.out.println("직급 코드 : ");
			String job_Code = sc.nextLine();
			
			System.out.println("급여 등급 : ");
			String sal_Level = sc.nextLine();
			
			System.out.println("급여 : ");
			int salary = sc.nextInt();
			
			
			
			ec.insertEmp(emp_Name, emp_No, email, phone, dept_Code, job_Code, sal_Level, salary);
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
