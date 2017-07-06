package exam.mybatis.objectfactory;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import exam.mybatis.typehandler.MyStringTypeHandler;

public class MyObjectFactory extends DefaultObjectFactory {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3493733692930523499L;
	private Logger log = Logger.getLogger(MyObjectFactory.class.getName());

	@Override
	public void setProperties(Properties properties) {
		log.info("MyObjectFactory.setProperties："+properties);
		super.setProperties(properties);
	}

	@Override
	public <T> T create(Class<T> type) {
		log.info("MyObjectFactory.create：");
		return super.create(type);
	}

	@Override
	public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
		log.info("MyObjectFactory.create：");
		return super.create(type, constructorArgTypes, constructorArgs);
	}

	@Override
	public <T> boolean isCollection(Class<T> type) {
		log.info("MyObjectFactory.isCollection："+super.isCollection(type));
		return super.isCollection(type);
	}
}
