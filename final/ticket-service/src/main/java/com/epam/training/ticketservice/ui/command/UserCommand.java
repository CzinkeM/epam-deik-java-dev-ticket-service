package com.epam.training.ticketservice.ui.command;

import com.epam.training.ticketservice.core.user.LoginService;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.RegistrationDto;
import com.epam.training.ticketservice.core.user.model.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Optional;

@ShellComponent
public class UserCommand {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCommand.class);

    private final LoginService loginService;
    private final UserService userService;

    public UserCommand(LoginService loginService, UserService userService) {
        this.loginService = loginService;
        this.userService = userService;
    }

    @ShellMethod(value = "Login - args: <username> <password>", key = "sign in privileged")
    public String login(String username, String password) {
        Optional<UserDto> userDto = loginService.login(username, password);
        String message;
        if (userDto.isPresent()) {
            message = userDto.get().toString();
        } else {
            message = "Login failed due to incorrect credentials";
        }
        return message;
    }

    @ShellMethod(value = "Logout", key = "sign out")
    public String logout() {
        Optional<UserDto> userDto = loginService.logout();
        String message;

        if (userDto.isPresent()) {
            message = "Successfully logged out";
        } else {
            message = "You are not signed in";
        }
        return message;
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    String describeUser() {
        Optional<UserDto> userDto = loginService.getLoggedInUser();
        String message;

        if (userDto.isPresent()) {
            message = "Signed in with privileged account " + userDto.get().getUsername();
        } else {
            message = "You are not signed in";
        }
        return message;
    }

    @ShellMethod(value = "Register New User", key = "register user")
    public String registrateUser(String username, String password) {
        RegistrationDto registrationDto = new RegistrationDto(username, password);
        String message;
        try {
            userService.registerUser(registrationDto);
            message = "Registration was successful";
        } catch (Exception e) {
            LOGGER.error("Error during registration", e);
            message = "The registration failed";
        }
        return message;
    }
}
