package review;

import com.yogijogi.obj.ObjController;

public class ReviewRun {

	public void ReviewRun() {
		
		System.out.println("=====선택=====");
		System.out.println("1. 리뷰보기");
		System.out.println("2. 리뷰작성");
		
		int n = ObjController.scanInt();
		
		switch(n) {
		case 1 : new Review().showReviewList(); break;
		case 2 : new Review().updateReview(); break;
		default : System.out.println("다시 선택 하세요");
		}
		
	}
	
}
