package com.foo.provider;

import org.springframework.stereotype.Service;

import com.foo.api.IFooFacade;

@Service("fooFacade")
public class FooFacade implements IFooFacade {

	public void foo() {
		System.out.println("dubbo-foo-provider.FooFacade.foo()");

	}

}
