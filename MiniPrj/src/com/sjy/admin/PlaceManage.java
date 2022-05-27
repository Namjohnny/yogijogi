package com.sjy.admin;

import com.sjy.sql.PlaceSql;
import com.yogijogi.obj.ObjController;

public class PlaceManage {

	// 등록된 핫플 관리 //---------------
	public void showList() {//===============
		System.out.println("===============핫플===============");
		System.out.println("핫플관리");
		// 핫플 테이블 가져오기 + 데이터 보여주기
		boolean wh1 = false;
		while (!wh1) {
			
			System.out.print("핫플종류: ");
			System.out.println("1.맛집 2.쇼핑몰 3.영화...");
			System.out.println("처음화면으로:0");
			System.out.print("선택:");
			int place = ObjController.scanInt();

			System.out.println(place + "번을 선택 하였습니다.");
			boolean wh2 = false;
			if (place == 0) {
				wh2 = true;
				wh1 =true; 
				new Admin();
			}

			while (!wh2) {
				switch (place) {
				case 1: {
					System.out.println("---------------맛집---------------");
					System.out.println("0.검색 1.보기 2.추가 3.수정 4.삭제 5.돌아가기");
					int fc =ObjController.scanInt();
					if (fc == 0) {
						//핫플 검색
						//searchFoodPlacce();
						System.out.println("검색 화면으로");
						continue;
					}
					else if (fc == 1) {
						System.out.println("---------------보기---------------");
						System.out.println("맛집 정보 보여주기");
						new PlaceSql().viewPlace();
						
						continue;
					} else if (fc == 2) {
						System.out.println("---------------추가---------------");
						// 맛집추가
						new PlaceSql().addPlace1();
						new PlaceSql().addPlace2();
						continue;

					} else if (fc == 3) {
						System.out.println("---------------수정---------------");
						// 수정 내용 Update
						new PlaceSql().modPlace();
						continue;
					} else if (fc == 4) {
						System.out.println("---------------삭제---------------");
						new PlaceSql().delPlace();
						continue;
					}

					else if (fc == 5) {
						System.out.println("이전으로");
						System.out.println("===============이전===============");
						wh2 = true;
						break;
					}

					else {
						System.out.println("재입력");
						continue;
					}

				} // case 1

				case 2: {
					System.out.println("---------------쇼핑---------------");
					System.out.println("0.검색 1.보기 2.추가 3.수정 4.삭제 5.돌아가기");
					int spch = ObjController.scanInt();
					if (spch == 0) {
						//핫플 검색
						System.out.println("검색 화면으로");
						continue;
					}
					else if (spch == 1) {
						System.out.println("---------------보기---------------");
						System.out.println("쇼핑몰 정보 보여주기");
						//showShoppingP();
						continue;
					} else if (spch == 2) {
						System.out.println("---------------추가---------------");
						System.out.print("쇼핑몰 추가: ");
						String updsp = ObjController.scanStr();
						// insertShoppingP();
						System.out.println("추가완료");
						continue;

					} else if (spch == 3) {
						System.out.println("---------------수정---------------");
						// 수정 내용 Update
						System.out.print("수정 쇼핑몰 입력:");
						String updfc = ObjController.scanStr();
						System.out.println("수정완료");
						continue;
					} else if (spch == 4) {
						System.out.println("---------------삭제---------------");
						System.out.print("삭제 쇼핑몰 입력:");
						String updfc = ObjController.scanStr();
						
						// deleteShoppingP();
						System.out.println("삭제완료");
						continue;
					}

					else if (spch == 5) {
						System.out.println("이전으로");
						System.out.println("===============이전===============");
						wh2 = true;
						break;
					}

					else {
						System.out.println("재입력");
						continue;
					}

				} // case 2
				case 3: {
					System.out.println("---------------영화---------------");
					System.out.println("0.검색 1.보기 2.추가 3.수정 4.삭제 5.돌아가기");
					int mvsh = ObjController.scanInt();
					if (mvsh == 0) {
						//핫플 검색
						System.out.println("검색 화면으로");
						continue;
					}
					else if (mvsh == 1) {
						System.out.println("---------------보기---------------");
						System.out.println("영화관 정보 보여주기");
						continue;
					} else if (mvsh == 2) {
						System.out.println("---------------추가---------------");
						System.out.print("영화관 추가: ");
						String updfc = ObjController.scanStr();
						// 맛집추가
						// Insert
						System.out.println("추가완료");
						continue;

					} else if (mvsh == 3) {
						System.out.println("---------------수정---------------");
						// 수정 내용 Update
						System.out.print("수정 영화관 입력:");
						String updfc = ObjController.scanStr();
						System.out.println("수정완료");
						continue;
					} else if (mvsh == 4) {
						System.out.println("---------------삭제---------------");
						System.out.print("삭제 영화관 입력:");
						String updfc = ObjController.scanStr();
						// 입력받은 맛집 delete
						System.out.println("삭제완료");
						continue;
					}

					else if (mvsh == 5) {
						System.out.println("이전으로");
						System.out.println("===============이전===============");
						wh2 = true;
						break;
					}

					else {
						System.out.println("재입력");
						continue;
					}

				} // case 3
				default:
				}// switch

				System.out.println("");

			} // whlie 2
		} // while 1

	}// showList
}

