package ru.ifmo.vovk.command;


import org.apache.felix.service.command.annotations.GogoCommand;
import org.osgi.service.component.annotations.Component;

@GogoCommand(scope = "practice", function = "hello")
@Component(service=Object.class)
public class HelloCommand {

    public String hello(String name) {
        return "Hello " + name;
    }
}
