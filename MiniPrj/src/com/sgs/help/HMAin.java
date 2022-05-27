package com.sgs.help;

import java.io.IOException;

public class HMAin {

	public static void main(String[] args) {

		User user = new User();
		user.setNick("상담사");
		user.setRank("helper");
		try {
			new Help(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
