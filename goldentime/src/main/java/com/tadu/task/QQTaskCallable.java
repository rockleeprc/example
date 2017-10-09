package com.tadu.task;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.http.client.utils.URLEncodedUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tadu.bean.BookInfo;
import com.tadu.constant.URLConsts;
import com.tadu.util.HttpUtils;
import com.tadu.util.KeyUtils;
import com.xiaoleilu.hutool.util.URLUtil;

public class QQTaskCallable implements Callable<String> {

	private String ywId;

	public QQTaskCallable(String ywId) {
		this.ywId = ywId;
	}

	@Override
	public String call() throws Exception {
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
			if (resultJson == null)
				return "false";
		}
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		BookInfo info = null;
		try {
			info = mapper.readValue(resultJson, BookInfo.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("0".equals(info.getReturnCode())) {
			return info.getResult().getBook().toString();
		}
		return "false";
	}

}
