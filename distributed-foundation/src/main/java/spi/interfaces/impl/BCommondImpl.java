package spi.interfaces.impl;

import spi.interfaces.Commond;

public class BCommondImpl implements Commond {

	@Override
	public void execute() {
		System.out.println("spi.interfaces.impl.BCommondImpl.execute()");
	}

}
