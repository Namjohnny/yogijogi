package com.sjy.admin;

import com.sjy.sql.RsvSql;
import com.yogijogi.obj.ObjController;

public class RsvManage {
	// 예약관리 reservation
		public void showList() {
			System.out.println("---------------예약---------------");
			System.out.println("예약관리");

			boolean b = false;
			while (!b) {
				System.out.println("===============예약관리===============");
				System.out.println("1.예약확인| 2.예약등록| 3.예약수정| 4.예약취소|");
				System.out.println("처음화면으로:0");
				String chrsv = ObjController.scanStr();
				

				if ("0".equals(chrsv)) {
					b = true;
					new Admin();
				}

				else if ("1".equals(chrsv)) {
					System.out.println("===============예약확인===============");
					System.out.println("사용자 전체예약 정보 보여주기");
					// 예약 테이블 보여주기
					new RsvSql().RsvView();
					
					continue;
				} else if ("2".equals(chrsv)) {
					System.out.println("===============예약등록===============");
					new RsvSql().addRsv();
					continue;
				} else if ("3".equals(chrsv)) {
					System.out.println("===============예약수정===============");
					new RsvSql().modRsv();
					continue;
					
				} 
				else if ("4".equals(chrsv)) {
					System.out.println("===============예약취소===============");
					new RsvSql().RsvView();
					new RsvSql().delRsv();
					continue;
				}
				else {
					System.out.println("재입력");
					continue;
				}

			}//while b
		}//showList

}//class
