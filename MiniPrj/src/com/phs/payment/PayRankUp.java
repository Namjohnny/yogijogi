package com.phs.payment;

public class PayRankUp {

	public void vipRankUp() {
		boolean isNotValue = false;
		while(!isNotValue) {
				System.out.println("VIP 등급");
				System.out.println("결제 금액 : 30,000원");
				System.out.println("1. 광고 제거");
				System.out.println("2. 10% 적립금");
				System.out.println("등급을 up 하시겠습니까? (Y/N)");
				String userInput = com.yogijogi.obj.ObjController.scanStr();
			if(userInput.equalsIgnoreCase("Y")) {
				Pay.payNow01();
				isNotValue = true;
				// 결제 안내페이지로 이동
			}else if(userInput.equalsIgnoreCase("N")) {
				System.out.println("이전메뉴로 돌아갑니다.");
				return;
			}else {
				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
			}
		}
		
	}
	
}
