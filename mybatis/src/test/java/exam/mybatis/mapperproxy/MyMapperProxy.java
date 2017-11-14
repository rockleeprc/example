package exam.mybatis.mapperproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import exam.mybatis.model.User;

public class MyMapperProxy implements InvocationHandler {


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		if ("selectByID".equals(method.getName())) {
			
			User u = new User();
			u.setId((Integer) args[0]);
			u.setUserName(this.toString());
			return u;
		}
		return null;
	}

}
