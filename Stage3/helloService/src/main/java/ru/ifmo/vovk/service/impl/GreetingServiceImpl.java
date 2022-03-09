package ru.ifmo.vovk.service.impl;

import org.osgi.service.component.annotations.Component;
import ru.ifmo.vovk.service.GreetingService;

@Component(service = GreetingService.class)
public class GreetingServiceImpl implements GreetingService {

    @Override
    public void sayHello(){
        System.out.println("Hello OSGi World!");
    }
}