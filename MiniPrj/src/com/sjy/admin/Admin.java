package com.sjy.admin;


import com.sgs.manager.PayManager;
import com.sgs.manager.ReviewManager;
import com.yogijogi.main.Main;
import com.yogijogi.member.MyPage;
import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;


public class Admin {

	public Admin() {
//		System.out.println("===============Time===============");
//		Date today = new Date();
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
//
//		System.out.println("Date: " + date.format(today));
//		System.out.println("Time: " + time.format(today));
//		System.out.println("===== 관리자 =====");
		
		boolean wh1 = false;
		while(!wh1) {
		System.out.println("관리자 페이지");
		System.out.println("1. 핫플관리");
		System.out.println("2. 사용자관리");
		System.out.println("3. 예약관리");
		System.out.println("4. 광고관리");
		System.out.println("5. 리뷰관리");
		System.out.println("6. 로그아웃");
		System.out.println("0. 종료");

		System.out.print("번호선택: ");
		String Choice = ObjController.scanStr();
		
		if ("0".equals(Choice)) {
			System.out.println("종료");
			System.exit(0);
		} // 콘솔 종료
		else if("1".equals(Choice)) {new PlaceManage().showList();}
		else if("2".equals(Choice)) {new UserManage().showList();}
		else if("3".equals(Choice)) {new RsvManage().showList();}
		else if("4".equals(Choice)) {new PayManager().showList();}
		else if("5".equals(Choice)) {new ReviewManager().showList();}
		else if("6".equals(Choice)) {logout();wh1=true;}
		else if("".equals(Choice) ) {System.out.println("숫자를 입력바랍니다.\n");continue;}
		else {System.out.println("숫자를 다시 입력해주세요\n"); continue;}
		}
	}
	
	private void logout() {
		User.LoginUserNo = -1;
		System.out.println("로그아웃 되었습니다!");
	}
}// class
