package com.sgs.help;

import java.io.IOException;

import com.yogijogi.member.User;

public class CMain {

	public static void main(String[] args) {
		
		User user = new User(1,"1","1","1","다람쥐","N","N");
		user.setNick("다람쥐");
		user.setRank("N");
		try {
			new Help(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
