package com.sjy.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yogijogi.obj.ObjController;


public class Admin {

	public Admin() {
		System.out.println("===============Time===============");
		Date today = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");

		System.out.println("Date: " + date.format(today));
		System.out.println("Time: " + time.format(today));
		System.out.println("===============관리자===============");
		
		System.out.println("관리자 페이지");
		System.out.println("1. 핫플관리");
		System.out.println("2. 사용자관리");
		System.out.println("3. 예약관리");
		System.out.println("4. 광고관리");
		System.out.println("5. 리뷰관리");
		System.out.println("0. 종료");

		System.out.print("번호선택: ");
		int Choice = ObjController.scanInt();
		
		if (Choice == 0) {
			System.out.println("종료");
			System.exit(0);
		} // 콘솔 종료
		else if(Choice==1) {new PlaceManage().showList();}
		else if(Choice==2) {new UserManage().showList();}
		else if(Choice==3) {new RsvManage().showList();}
		else if(Choice==4) {}//new PayManager().showList();
		else if(Choice==5) {}//new ReviewManager().showList();
		else {System.out.println("숫자를 다시 입력해주세요");}
		
	}

}// class
