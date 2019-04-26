package com.jjt.project.utils;

import java.util.UUID;

public class UUIDUtils {
	public static String next() {
		return UUID.randomUUID().toString();
	}
}
