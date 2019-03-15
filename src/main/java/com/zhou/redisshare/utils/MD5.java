package com.zhou.redisshare.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class MD5 {

	public static String stringToMD5(String str) {

		try {
			byte[] strTemp = str.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			return toHexString(mdTemp.digest());
		} catch (Exception e) {
			return null;
		}
	}

	public static String fileNameToMD5(String fileName) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
			return streamToMD5(inputStream);
		} catch (Exception e) {
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String streamToMD5(InputStream inputStream) {
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int numRead = 0;
			while ((numRead = inputStream.read(buffer)) > 0) {
				mdTemp.update(buffer, 0, numRead);
			}
			return toHexString(mdTemp.digest());
		} catch (Exception e) {
			return null;
		}
	}

	private static String toHexString(byte[] md) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		int j = md.length;
		char str[] = new char[j * 2];
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[2 * i] = hexDigits[byte0 >>> 4 & 0xf];
			str[i * 2 + 1] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
	public static String stringToMD5Crypter(String originalString){
		try {
			MessageDigest messagedigest = MessageDigest.getInstance("MD5");
			messagedigest.reset();
			messagedigest.update(("GJDX*ZNMP" + originalString).getBytes("utf8"));
			byte[] abyte0 = messagedigest.digest();
			return toString(abyte0);
		} catch (Exception exception) {
			System.err.println(exception.getMessage());
		}
		return null;
	}
	private static final char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	public static String toString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
	{
		char[] arrayOfChar = new char[paramInt2 * 2];
		int i = 0;
		for (int k = paramInt1; k < paramInt1 + paramInt2; k++)
		{
			int j = paramArrayOfByte[k];
			arrayOfChar[(i++)] = hexDigits[(j >>> 4 & 0xF)];
			arrayOfChar[(i++)] = hexDigits[(j & 0xF)];
		}
		return new String(arrayOfChar);
	}

	public static String toString(byte[] paramArrayOfByte)
	{
		return toString(paramArrayOfByte, 0, paramArrayOfByte.length);
	}
}
