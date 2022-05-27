package com.sgs.help;

import java.io.IOException;
import java.util.Scanner;

import com.yogijogi.obj.ObjController;

public class Help {
	Scanner sc = new Scanner(System.in);
	private User user;
	
	public Help(User user) throws IOException {
		this.user = user;
		if(user.getRank().equals("helper")) new Communication(user).server();
		else showList();
	}
	
	public void showList() {
		System.out.println("=====고객센터입니다=====");
		while(true) {
			System.out.println("1. 챗봇");
			System.out.println("2. 상담사 연결");
			System.out.println("0. 고객센터 서비스 종료");
			int n = sc.nextInt();
			if(n==1) botChat();
			else if(n==2) {
				realChat();
				break;
			}
			else if(n==0) {
				System.out.println("고객센터 서비스가 종료되었습니다");
				break;
			}
			else System.out.println("잘못입력되었습니다. 다시 선택해주세요");
		}
	}
	
	public void botChat() {

		while(true) {
			System.out.println("무엇을 도와드릴까요?");
			System.out.println("1.회원가입");
			System.out.println("2.로그인");
			System.out.println("3.예약");
			System.out.println("4.광고");
			System.out.println("5.결제");
			System.out.println("6.기타");
			System.out.println("7.상담사 연결");
			System.out.println("0.고객센터 서비스 종료");
			int n = ObjController.scanInt();
				 if(n==1) {
				System.out.println("1.가입이 안돼요");
				System.out.println("2.등급이 따로 있나요?");
				commitIssue();
			}
			else if(n==2) {
				System.out.println("1.로그인이 안돼요");
				System.out.println("2.등급이 따로 있나요?");
				commitIssue();
			}
			else if(n==3) {
				System.out.println("1.예약확인이 안돼요");
				System.out.println("2.예약취소가 안돼요");
				commitIssue();
			}
			else if(n==4) {
				System.out.println("1.광고를 그만 보고 싶어요");
				System.out.println("1.광고를 더 보고 싶어요");
				commitIssue();
			}
			else if(n==5) {
				System.out.println("1.결제수단을 바꾸고 싶어요");
				System.out.println("2.결제확인은 어떻게 하나요?");
				commitIssue();
			}
			else if(n==6) {
				commitIssue();
			}
			else if(n==7) {
				System.out.println("상담사로 연결하겠습니다. 잠시만 기다려주세요");
				realChat();
			}
			else if(n==0) {
				System.out.println("고객센터 서비스가 종료되었습니다");
				break;
			}
			else System.out.println("잘못입력되었습니다. 다시 선택해주세요");
		}
	}
	public void realChat() {
		try {
			new Communication(user).client();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void commitIssue() {
		ObjController.scanInt();
		System.out.println("===================");
		System.out.println("저런, 저도 잘 모르겠네요");
		System.out.println("어떤 문제인지 상세히 작성해주세요");
		System.out.print(">>>");
		String claimMsg = ObjController.scanStr();
		System.out.println("작성감사합니다. 확인 후 조치하겠습니다");
		System.out.println("===================");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
