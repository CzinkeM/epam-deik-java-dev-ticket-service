package com.epam.training.ticketservice.core.user;

import com.epam.training.ticketservice.core.user.model.RegistrationDto;
import com.epam.training.ticketservice.core.user.model.UserDto;

import java.util.Optional;

public interface UserService {

    void registerUser(RegistrationDto registrationDto);

    Optional<UserDto> retrieveUserByNameAndPassword(String name, String password);

}
