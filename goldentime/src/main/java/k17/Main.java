package k17;

import java.util.HashMap;
import java.util.Map;


public class Main {
	public static void main(String[] args) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("partnerId", "357");
		param.put("page", "1");
		param.put("lastUpdateDate", "2015-12-12 00:00:00");
		String param_str = SignUtil.getParameterString(param);
		System.out.println("签名结果："+Sign.sign(param_str, "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAp1XBZSXO4w+iqn5rkSfNt6RXA2VIL4Yi7D+b/Wsoo5AX3Lre1Y5a6dpObSGYMMLoeM3nX1ccCCPeaVbM5EtqjwIDAQABAkEAmYl48vsW8oZ1JeTrg/u2qSrUYBw9eMWmI4lV8texKSlcE8e7mRHOPqm7EicyomNrYTm6eftljZQW60BaGYBIYQIhAPH+BcmHCrw/KstQoEhqSzdnW1/5sd6ZbDDkf7cKVfkfAiEAsQVrTceZzqBvLZNZS2i7TCbrnllYzJPjjhG9lUMssJECIFscfEtTR+x2kAM62Q5KzwUOKi4pkhmwhDaoYZmmD5abAiB1tqDQK/a/TDgiGmW6m35Q+WCc/9To1T7wbiOfafgBwQIgW8WGzfP8uxpCOWbFFXCCKjP1VVzvM7/COF26Ln7vFmU="));
		
	}

}
