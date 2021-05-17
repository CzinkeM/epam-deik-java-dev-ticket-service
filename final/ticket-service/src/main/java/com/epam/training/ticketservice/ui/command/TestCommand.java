package com.epam.training.ticketservice.ui.command;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class TestCommand {

    @ShellMethod(value = "hello-bello", key = "hello")
    public String hello() {
        return "hello ez az első parancsod";
    }
}
