package com.yogijogi.obj;

import java.util.Scanner;

public class ObjController {
	
	Scanner sc = new Scanner(System.in);
	
	public String scanStr() {
		String result = sc.nextLine();
		return result;
	}
	
	public int scanInt() {
		int result = Integer.parseInt(sc.nextLine());
		return result;
	}

}