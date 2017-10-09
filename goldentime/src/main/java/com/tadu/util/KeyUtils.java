package com.tadu.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class KeyUtils {

	private static final int QQ_CHANNEL = 1080;
	private static final String QQ_KEY = QQ_CHANNEL + "_bk";
	private static final String THREEG_PASSWORD = "077264e9-cc95-464a-98e5-a22f819a426a";

	private KeyUtils() {
		throw new RuntimeException("工具类不可实例化");
	}

	/**
	 * 3Gkey
	 * 
	 * @param otherId
	 * @return
	 */
	public static String threeGKeyHex(String otherId) {
		String md5 = DigestUtils.encodeMD5(THREEG_PASSWORD + otherId);
		return str2HexStr(md5);
	}

	private static String str2HexStr(String str) {

		StringBuffer sbmd5 = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < 16; i++) {
			String subStr = "";
			if (i == 0) {
				subStr = str.substring(i, i + 2);
			} else if (i == 1) {
				subStr = str.substring(2, 4);
			} else {
				int end = (i + 1) * 2 > str.length() ? str.length() : (i + 1) * 2;
				subStr = str.substring(i * 2, end);
			}
			if (flag) {
				sbmd5.append("-");
			}
			sbmd5.append(subStr);
			flag = true;
			if (i == 31) {
				flag = false;
			}
		}
		return sbmd5.toString();
	}

	/**
	 * 阅文书籍key
	 * 
	 * @return
	 */
	public static String qqAppKey() {
		String kmd5 = DigestUtils.encodeMD5(QQ_KEY).substring(0, 7);
		String c5 = String.format("%05d", QQ_CHANNEL);
		String appKey = kmd5 + c5;
		return appKey;
	}

	/**
	 * 阅文当天的token
	 * 
	 * @return
	 */
	public static String qqAppToken() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String today = format.format(new Date());
		String channel_today = QQ_CHANNEL + today;
		String appToken = DigestUtils.encodeMD5(channel_today).substring(0, 12);
		return appToken;
	}
}
