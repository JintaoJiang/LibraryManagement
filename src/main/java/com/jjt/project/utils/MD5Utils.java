package com.jjt.project.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	public static String next(String key) {
		char[] hexDigits = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(key.getBytes());
			byte[] output = md.digest();
			char[] res = new char[output.length * 2];
			int j = 0;
			for(int i = 0; i < output.length; i++) {
				res[j++] = hexDigits[(output[i] >> 4) & 0xF];
				res[j++] = hexDigits[output[i] & 0xF];
			}
			return new String(res);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}		
	}
}
