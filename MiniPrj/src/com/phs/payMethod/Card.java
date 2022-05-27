package com.phs.payMethod;

public class Card {

	public void payCard() {
		boolean isNotValue = false;
		while(!isNotValue) {
		System.out.println("=============== 카드결제 ===============");
		System.out.println("결제 하실 금액은 : " + "?" + "입니다.");
		System.out.println("결제 하시겠습니까? (Y/N)");
		String userInput = com.yogijogi.obj.ObjController.scanStr();
		if(userInput.equals('Y')) {
			System.out.println("결제가 완료되었습니다.");
			isNotValue = true;
		}else if(userInput.equals('N')) {
			System.out.println("메인 메뉴로 돌아갑니다.");
			isNotValue = true;
		}else {
			System.out.println("잘못된 입력입니다.");
		}
		}
		//메인메뉴로 돌아가는 메소드
	}
	
}
