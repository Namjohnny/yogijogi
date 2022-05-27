package com.sjy.user;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yogijogi.obj.ObjController;

public class UserView {

	public UserView() {
		System.out.println("===============Time===============");
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");

		System.out.println("Date: " + date.format(today));
		System.out.println("Time: " + time.format(today));

		String id = Login.id;// id값 넘겨받기
		System.out.println("userId: " + id + "님 환영합니다.");

		boolean wh1 = false;
		while (!wh1) {
			
			System.out.println("===============사용자===============");
			System.out.println("id:" + id);
			System.out.println("다음 항목중 선택해주세요");
			System.out.println("1.검색");
			System.out.println("2.예약 확인");
			System.out.println("3.도움말");
			System.out.println("4.예약");
			System.out.println("5.광고삭제");
			System.out.println("6.회원 탈퇴");
			System.out.println("0. 종료");
			
			System.out.print("번호선택: ");
			int userChoice = ObjController.scanInt();
			User ui = new User();

			
			if (userChoice == 0) {
				System.out.println("종료");
				System.exit(0);
			} // 콘솔 종료
			else if(userChoice==1) {ui.connSearch();}
			else if(userChoice==2) {ui.connCheck();}
			else if(userChoice==3) {ui.connHelp();}
			else if(userChoice==4) {ui.connRsv();}
			else if(userChoice==5) {ui.delAd();}
			else if(userChoice==6) {ui.delUser();}
			else {System.out.println("숫자를 다시 입력해주세요");}
			
			
			
		}
	}

}// class
