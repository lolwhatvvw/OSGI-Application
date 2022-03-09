package ru.ifmo.vovk.service.impl;

import ru.ifmo.vovk.service.GreetingService;


public class GreetingServiceImpl implements GreetingService {

    @Override
    public void sayHello(){
        System.out.println("Hello OSGi World!");
    }


}
