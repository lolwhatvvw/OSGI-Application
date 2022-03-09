package ru.ifmo.vovk.client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import ru.ifmo.vovk.service.GreetingService;

@Component
public class Client{
    private GreetingService greetingService;

    @Reference(service = GreetingService.class)
    protected  void setGreetingService(GreetingService greetingService){
        this.greetingService = greetingService;
    }

    @Activate
    protected void onActivate(){
        greetingService.sayHello();
    }


}