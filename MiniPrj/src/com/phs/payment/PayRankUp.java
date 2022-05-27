package com.phs.payment;

public class PayRankUp {

	// 등급별 결제 금액
	// 
	
	public void vipRankUp() {
		boolean isNotValue = false;
		while(!isNotValue) {
				System.out.println("VIP 등급");
				System.out.println("결제 금액 : 30,000원");
				System.out.println("1. 광고 제거");
				System.out.println("2. 10% 적립금");
				System.out.println("등급을 up 하시겠습니까? (Y/N)");
				String userInput = InputUtil.inputStr();
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
	
	
//	public void silverRankUp() {
//		boolean isNotValue = false;
//		while(!isNotValue) {
//				System.out.println("실버 등급");
//				System.out.println("1. 광고 제거");
//				System.out.println("2. 6% 적립금");
//				System.out.println("3. 리뷰 등록시 적립금 1,000원");
//				System.out.println("4. 리뷰 보기 10개");
//				System.out.println("등급을 up 하시겠습니까? (Y/N)");
//				String userInput = InputUtil.inputStr();
//			if(userInput.equals("Y")) {
//				isNotValue = true;
//			}else if(userInput.equals("N")) {
//				System.out.println("이전메뉴로 돌아갑니다.");
//				RankUp();
//			}else {
//				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
//			}
//		}
//	}
//	
//	public void bronzeRankUp() {
//		boolean isNotValue = false;
//		while(!isNotValue) {
//				System.out.println("브론즈 등급");
//				System.out.println("1. 3% 적립금");
//				System.out.println("3. 리뷰 등록시 적립금 500원");
//				System.out.println("4. 리뷰 보기 5개");
//				System.out.println("등급을 up 하시겠습니까? (Y/N)");
//				String userInput = InputUtil.inputStr();
//			if(userInput.equals("Y")) {
//				isNotValue = true;
//			}else if(userInput.equals("N")) {
//				System.out.println("이전메뉴로 돌아갑니다.");
//				RankUp();
//			}else {
//				System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
//			}
//		}
//	}
