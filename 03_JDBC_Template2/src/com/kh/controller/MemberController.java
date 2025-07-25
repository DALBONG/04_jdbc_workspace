package com.kh.controller;

import java.util.ArrayList;

import com.kh.model.dao.MemberDao;
import com.kh.model.service.MemberService;
import com.kh.model.vo.Member;
import com.kh.view.MemberMenu;

//Controller : view를 통해 사용자가 요청한 기능에 대해 처리하는 담당
  			// 해당 메소드로부터 전달된 데이터 *가공처리 한 후*  Dao로 전달하면서 호출
			// Dao부터 반환받는 결과에 따라 성공, 실패인지 판단 후 응답화면 결정. (view메소드 호출)

public class MemberController {

	/**
	 * 사용자의 회원 추가 요청을 처리해주는 메소드
	 * @param userId ~ hobby : 사용자가 입력한 정보들이 담겨있는 매개변수
	 *
	 */
	public void insertMember(String userId, String userPwd, String userName, String gender, String age, String email, String phone, String address, String hobby) {
		
		// 받은 값들을 데이터를 직접적으로 처리해주는 Dao로 넘기기 (담아서 전달)
		// Member 객체에 담아서
		
		// 방법1) 기본 생성자로 생성한 후 각 필드에 값을 setter 메소드를 통해 일일히 담는 법
				// -> 매개 변수가 별로 없을 때
		// 방법2) 매개변수 생성자를 통해 생성과 동시에 담는 법.
		
		Member m = new Member(userId, userPwd, userName, gender, Integer.parseInt(age), email, phone, address, hobby);
			// 9개 짜리의 생성자가 없으므로 Member Class로 넘어가서 9개짜리 생성자 생성
		     // -- alt shift s, constructor using feild

		int result = new MemberService().insertMember(m);
		
		if(result > 0 ) {
			new MemberMenu().displaySuccess("성공적으로 추가 되었음!");
		}else {
			new MemberMenu().displayFail("실패적으로 추가되지 않았음!");
		}
		
	}
	
	/**
	 * 회원 전체조회 처리 메소드
	 * 조회, dao에서 바로 보냄.
	 */
	public void selectList() {
		ArrayList<Member> list = new MemberService().selectList();
	
		if(list.isEmpty()) {  // 비어있을 경우 (조회된 데이터 X )
			new MemberMenu().displayNoData("전체 조회 결과가 없음다 ");
		}else {  // 조회된 데이터 O
			new MemberMenu().displayMemberList(list);
		}
	}
	
		
	public void selectByUserId(String userId){
		Member m = new MemberService().selectByUserId(userId);
		
		if(m == null) { // 검색 결과 없을 시 (조회 데이터 없음)
			new MemberMenu().displayNoData(userId + "에 해당하는 검색결과가 없슴다");
		}else {
			new MemberMenu().displayMember(m);
			
			
		}
		
	}
	
	public void selectByUserName(String keyword) {
		ArrayList<Member> list = new MemberService().selectByUserName(keyword);
		
		if(list.isEmpty()) {
			new MemberMenu().displayNoData(keyword + "에 해당하는 결과 없음");
		}else {
			new MemberMenu().displayMemberList(list);
		}
		
	}
	
	
	/**
	 * 정보 변경 요청 처리해주는 메소드 
	 * @param userId : 변경할 정보의 회원 아이디
	 * @param userPwd : 변경할 비번
	 * @param email : 변경할 메일
	 * @param phone : 변경할 번호
	 * @param address : 변경할 주소 
	 */
	public void updateMember(String userId, String userPwd, String email, String phone, String address) {
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		
		// 멤버dao에 updateMember 보낼꺼야
		int result = new MemberService().updateMember(m);
		// 자료형
		
		if(result > 0) {
			new MemberMenu().displaySuccess("성공적으로 정보 변경 완료!");
		}else {
			new MemberMenu().displayFail("정보변경 실패!");
		}
	}	
	
	public void deleteMember(String userId) {
		int result = new MemberService().deleteMember(userId);
	
		if(result > 0) {
			new MemberMenu().displaySuccess("탈퇴 성공!");
		}else {
			new MemberMenu().displayFail("탈퇴 실패!");
		}
	
		
		
	}

	
	
}
