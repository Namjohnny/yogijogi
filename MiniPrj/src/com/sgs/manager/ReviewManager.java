package com.sgs.manager;

import java.util.Scanner;

public class ReviewManager {
	Scanner sc = new Scanner(System.in);
	
	public void showList() {
		System.out.println("=====리뷰관리=====");
		while(true) {
			System.out.println("1.리뷰조회");
			System.out.println("2.리뷰추가");
			System.out.println("3.리뷰수정");
			System.out.println("4.리뷰삭제");
			System.out.println("0.리뷰관리 종료");
			int n = sc.nextInt();
				 if(n==1) searchReview();
			else if(n==2) addReview();
			else if(n==3) modReview();
			else if(n==4) delReview();
			else if(n==0) {
				System.out.println("결제관리 종료");
				break;
			}
			else System.out.println("잘못입력되었습니다. 다시 선택해주세요");
		}
	}
	public void searchReview() {
		
	}
	public void addReview() {
		
	}
	public void modReview() {
		
	}
	public void delReview() {
		
	}
}
