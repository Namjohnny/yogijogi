package com.phs.payment;

public class PayRankUp {

	// ��޺� ���� �ݾ�
	// 
	
	public void vipRankUp() {
		boolean isNotValue = false;
		while(!isNotValue) {
				System.out.println("VIP ���");
				System.out.println("���� �ݾ� : 30,000��");
				System.out.println("1. ���� ����");
				System.out.println("2. 10% ������");
				System.out.println("����� up �Ͻðڽ��ϱ�? (Y/N)");
				String userInput = InputUtil.inputStr();
			if(userInput.equalsIgnoreCase("Y")) {
				Pay.payNow01();
				isNotValue = true;
				// ���� �ȳ��������� �̵�
			}else if(userInput.equalsIgnoreCase("N")) {
				System.out.println("�����޴��� ���ư��ϴ�.");
				return;
			}else {
				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �������ּ���.");
			}
		}
		
	}
	
	
//	public void silverRankUp() {
//		boolean isNotValue = false;
//		while(!isNotValue) {
//				System.out.println("�ǹ� ���");
//				System.out.println("1. ���� ����");
//				System.out.println("2. 6% ������");
//				System.out.println("3. ���� ��Ͻ� ������ 1,000��");
//				System.out.println("4. ���� ���� 10��");
//				System.out.println("����� up �Ͻðڽ��ϱ�? (Y/N)");
//				String userInput = InputUtil.inputStr();
//			if(userInput.equals("Y")) {
//				isNotValue = true;
//			}else if(userInput.equals("N")) {
//				System.out.println("�����޴��� ���ư��ϴ�.");
//				RankUp();
//			}else {
//				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �������ּ���.");
//			}
//		}
//	}
//	
//	public void bronzeRankUp() {
//		boolean isNotValue = false;
//		while(!isNotValue) {
//				System.out.println("����� ���");
//				System.out.println("1. 3% ������");
//				System.out.println("3. ���� ��Ͻ� ������ 500��");
//				System.out.println("4. ���� ���� 5��");
//				System.out.println("����� up �Ͻðڽ��ϱ�? (Y/N)");
//				String userInput = InputUtil.inputStr();
//			if(userInput.equals("Y")) {
//				isNotValue = true;
//			}else if(userInput.equals("N")) {
//				System.out.println("�����޴��� ���ư��ϴ�.");
//				RankUp();
//			}else {
//				System.out.println("�߸��� �Է��Դϴ�. �ٽ� �������ּ���.");
//			}
//		}
//	}
