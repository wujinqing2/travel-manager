package net.rytong.admin.common;

import net.rytong.admin.common.security.SHA1;

public class Test {

	public static void main(String[] args) {
		String key = "${ceair&chinaebi&wujq}";
		
		for(int i = 0; i <= 189725; i++)
		{
			int r = (int)(Math.random() * 1000);
			
			if(String.valueOf(r).contains("3") || String.valueOf(r).contains("4") || String.valueOf(r).contains("7"))
				key = new StringBuilder(key).reverse().toString();
			
			key = SHA1.encrypt(key);
		}
		
		System.out.println(key);
	}
}
