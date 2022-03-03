package hello.client;

import hello.service.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;


public class Client implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        ServiceReference reference =
                bundleContext.getServiceReference(GreetingService.class.getName());
        GreetingService greetingService =
                (GreetingService) bundleContext.getService(reference);
        greetingService.sayHello();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
    }

}