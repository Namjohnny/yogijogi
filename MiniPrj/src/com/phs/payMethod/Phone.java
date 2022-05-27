package com.phs.payMethod;

public class Phone {

	public void payPhone() {
		boolean isNotValue = false;
		while(!isNotValue) {
		System.out.println("=============== 휴대폰결제 ===============");
		System.out.println("결제 하실 금액은 : " + "?" + "입니다.");
		System.out.println("결제 하시겠습니까? (Y/N)");
		String userInput = com.yogijogi.obj.ObjController.scanStr();
		if(userInput.equals('Y')) {
			System.out.println("핸드폰 번호를 입력바랍니다.");
			isNotValue = true;
		}else if(userInput.equals('N')) {
			System.out.println("메인 메뉴로 돌아갑니다.");
			isNotValue = true;
		}else {
			System.out.println("잘못된 입력입니다.");
		}
		}
	}
	
}
