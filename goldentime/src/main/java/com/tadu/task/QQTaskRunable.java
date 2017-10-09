package com.tadu.task;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tadu.bean.BookInfo;
import com.tadu.constant.URLConsts;
import com.tadu.util.FileHelper;
import com.tadu.util.HttpUtils;
import com.tadu.util.KeyUtils;

public class QQTaskRunable implements Runnable {

	private static final String OUTPUT_FILE_PATH = "E:" + File.separator + "yw_time.txt";
	private static final String OUTPUT_FAIL_PATH = "E:" + File.separator + "yw_time_fail.txt";
	private String taduId;
	private String ywId;

	public QQTaskRunable(String taduId, String ywId) {
		this.taduId = taduId;
		this.ywId = ywId;
	}

	@Override
	public void run() {
		Map<String, String> params = new HashMap<>();
		params.put("service", URLConsts.QQ_SERVICE);
		params.put("action", URLConsts.QQ_BOOK_INFO);
		params.put("CBID", ywId);
		params.put("appKey", KeyUtils.qqAppKey());
		params.put("appToken", KeyUtils.qqAppToken());
		String resultJson = null;
		try {
			resultJson = HttpUtils.httpGet(URLConsts.QQ_URL, params);
		} catch (Exception e) {
			for (int i = 0; i < 5; i++) {
				resultJson = HttpUtils.httpGet(URLConsts.QQ_URL, params);
			}
		} finally {
			if (resultJson == null) {
				try {
					FileHelper.writeLine(OUTPUT_FAIL_PATH, taduId + "," + ywId);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}

		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		BookInfo info = null;
		try {
			info = mapper.readValue(resultJson, BookInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if ("0".equals(info.getReturnCode())) {
				FileHelper.writeLine(OUTPUT_FILE_PATH, taduId + "," + info.getResult().getBook());
			} else {
				FileHelper.writeLine(OUTPUT_FAIL_PATH, taduId + "," + ywId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
