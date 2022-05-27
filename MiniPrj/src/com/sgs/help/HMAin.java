package com.sgs.help;

import java.io.IOException;

import com.yogijogi.member.User;

public class HMAin {

	public static void main(String[] args) {

		User user = new User(1,"1","1","1","상담사","H","N");
		try {
			new Help(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
