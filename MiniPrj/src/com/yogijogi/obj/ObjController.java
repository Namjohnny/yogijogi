package com.yogijogi.obj;

import java.util.Scanner;

public class ObjController {
	
	 static Scanner sc = new Scanner(System.in);
	
	public static String scanStr() {
		String result = sc.nextLine();
		return result;
	}
	
	public static int scanInt() {
		int result = Integer.parseInt(sc.nextLine());
		return result;
	}

}