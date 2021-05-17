package com.epam.training.ticketservice.core.user.impl;

import com.epam.training.ticketservice.core.user.LoginService;
import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistance.entity.User;
import org.springframework.shell.Availability;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserService userService;
    private UserDto loggedInUser = null;

    public LoginServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<UserDto> login(String username, String password) {
        Objects.requireNonNull(username, "Username cannot be null during login");
        Objects.requireNonNull(password, "Password cannot be null during login");
        loggedInUser = userService.retrieveUserByNameAndPassword(username, password).orElseGet(() -> null);
        return getLoggedInUser();
    }

    @Override
    public Optional<UserDto> logout() {
        Optional<UserDto> previouslyLoggedInUser = getLoggedInUser();
        loggedInUser = null;
        return previouslyLoggedInUser;
    }

    @Override
    public Optional<UserDto> getLoggedInUser() {
        return Optional.ofNullable(loggedInUser);
    }

    @Override
    public Availability isAdminCommandAreAvailable() {
        UserDto user;
        if (getLoggedInUser().isPresent()) {
            user = getLoggedInUser().get();
        } else {
            return Availability.unavailable("You are not signed in");
        }
        if (user.getRole() == User.Role.ADMIN) {
            return Availability.available();
        } else {
            return Availability.unavailable("You are not signed in");
        }
    }
}
