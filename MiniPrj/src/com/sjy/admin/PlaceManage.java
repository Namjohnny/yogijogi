package com.sjy.admin;

import java.util.ArrayList;

import com.sjy.sql.PlaceSql;
import com.yogijogi.obj.ObjController;

public class PlaceManage {

	String food = "음식점";
	String culture = "문화체험";
	String pleasure = "유흥";

	// 등록된 핫플 관리 //---------------
	public void showList() {// ===============
		System.out.println("===============핫플===============");
		System.out.println("핫플관리");
		// 핫플 테이블 가져오기 + 데이터 보여주기
		boolean wh1 = false;
		boolean wh2 = false;
		while (!wh1) {
			System.out.println("--------------------------------");
			System.out.println("※등록,수정,삭제는 분류별로 입력됩니다.");
			System.out.println("1.핫플검색 | 2.핫플조회 | 3.핫플등록 | 4. 핫플수정| 5.핫플삭제 ");
			System.out.println("처음화면으로:0");

			System.out.print("선택: ");
			int ch = ObjController.scanInt();
			if (ch == 0) {
				// wh2 = true;
				wh1 = true;
				new Admin();
			} else if (ch == 1) {
				System.out.println("검색화면으로이동");
				// new Search();
			} else if (ch == 2) {
				searchPlace();
			} else if (ch == 3) {
				// 핫플등록
				System.out.println("1.음식점 2.문화체험 3.유흥");
				int inch = ObjController.scanInt();
				addPlace(inch);
			} else if (ch == 4) {
				// 핫플수정
				System.out.println("1.음식점 2.문화체험 3.유흥");
				int modch = ObjController.scanInt();
				modPlace(modch);
			} else if (ch == 5) {
				// 핫플삭제
				System.out.println("1.음식점 2.문화체험 3.유흥");
				int delch = ObjController.scanInt();
				delPace(delch);
			}

			else {
				System.out.println("번호를 확인하고 다시 입력해주세요");
				return;
			} // ch에 관한 else

		} // while 1

	}// showList

	public void searchPlace() {
		System.out.println("---------------검색---------------");
		System.out.println("1.모두 2.구별 3.분류별 ");
		int ch2 = ObjController.scanInt();
		if (ch2 == 1) {
			view();
		} else if (ch2 == 2) {
			System.out.print("구입력: ");
			String lc = ObjController.scanStr();
			view(lc);
		} else if (ch2 == 3) {
			System.out.println("1.음식점 2.문화체험 3.유흥");
			int shch = ObjController.scanInt();
			search(shch);
			
		}

	}
	//검색
	public void search(int shch) {
		if (shch == 1) {
			System.out.println("음식점 정보 보여주기");
			new PlaceSql().showList(food);
		} else if (shch == 2) {
			System.out.println("문화체험 정보 보여주기");
			new PlaceSql().showList(culture);
		} else if (shch == 3) {
			System.out.println("유흥 정보 보여주기");
			new PlaceSql().showList(pleasure);
		}else {System.out.println("번호를 다시 입력해주세요"); return;}
	}
	
	// 등록
	public void addPlace(int inch) {
		
		if (inch == 1) {
			System.out.println("---------------음식추가---------------");
			new PlaceSql().addFood(food);
		} else if (inch == 2) {
			System.out.println("---------------문화추가---------------");
			new PlaceSql().addCulture(culture);
		} else if (inch == 3) {
			System.out.println("---------------유흥추가---------------");
			new PlaceSql().addP(pleasure);
		}else {System.out.println("번호를 다시 입력해주세요"); return;}
	}

	public void modPlace(int upch) {
		if (upch == 1) {
			System.out.println("---------------음식수정---------------");
			new PlaceSql().modPlace(food);
		} else if (upch == 2) {
			System.out.println("---------------문화수정---------------");
			new PlaceSql().modPlace(culture);
		} else if (upch == 3) {
			System.out.println("---------------유흥수정---------------");
			new PlaceSql().modPlace(pleasure);
		}else {System.out.println("번호를 다시 입력해주세요"); return;}
	}

	public void delPace(int delch) {
		if (delch == 1) {
			System.out.println("---------------음식삭제---------------");
			new PlaceSql().delPlace(food);
		} else if (delch == 2) {
			System.out.println("---------------문화삭제---------------");
			new PlaceSql().delPlace(culture);
		} else if (delch == 3) {
			System.out.println("---------------유흥삭제---------------");
			new PlaceSql().delPlace(pleasure);
		}else {System.out.println("번호를 다시 입력해주세요"); return;}
	}

	public void view() {
		ArrayList<Place> dtos = new PlaceSql().showListAll();
		System.out.print("장소번호" + " | ");
		System.out.print("장소이름" + " | ");
		System.out.print("장소지역" + " | ");
		System.out.print("세부번호" + " | ");
		System.out.print("대분류" + " | ");
		System.out.print("소분류" + " | ");
		System.out.println();
		for (int i = 0; i < dtos.size(); i++) {

			int pNo = dtos.get(i).getPno();
			String pName = dtos.get(i).getPname();
			String pLoca = dtos.get(i).getLoca();

			int ptNo = dtos.get(i).getPtno();
			String ptType1 = dtos.get(i).getPtype1();
			String ptType2 = dtos.get(i).getPtype2();

			System.out.print(pNo + " | ");
			System.out.print(pName + " | ");
			System.out.print(pLoca + " | ");
			System.out.print(ptNo + " | ");
			System.out.print(ptType1 + " | ");
			System.out.print(ptType2 + " | ");
			System.out.println();
		}
		System.out.println();
	}// view()

	public void view(String lc) {

		ArrayList<Place> dtos = new PlaceSql().showListlc(lc);
		if (dtos.size() == 0) {
			System.out.println("해당구가 없거나 구를 잘못입력하였습니다.");
			System.out.println();
			return;
		}
		System.out.print("장소번호" + " | ");
		System.out.print("장소이름" + " | ");
		System.out.print("장소지역" + " | ");
		System.out.print("세부번호" + " | ");
		System.out.print("대분류" + " | ");
		System.out.print("소분류" + " | ");
		System.out.println();

		for (int i = 0; i < dtos.size(); i++) {

			int pNo = dtos.get(i).getPno();
			String pName = dtos.get(i).getPname();
			String pLoca = dtos.get(i).getLoca();

			int ptNo = dtos.get(i).getPtno();
			String ptType1 = dtos.get(i).getPtype1();
			String ptType2 = dtos.get(i).getPtype2();

			System.out.print(pNo + " | ");
			System.out.print(pName + " | ");
			System.out.print(pLoca + " | ");
			System.out.print(ptNo + " | ");
			System.out.print(ptType1 + " | ");
			System.out.print(ptType2 + " | ");
			System.out.println();
		}
		System.out.println();
	}// view(lc)

}// class
