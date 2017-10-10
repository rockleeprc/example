package com.tadu.task;

import com.tadu.util.HttpUtils;

public class HttpMain {
	public static void main(String[] args) {
		t();
	}

	public static void tt() {
		for (int i = 0; i < 1000; i++) {
			String result = HttpUtils.doGet(
					"https://api.yuewen.com/content/cp/ServiceBus.do?service=CpNovel&action=bookinfo&CBID=3756421203768801&appKey=a092d2a01080&appToken=b65202841ec9");
			System.out.println(i + "=" + result);
		}
	}

	public static void t() {
		for (int i = 0; i < 1000; i++) {
			String result = HttpUtils.doGet(
					"http://openapi.tadu.com/api/get_part_content/?spName=zhuishu&securityId=82b1725a17014b1c8b525b6d725b4a8094c649c3&bookId=531983&chapterId=60068787");
			System.out.println(i + "=" + result);
		}
	}
}
