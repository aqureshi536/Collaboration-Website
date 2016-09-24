package com.ahmad.utility;

import java.util.UUID;

public class IdGenerator {

	public static String generateId(String prefix){
		return prefix+UUID.randomUUID().toString().substring(24).toUpperCase();
	}
	
}
