package com.sjy.admin;

import com.sjy.sql.UserSql;
import com.yogijogi.member.Join;
import com.yogijogi.obj.ObjController;
import com.yogijogi.obj.OracleDB;

public class UserManage {
	int userno;
	UserSql userSql = new UserSql();
	public void showList() {
		System.out.println("---------------사용자---------------");
		System.out.println("사용자관리 화면");

		

		boolean wh1 = false;
		while (!wh1) {
			System.out.println("===============사용자관리===============");
			System.out.println("1.회원조회. 2.회원가입 3.회원정보수정 4.회원탈퇴 ");
			System.out.println("처음화면으로:0");
			int userch = ObjController.scanInt();
			
			if (userch == 0) {
				wh1=true;
				new Admin();
				
			} else if (userch == 1) {

				System.out.println("---------------회원조회---------------");
				// 회원정보 보여주는 메서드 (전체 or 특정회원 선택?)
				userSql.searchAll();
				continue;
			} else if (userch == 2) {
				System.out.println("---------------회원가입---------------");
				// 회원가입메서드 (회원정보 입력 및 db저장)
				new Join().joinView();
				continue;
				
			} else if (userch == 3) {

				System.out.println("---------------회원정보수정---------------");
				userSql.searchAll();
				System.out.println();
				System.out.println("-------수정내용-------");
				userSql.modUser();
				continue;
			} else if (userch == 4) {

				System.out.println("---------------회원탈퇴---------------");
				userSql.searchAll();
				System.out.print("탈퇴 회원번호:");
				System.out.println();
				int memNo = ObjController.scanInt();
				userSql.delUser(memNo);
				continue;
			}
			else {
				System.out.println("재입력");
				continue;
			}

		}
	}// showList
}// class
