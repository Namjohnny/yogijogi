package com.yogijogi.main;

import java.io.IOException;

import com.sgs.help.Help;
import com.sjy.admin.Admin;
import com.yogijogi.hotplace.HotPlace;
import com.yogijogi.member.Join;
import com.yogijogi.member.Login;
import com.yogijogi.member.MyPage;
import com.yogijogi.member.User;
import com.yogijogi.obj.ObjController;

public class Main {

	public static void main(String[] args) {

		boolean mainList = true;
		Login login = new Login();
		Join join = new Join();
		MyPage page = new MyPage();
		HotPlace hp = new HotPlace();
		String rank = "";
		User user = null;
		int listNum;
		
		while(mainList) {
			System.out.println("===== 요기조기 =====");
			if(User.LoginUserNo == -1) {
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("3. 종료");
				System.out.print("번호 입력 >> ");
				listNum = ObjController.scanInt();
				
				switch(listNum) {
				case 1 :
					//로그인
					user = login.loginView();
					if(user == null) break;
					rank = user.getRank();
					break;
				case 2 :
					//회원가입
					join.joinView();
					break;
				case 3 :
					//종료
					mainList = false;
					break;
				default :
					System.out.println("번호를 잘못 입력하였습니다.");
				}
			}
			else if(rank.equals("A")) {
				// 관리자용 메뉴
				new Admin();
			}
			else {
				System.out.println("1. 마이페이지");
				System.out.println("2. 서울 핫플");
				System.out.println("3. 핫플 검색");
				System.out.println("4. 리뷰 게시판");
				System.out.println("5. 고객 센터");
				System.out.println("6. 종료");
				System.out.print("번호 입력 >> ");
				listNum = ObjController.scanInt();
				
				switch(listNum) {
				case 1 :
					//마이페이지
					page.showPage(user);
					break;
				case 2 :
					//서울 핫플 전부 출력
					break;
				case 3 :
					//서울 핫플 검색하기
					hp.searchPlace();
					break;
				case 4 :
					//리뷰 게시판
					// ReviewMain을 main문이 아니라 메소드로 수정 후 해당 메소드 이곳에 넣기
				case 5 :
					//고객 센터
					try {
						new Help(user);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				case 6 :
					// 종료
					mainList = false;
					break;
				default :
					System.out.println("번호를 잘못 입력하였습니다.");
				}
				
				
			}
			
		}
		
	}

}
