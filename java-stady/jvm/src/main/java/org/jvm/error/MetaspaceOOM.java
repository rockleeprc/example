package org.jvm.error;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * -XX:MaxMetaspaceSize=10M
 */
public class MetaspaceOOM {

    public static void main(String[] args) {
        List<HashMap> list = new ArrayList<>();
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(HashMap.class);
            enhancer.setUseFactory(false);
            enhancer.setCallback((MethodInterceptor) (obj, method, ags, proxy) -> {
                System.out.println("cglib代理");
                return proxy.invokeSuper(obj, ags);
            });
            HashMap cglibProxy = (HashMap) enhancer.create();
            cglibProxy.toString();
            list.add(cglibProxy);
        }
    }
}
