package exam.mybatis.mapperproxy;

import java.lang.reflect.Proxy;

public class MySqlSession {

	@SuppressWarnings("unchecked")
	public <T> T getMapper(Class<T> mapperInterface) {
		MyMapperProxy mapperProxy = new MyMapperProxy();
		return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface },
				mapperProxy);
	}
}
