package com.sjy.admin;

import com.yogijogi.obj.ObjController;

public class UserManage {
	int userno;

	public void showList() {
		System.out.println("---------------사용자---------------");
		System.out.println("사용자관리 화면");

		System.out.println("===============사용자관리===============");
		System.out.println("1.회원조회. 2.회원가입 3.회원정보수정 4.회원탈퇴 5.");
		System.out.println("처음화면으로:0");
		int userch = ObjController.scanInt();

		boolean wh1 = false;
		while (!wh1) {

			if (userch == 0) {
				wh1=true;
				new Admin();
				
			} else if (userch == 1) {

				System.out.println("---------------회원조회---------------");
				// 회원정보 보여주는 메서드 (전체 or 특정회원 선택?)
				
				continue;
			} else if (userch == 2) {
				System.out.println("---------------회원가입---------------");
				// 회원가입메서드 (회원정보 입력 및 db저장)

				System.out.println("회원가입 완료");
				continue;
				
			} else if (userch == 3) {

				System.out.println("---------------회원정보수정---------------");
				System.out.println("수정할 회원: ");
				userno = ObjController.scanInt();

				// 회원정보수정 메서드(userno에 맞는 회원 정보 가져와서 기존데이터를 새로 입력한 데이터로 변경)

				System.out.println("수정완료");
				continue;
			} else if (userch == 4) {

				System.out.println("---------------회원탈퇴---------------");
				System.out.println("탈퇴 회원");
				userno = ObjController.scanInt();
				// 회원 탈퇴 메서드()

				System.out.println("탈퇴 완료");
				continue;
			} else if (userch == 5) {
				System.out.println("---------------회원---------------");
				continue;

			} else {
				System.out.println("재입력");
				continue;
			}

		}
	}// showList
}// class
