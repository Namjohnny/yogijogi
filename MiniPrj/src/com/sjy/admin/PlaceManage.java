package com.sjy.admin;

import com.sjy.sql.PlaceSql;
import com.yogijogi.obj.ObjController;

public class PlaceManage {
	
	String food = "음식점";
	String culture = "문화체험";
	String pleasure = "유흥";
	// 등록된 핫플 관리 //---------------
	public void showList() {//===============
		System.out.println("===============핫플===============");
		System.out.println("핫플관리");
		// 핫플 테이블 가져오기 + 데이터 보여주기
		boolean wh1 = false;
		while (!wh1) {
			
			System.out.print("핫플종류: ");
			System.out.println("1.음시점 2.문화체험 3.유흥");
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
					System.out.println("---------------음식점---------------");
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
						new PlaceSql().viewPlace(food);
						
						continue;
					} else if (fc == 2) {
						System.out.println("---------------추가---------------");
						// 음식점 추가 
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
					System.out.println("---------------문화체험---------------");
					System.out.println("0.검색 1.보기 2.추가 3.수정 4.삭제 5.돌아가기");
					int spch = ObjController.scanInt();
					if (spch == 0) {
						//핫플 검색
						System.out.println("검색 화면으로");
						continue;
					}
					else if (spch == 1) {
						System.out.println("---------------보기---------------");
						System.out.println("문화체험 정보 보여주기");
						new PlaceSql().viewPlace(culture);
						continue;
					} else if (spch == 2) {
						System.out.println("---------------추가---------------");
						System.out.print("문화체험 추가: ");
						String updsp = ObjController.scanStr();
						// insertShoppingP();
						System.out.println("추가완료");
						continue;

					} else if (spch == 3) {
						System.out.println("---------------수정---------------");
						// 수정 내용 Update
						System.out.print("수정 문화체험 입력:");
						String updfc = ObjController.scanStr();
						System.out.println("수정완료");
						continue;
					} else if (spch == 4) {
						System.out.println("---------------삭제---------------");
						new PlaceSql().delPlace();
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
					System.out.println("---------------유흥---------------");
					System.out.println("0.검색 1.보기 2.추가 3.수정 4.삭제 5.돌아가기");
					int mvsh = ObjController.scanInt();
					if (mvsh == 0) {
						//핫플 검색
						System.out.println("검색 화면으로");
						continue;
					}
					else if (mvsh == 1) {
						System.out.println("---------------보기---------------");
						new PlaceSql().viewPlace(pleasure);
						continue;
					} else if (mvsh == 2) {
						System.out.println("---------------추가---------------");
						System.out.print("유흥 추가: ");
						String updfc = ObjController.scanStr();
						// 맛집추가
						// Insert
						System.out.println("추가완료");
						continue;

					} else if (mvsh == 3) {
						System.out.println("---------------수정---------------");
						// 수정 내용 Update
						System.out.print("수정 유흥 입력:");
						String updfc = ObjController.scanStr();
						System.out.println("수정완료");
						continue;
					} else if (mvsh == 4) {
						System.out.println("---------------삭제---------------");
						new PlaceSql().delPlace();
						
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

