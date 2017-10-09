package com.tadu.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class DigestUtils {
	
	
	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	public static String encodeMD5(String str) {
        try {
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(str.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return null;
    }
	
	/**
	@Deprecated
	public static String encodeMD5(String strOrigin) {
		String strAim = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			byte[] a = strOrigin.getBytes("utf-8");
			byte[] b = messageDigest.digest(a);
			strAim = byteArrayToHexString(b);
		} catch (Exception exception) {
		}
		return strAim;
	}
	 **/

	private static String byteArrayToHexString(byte byteArray[]) {
		StringBuffer strHex = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++)
			strHex.append(byteToHexString(byteArray[i]));

		return strHex.toString();
	}

	private static String byteToHexString(byte bt) {
		int i = bt;
		if (i < 0)
			i += 256;
		int j = i / 16;
		int k = i % 16;
		return hexDigits[j] + hexDigits[k];
	}
	

}
