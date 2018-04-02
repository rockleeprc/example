package com.bar.provider;

import org.springframework.stereotype.Service;

import com.bar.api.IBarFacade;

@Service("barFacade")
public class BarFacade implements IBarFacade {

	@Override
	public void bar() {
		System.out.println("dubbo-bar-provider.BarFacade.bar()");
	}

}