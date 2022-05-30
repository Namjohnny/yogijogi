package search;

import com.yogijogi.obj.ObjController;

public class SearchRun {

	public void searchRun() {
		
		System.out.println("=====검색=====");
		new Search().showList();
		
		System.out.println("1. 한식 정보상세 보기");
		System.out.println("2. 일식 정보상세 보기");
		System.out.println("3. 중식 정보상세 보기");
		System.out.println("4. 양식 정보상세 보기");
		System.out.println("5. 기타 정보상세 보기");
		System.out.println("6. 박물관 정보상세 보기");
		System.out.println("7. 미술관 정보상세 보기");
		System.out.println("8. 영화관 정보상세 보기");
		System.out.println("9. 극장 정보상세 보기");
		System.out.println("10. 기타 정보상세 보기");
		System.out.println("11. 주점 정보상세 보기");
		System.out.println("12. 클럽 정보상세 보기");
		System.out.println("=====번호를 입력해 주세요=====");
		
		int n = ObjController.scanInt();
		
		switch(n) {
		case 1 : new Pt1().search1(); break;
		case 2 : new Pt1().search2(); break;
		case 3 : new Pt1().search3(); break;
		case 4 : new Pt1().search4(); break;
		case 5 : new Pt1().search5(); break;
		case 6 : new Pt1().search6(); break;
		case 7 : new Pt1().search7(); break;
		case 8 : new Pt1().search8(); break;
		case 9 : new Pt1().search9(); break;
		case 10 : new Pt1().search10(); break;
		case 11 : new Pt1().search11(); break;
		case 12 : new Pt1().search12(); break;
		default : System.out.println("다시 선택 하세요");
		}
	}
	
}
