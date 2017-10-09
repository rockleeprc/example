package com.tadu.task;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.tadu.constant.URLConsts;
import com.tadu.util.HttpUtils;
import com.tadu.util.KeyUtils;
import com.tadu.util.ThreeSAXParser;

public class ThreeGCallable implements Callable<String> {

	private String otherId;

	public ThreeGCallable(String otherId) {
		super();
		this.otherId = otherId;
	}

	@Override
	public String call() throws Exception {
		String key = KeyUtils.threeGKeyHex(otherId);
		Map<String, String> params = new HashMap<>();
		params.put("m", URLConsts.G3_M);
		params.put("a", URLConsts.G3_A);
		params.put("un", URLConsts.G3_UN);
		params.put("h", key);
		params.put("bid", otherId);
		String result = HttpUtils.httpGet(URLConsts.G3_URL, params);

		SAXParser saxParser = SAXParserFactory.newInstance().newSAXParser();
		ThreeSAXParser.Parser3G parser = new ThreeSAXParser.Parser3G();
		saxParser.parse(new InputSource(new ByteArrayInputStream(result.getBytes())), parser);
		return parser.getUptime() + "," + parser.getOtherId();
	}

}
