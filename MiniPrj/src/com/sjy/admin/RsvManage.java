package com.sjy.admin;

import com.yogijogi.obj.ObjController;

public class RsvManage {
	// 예약관리 reservation
		public void showList() {
			System.out.println("---------------예약---------------");
			System.out.println("예약관리");

			boolean b = false;
			while (!b) {
				System.out.println("===============예약관리===============");
				System.out.println("1.예약확인 2.예약수정 3.예약삭제");
				System.out.println("처음화면으로:0");
				int chrsv = ObjController.scanInt();

				if (chrsv == 0) {
					b = true;
					new Admin();
				}

				else if (chrsv == 1) {
					System.out.println("===============예약확인===============");
					System.out.println("사용자 전체예약 정보 보여주기");
					// 예약 테이블 보여주기
					continue;
				} else if (chrsv == 2) {
					System.out.println("===============예약수정===============");

					System.out.print("수정 예약 번호:");
					int rsvUdN = ObjController.scanInt();
					// 수정 함수
					// 예약수정
					System.out.println("예약수정중");
					System.out.println("수정완료");
					continue;

				} else if (chrsv == 3) {
					System.out.println("===============예약삭제===============");
					// 수정 내용 Update
					System.out.print("삭제 예약 번호:");
					String rsvDtN = ObjController.scanStr();
					System.out.println("삭제 완료");
					continue;
				}

				else {
					System.out.println("재입력");
					continue;
				}

			}//while b
		}//showList

}//class
