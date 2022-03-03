package hello.service.impl;


import hello.service.GreetingService;

public class GreetingServiceImpl implements GreetingService {

    @Override
    public void sayHello(){
        System.out.println("Hello OSGi World!");
    }
}
