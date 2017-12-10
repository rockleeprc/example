package exam.imp;

import exam.api.IProvider;

public class ProviderImp implements IProvider {

	public Integer plus(Integer i, Integer j) {
		System.out.println("i=" + i + ",i=" + j);
		return i + j;
	}

}
