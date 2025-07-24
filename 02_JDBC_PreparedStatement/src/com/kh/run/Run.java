package com.kh.run;

import com.kh.view.MemberMenu;

public class Run {

	public static void main(String[] args) {

		/*
		 * MVC 패턴
		   - M(model) : 데이터 처리 담당 (데이터들을 담기 위한 클래스(vo)),
		    		  데이터들이 보관된 공간과 직접적으로 접근해서 데이터 주고받기(DAO)
		   - V(view) : 사용자가 보는 화면 담당 (사용자가 보게되는 시각적 요소)
		    		  출력, 입력문만 
		   - C(controller) : 사용자의 요청을 처리 (사용자의 요청처리 후, 해당하는 응답화면 지정)
		   
		   + Run : 프로그램 실행만 담당 (사용자가 보게될 첫 화면만 띄워주고 끝)
		 */
	
		
		new MemberMenu().mainMenu();
	}

}
