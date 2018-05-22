package org.framework.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class PropsUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

	/**
	 * 加载属性文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static Properties loadProps(String fileName) {
		Properties props = null;
		try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName)) {
			if (is == null)
				throw new FileNotFoundException("load file path :" + fileName);
			props = new Properties();
			props.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return props;
	}

	public static String getString(Properties props, String key) {
		return getString(props, key, "");
	}

	public static String getString(Properties props, String key, String defaultValue) {
		String value = defaultValue;
		if (props.containsKey(key)) {
			value = props.getProperty(key);
		}
		return value;
	}

	public static int getInt(Properties props, String key) {
		return getInt(props, key, 0);
	}

	public static int getInt(Properties props, String key, int defualtValue) {
		int value = defualtValue;
		if (props.containsKey(key))
			value = CastUtil.castInt(props.getProperty(key));
		return value;
	}

	public static boolean getBoolean(Properties props, String key) {
		return getBoolean(props, key, false);
	}

	public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
		boolean value = defaultValue;
		if (props.containsKey(key)) {
			value = CastUtil.castBoolean(props.getProperty(key));
		}
		return value;
	}
}
