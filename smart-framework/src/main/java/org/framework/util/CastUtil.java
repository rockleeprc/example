package org.framework.util;

public class CastUtil {

	public static String castString(Object obj) {
		return castString(obj, "");
	}

	public static String castString(Object obj, String defaultValue) {
		// 非null对象几乎都会被转成String，不需要try{}catch{}处理
		return obj != null ? String.valueOf(obj) : defaultValue;
	}

	public static int castInt(Object obj) {
		return castInt(obj, 0);
	}

	public static int castInt(Object obj, int defaultValue) {
		int value = defaultValue;
		if (obj != null) {
			String val = castString(obj);
			if (StringUtil.isNotEmpty(val)) {
				try {
					value = Integer.parseInt(val);
				} catch (Exception e) {
					value = defaultValue;
				}
			}
		}
		return value;
	}

	public static boolean castBoolean(Object obj) {
		return castBoolean(obj, false);
	}

	public static boolean castBoolean(Object obj, boolean defautValue) {
		boolean value = defautValue;
		if (obj != null)
			value = Boolean.parseBoolean(castString(obj));
		return value;
	}

	public static void main(String[] args) {
		System.out.println(castString("1"));
		System.out.println(castInt("x"));

	}

}
