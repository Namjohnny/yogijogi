package com.sgs.manager;

import java.util.Scanner;

public class PayManager {
	Scanner sc = new Scanner(System.in);
	
	public void showList() {
		System.out.println("=====결제관리=====");
		while(true) {
			System.out.println("1.결제조회");
			System.out.println("2.결제추가");
			System.out.println("3.결제수정");
			System.out.println("4.결제삭제");
			System.out.println("0.결제관리 종료");
			int n = sc.nextInt();
				 if(n==1) searchPay();
			else if(n==2) addPay();
			else if(n==3) modPay();
			else if(n==4) delPay();
			else if(n==0) {
				System.out.println("결제관리 종료");
				break;
			}
			else System.out.println("잘못입력되었습니다. 다시 선택해주세요");
		}
	}
	public void searchPay() {
		
	}
	public void addPay() {
		
	}
	public void modPay() {
		
	}
	public void delPay() {
		
	}
}
