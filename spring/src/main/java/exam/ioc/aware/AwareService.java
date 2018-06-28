package exam.ioc.aware;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
		
		//通过spring ResourceLoader
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
		
		//通过java类加载器
		InputStream resourceAsStream = AwareService.class.getClassLoader().getResourceAsStream("dataSource.properties");
		try {
			prop.load(resourceAsStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("jdbc.driverClassName"));
		
//		InputStream is = AwareService.class.getClassLoader().getResourceAsStream("/");
		URL url = AwareService.class.getClassLoader().getResource("");
		System.out.println("--->"+url);//E:/workspace/github/example/spring/target/test-classes/
	}

}
