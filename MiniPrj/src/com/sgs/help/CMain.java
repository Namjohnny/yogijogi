package com.sgs.help;

import java.io.IOException;

public class CMain {

	public static void main(String[] args) {
		
		User user = new User();
		user.setNick("다람쥐");
		user.setRank("VIP");
		try {
			new Help(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
