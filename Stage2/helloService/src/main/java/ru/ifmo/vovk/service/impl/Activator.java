package ru.ifmo.vovk.service.impl;

import ru.ifmo.vovk.service.GreetingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        bundleContext.registerService(GreetingService.class.getName(),
                new GreetingServiceImpl(), null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
    }

}