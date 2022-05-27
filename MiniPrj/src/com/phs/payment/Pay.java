package com.phs.payment;

import java.sql.Connection;

public class Pay {

	public static void payNow01() {
		System.out.println("============ VIP 등급 결제 페이지 ============");
		System.out.println("결제 하실 금액은 30,000원 입니다.");
		
		com.phs.payMethod.ChoicePayMethod.PayNow();
		
		Connection conn = com.yogijogi.obj.OracleDB.getOracleConnection();
		
	}
	public static void payNow02() {
		System.out.println("============ 예약 결제 페이지 ============");
		System.out.println("결제 하실 금액은 " +"00원" + " 입니다.");
		
		com.phs.payMethod.ChoicePayMethod.PayNow();
	}
	
}
