package k17.keyt;

import java.util.HashMap;
import java.util.Map;

public class Main {
	private static final String SECRET_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA09lLyECaAfz64RM0J/E505rQfxbGTTT9Z/30Tuxbdqc7OwMwX1ZHe5tfxuj/VuaE5UAT3j7xZeijY/HHLijSDwIDAQABAkBRaE8WxLxpxy0hEKAaOThfeD5ml/nb8WDvdUdMjMcY8LdrQVjzNs1wIG3vLA5mRkd336TryBWGpeeyx4mfCWkBAiEA7i5izfmFTiiwzfqqsOroMG6Ds3gG557rjTt/SH+pbLsCIQDjspiI5dGeLGQ/BEDu7T8/VskYZRxgJ1HXIHxKPmfkvQIhAL9DKn6Cl4SK8meFmhoVmLyDkmjEwq6ulDLGi1CZi2DPAiEAyszwJMYknC/HnYTpXKS8d2qRs4Oi8VU0BFpvuSS6HjUCIGljzPDLvBCsOW30vx6PI0IFwuP3TpfBLfowlTlr+1v9";

	public static void main(String[] args) {
		t1();
	}

	public static void t2() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnerId", "1021");
		param.put("bookId", "1721756");
		String param_str = SignUtil.getParameterString(param);
		System.out.println(param_str);
		System.out.println("签名结果：" + Sign.sign(param_str, SECRET_KEY));
	}

	public static void t1() {
		Map<String, String> param = new HashMap<String, String>();
		param.put("partnerId", "1021");
		param.put("page", "1");
		param.put("lastUpdateDate", "2017-10-08 00:01:01");
		String param_str = SignUtil.getParameterString(param);
		System.out.println(param_str);
		System.out.println("签名结果：" + Sign.sign(param_str, SECRET_KEY));
	}

}
