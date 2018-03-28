package spi.interfaces.impl;

import spi.interfaces.Commond;

public class ACommondImpl implements Commond {

	@Override
	public void execute() {
		System.out.println("spi.interfaces.impl.ACommondImpl.execute()");
	}

}
