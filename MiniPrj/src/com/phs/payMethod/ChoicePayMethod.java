package com.phs.payMethod;

public class ChoicePayMethod {

	public static String PayNow() {
		Card cd = new Card();
		Phone pn = new Phone();
		Account at = new Account();
		boolean isNotValue = false;
		String str = null ;
		while(!isNotValue) {
		System.out.println("결제 수단을 선택해 주세요.");
		System.out.println("1. 카드결제");
		System.out.println("2. 휴대폰결제");
		System.out.println("3. 계좌이체");
		System.out.println("4. 돌아가기");
		int userInput = com.yogijogi.obj.ObjController.scanInt();
		if(userInput == 1) {
			cd.payCard();
			str = "CARD";
			isNotValue = true;
		}else if(userInput == 2) {
			pn.payPhone();
			str = "PHONE";
			isNotValue = true;
		}else if(userInput == 3) {
			at.payAccount();
			str = "ACCOUNT";
			isNotValue = true;
		}else if(userInput == 4) {
			System.out.println("이전메뉴로 돌아갑니다.");
			//이전으로 돌아가기
			isNotValue = true;
		}else {
			System.out.println("잘못된 입력입니다. 다시 선택 해 주세요.");
		}
		}
		return str;
		
	}
	
}
