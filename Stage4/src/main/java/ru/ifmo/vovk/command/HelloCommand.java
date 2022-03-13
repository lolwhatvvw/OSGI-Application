package ru.ifmo.vovk.command;

import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "osgi.command.scope=practice",
                "osgi.command.function=hello"
        },
        service=HelloCommand.class
)
public class HelloCommand {

    public String hello(String name) {
        return "Hello, " + name;
    }

}
