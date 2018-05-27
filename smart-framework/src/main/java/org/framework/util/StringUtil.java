package org.framework.util;

import org.apache.commons.lang3.StringUtils;

public class StringUtil {

	public static final String SEPARATOR = String.valueOf((char) 29);

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(String str) {
		if (str != null)
			str = str.trim();
		return StringUtils.isEmpty(str);
	}

	/**
	 * 分割固定格式的字符串
	 */
	public static String[] splitString(String str, String separator) {
		return StringUtils.splitByWholeSeparator(str, separator);
	}
}
