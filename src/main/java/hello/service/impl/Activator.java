package hello.service.impl;

import hello.service.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Activator implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        bundleContext.registerService(GreetingService.class.getName(),
                new GreetingServiceImpl(), null);
    }

    public void stop(BundleContext bundleContext) throws Exception {
    }

}