package com.phs.payMethod;

public class Account {

	// 관리자 계좌명 출력
		// 계좌이체 기간 고지
		
		public void payAccount() {
			boolean isNotValue = false;
			while(!isNotValue) {
			System.out.println("=============== 계좌이체 ===============");
			System.out.println("결제 하실 금액은 : " + "?" + "입니다.");
			System.out.println("결제 하시겠습니까? (Y/N)");
			String userInput = com.yogijogi.obj.ObjController.scanStr();
			if(userInput.equals('Y')) {
				System.out.println("결제금액을 농협 123-543-12345678 로 입금바랍니다.");
				System.out.println("오늘로부터 2일 경과 후 미 결제시 자동 취소됩니다.");
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
