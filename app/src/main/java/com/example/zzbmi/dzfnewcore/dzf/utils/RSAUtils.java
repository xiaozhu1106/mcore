package com.example.zzbmi.dzfnewcore.dzf.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.IOException;

public class RSAUtils {
	public static String encrypt(String srcStr) {
		if (srcStr == null)
			return null;
		return new String(Base64.encodeBase64(srcStr.getBytes()));
	}
	public static String decrypt(String enStr) throws IOException {
		if (enStr == null)
			return null;
		return new String(Base64.decodeBase64(enStr.getBytes()));
	}

	public static void main(String[] args) {
		String en = encrypt("sfjdshongliangelk说了点附近的数量");
		System.out.println(en);
		try {
			System.out.println(decrypt(en));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}