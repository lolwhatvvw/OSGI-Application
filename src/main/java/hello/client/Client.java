package hello.client;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import hello.service.GreetingService;


public class Client implements BundleActivator {
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference =
                bundleContext.getServiceReference(GreetingService.class.getName());
        GreetingService greetingService =
                (GreetingService) bundleContext.getService(reference);
        greetingService.sayHello();
    }
    public void stop(BundleContext bundleContext) throws Exception {
    }

}