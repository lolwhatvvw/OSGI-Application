package ru.ifmo.vovk.client;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import ru.ifmo.vovk.service.GreetingService;

@Component
public class Client{
    private GreetingService greetingService;

    @Activate
    public Client(@Reference GreetingService greetingService){
        this.greetingService = greetingService;
        onActivate();
    }

    public void onActivate(){
        greetingService.sayHello();
    }
}