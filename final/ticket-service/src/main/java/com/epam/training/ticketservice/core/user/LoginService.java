package com.epam.training.ticketservice.core.user;

import com.epam.training.ticketservice.core.user.model.UserDto;
import org.springframework.shell.Availability;

import java.util.Optional;

public interface LoginService {

    Optional<UserDto> login(String username, String password);

    Optional<UserDto> logout();

    Optional<UserDto> getLoggedInUser();

    Availability isAdminCommandAreAvailable();
}
