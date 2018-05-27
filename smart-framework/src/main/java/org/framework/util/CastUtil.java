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

	/**
	 * 转为 double 型
	 */
	public static double castDouble(Object obj) {
		return CastUtil.castDouble(obj, 0);
	}

	/**
	 * 转为 double 型（提供默认值）
	 */
	public static double castDouble(Object obj, double defaultValue) {
		double doubleValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					doubleValue = Double.parseDouble(strValue);
				} catch (NumberFormatException e) {
					doubleValue = defaultValue;
				}
			}
		}
		return doubleValue;
	}

	/**
	 * 转为 long 型
	 */
	public static long castLong(Object obj) {
		return CastUtil.castLong(obj, 0);
	}

	/**
	 * 转为 long 型（提供默认值）
	 */
	public static long castLong(Object obj, long defaultValue) {
		long longValue = defaultValue;
		if (obj != null) {
			String strValue = castString(obj);
			if (StringUtil.isNotEmpty(strValue)) {
				try {
					longValue = Long.parseLong(strValue);
				} catch (NumberFormatException e) {
					longValue = defaultValue;
				}
			}
		}
		return longValue;
	}

	public static void main(String[] args) {
		System.out.println(castString("1"));
		System.out.println(castInt("x"));

	}

}
