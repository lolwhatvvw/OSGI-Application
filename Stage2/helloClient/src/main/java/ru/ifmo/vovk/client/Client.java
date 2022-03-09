package ru.ifmo.vovk.client;

import ru.ifmo.vovk.service.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class Client implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference =
                bundleContext.getServiceReference(GreetingService.class.getName());
        if(reference != null) ((GreetingService) bundleContext.getService(reference)).sayHello();
        else System.out.println("Greeting service is not installed");
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
    }

}