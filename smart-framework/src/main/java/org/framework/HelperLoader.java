package org.framework;

import org.framework.helper.BeanHelper;
import org.framework.helper.ClassHelper;
import org.framework.helper.ControllerHelper;
import org.framework.helper.IocHelper;
import org.framework.util.ClassUtil;

/**
 * 加载相应的 Helper 类
 *
 * @author huangyong
 * @since 1.0.0
 */
public final class HelperLoader {

	public static void init() {
		Class<?>[] classList = { ClassHelper.class, BeanHelper.class, IocHelper.class, ControllerHelper.class };
		for (Class<?> cls : classList) {
			ClassUtil.loadClass(cls.getName());
		}
	}
}