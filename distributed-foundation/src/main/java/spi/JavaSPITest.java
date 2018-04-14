package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;

import spi.interfaces.Commond;

public class JavaSPITest {

	
	@Test
	public void testInvoke() {
		ServiceLoader<Commond> serviceLoader = ServiceLoader.load(Commond.class);
		for(Iterator<Commond> iter = serviceLoader.iterator();iter.hasNext();){
			iter.next().execute();
		}
		
	}

	@Test
	public void testFoeach() {
		ServiceLoader<Commond> serviceLoader = ServiceLoader.load(Commond.class);
		for (Commond commond : serviceLoader) {
			System.out.println(commond.getClass().getSimpleName());
		}
	}
}
