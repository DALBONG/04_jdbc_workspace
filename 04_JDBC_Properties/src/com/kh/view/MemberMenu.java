package com.kh.view;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import com.kh.controller.MemberController;
import com.kh.model.vo.Member;

// View 사용자가 보게 될 시각적 요소 출력 및 입력
public class MemberMenu {

	//Scanner 객체 생성 (전역으로 다 쓸 수 있도록)
	private Scanner sc = new Scanner(System.in);
	// MemberController 객체 생성
	private MemberController mc = new MemberController();
	
	/**
	 * 사용자가 보게 될 첫 화면
	 */
	public void mainMenu() {
		while(true) {
			System.out.println("\n == 회원 관리 프로그램 ==");
			System.out.println("1. 회원 추가");
			System.out.println("2. 회원 전체 조회");
			System.out.println("3. 회원 아이디 검색");
			System.out.println("4. 회원 이름으로 키워드 검색");
			System.out.println("5. 회원 정보 변경");
			System.out.println("6. 회원 탈퇴");
			System.out.println("0. 프로그램 종료");
			
			System.out.println(" -> 메뉴 선택 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: inputMember(); break;
			case 2: mc.selectList(); break;
			case 3: //String userId = inputMemberId();
					//mc.selectByUserId(userId);
					mc.selectByUserId(inputMemberId());
					break;
			case 4: //String keyword = inputMemberName();
					//mc.selectByUserName(keyword);
					mc.selectByUserName(inputMemberName());
					break;
			case 5: updateMember();
					break;
			case 6: 
					mc.deleteMember(inputMemberId());
					break;
			case 0: System.out.println("이용 감사!");
			return;
			default : System.out.println("메뉴 잘못 썼어.. 다시 입력!");
			}
		}
	}
	
	/**
	 * 회원 추가 창 (서브 화면)
	 * 추가하고자 하는 회원 정보를 입력받아 회원주가 요청하는 창
	 */
	public void inputMember() {
		System.out.println("\n == 회원 추가 ==");
		//아이디 ~ 취미 입력 받기
		
		System.out.println("아이디 : ");
		String userId = sc.nextLine();
				
		System.out.println("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.println("이름 : ");
		String userName = sc.nextLine();
		
		System.out.println("성별(M/F) : ");
		String gender = sc.nextLine(); 
		
		System.out.println("나이 : ");
		String age = sc.nextLine();
		
		System.out.println("이메일 : ");
		String email = sc.nextLine();
		
		System.out.println("폰번호(-뺴고) : ");
		String phone = sc.nextLine();
		
		System.out.println("주소 : ");
		String address = sc.nextLine();
		
		System.out.println("취미(,로 이어서) : ");
		String hobby = sc.nextLine();
		// db에 나타내려면, controller에서 
		
		// 회원 추가 요청 == Controller 메소드 호출
		mc.insertMember(userId, userPwd, userName, gender, age, email, phone, address, hobby);
	}
	
	/**
	 * 사용자에게 회원 아이디 입력 받은 후, 그 값을 반환
	 * @return 사용자가 입력한 ID값.
	 */
	public String inputMemberId() {
		System.out.println("\n회원 아이디 입력 : ");
		return sc.nextLine();
		
	}
	
	
	public String inputMemberName() {
		System.out.println("회원 이름 입력(키워드) :");
		return sc.nextLine();
		
	}
	
	/**
	 * 사용자에게 변경할 정보들(비번,이멜,전번,주소)과 해당 회원의 아이디 입력받는 화면
	 */
	public void updateMember() {
		System.out.println("\n 회원 정보 변경");
		// 아이디 > 비번, 이멜, 전번, 주소 변경
		
		/*
		System.out.println("아이디 입력 : ");
		String userID = sc.nextLine();
		*/
		String userId = inputMemberId();
		
		System.out.println("변경할 비번 : ");
		String userPwd = sc.nextLine();
		System.out.println("변경할 이메일 : ");
		String email = sc.nextLine();
		System.out.println("변경할 번호 : ");
		String phone = sc.nextLine();
		System.out.println("변경할 주소 : ");
		String address = sc.nextLine();
		
		mc.updateMember(userId, userPwd, email, phone, address);
		
		
	}
	
	public void deleteMember() {
		System.out.println("\n 탈퇴할 아이디 입력");
		// 아이디, 비밀번호 정보 받기 -> 받았으면 정말 탈퇴할 것인지? 문구 y/n
		String userId = inputMemberId();
		
		mc.deleteMember(userId);
		
	

	}
	
	//=============================== 응답 화면 =====================================
	
	/**
	 * 서비스 요청 처리 후 성공 했을 경우 사용자가 보게될 응답 화면 
	 * @param message 성공 메시지
	 */
	public void displaySuccess(String message) {
		System.out.println("\n 서비스 요청 성공 : " + message);
		
	}
			
	/**
	 * 서비스 요청 처리 후 실패했을 경우 사용자가 보게 될 응답화면
	 * @param message 실패 메시지
	 */
	public void displayFail(String message) {
		System.out.println("\n 서비스 요청 실패 : " + message);
		
	}
			
	
	/**
	 * 조회 서비스 요청시 조회 결과가 없었을 시 사용자가 보게 될 응답화면
	 * @param message
	 */
	public void displayNoData(String message) {
		System.out.println("\n " + message);
	}
	
	/**
	 * 조회 서비스 요청시, 결과가 여러 행일 경우 사용자가 보게될 응답화면
	 * @param list
	 */
	public void displayMemberList(ArrayList<Member> list) {
		System.out.println("\n 조회된 데이터는 다음곽 같음");
		
		/*
		for (int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		*/
		
		// 향상된 FOR문
		for(Member m: list) {  //m = list.get(0) -> m=list.get(1)
			System.out.println(m);
		}
		
	}	
		/**
		 * @param m
		 */
		public void displayMember(Member m) {
			System.out.println("\n 조회된 데이터는 다음과 같습니다");
			System.out.println();
		
		
	}
	
			
	
			
			
}
