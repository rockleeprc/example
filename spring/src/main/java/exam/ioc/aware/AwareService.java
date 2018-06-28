package exam.ioc.aware;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

@Service
public class AwareService implements BeanNameAware, ResourceLoaderAware {

	private String beanName;
	private ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public void setBeanName(String name) {
		this.beanName = name;
	}

	public void print() {
		System.out.println("beanName : " + beanName);
		Resource resource = resourceLoader.getResource("classpath:dataSource.properties");
		Properties prop = new Properties();
		try {
			InputStream inputStream = resource.getInputStream();
			prop.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("jdbc.driverClassName"));
	}

}
