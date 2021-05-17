package com.epam.training.ticketservice.core.user.impl;

import com.epam.training.ticketservice.core.user.UserService;
import com.epam.training.ticketservice.core.user.model.RegistrationDto;
import com.epam.training.ticketservice.core.user.model.UserDto;
import com.epam.training.ticketservice.core.user.persistance.entity.User;
import com.epam.training.ticketservice.core.user.persistance.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        User admin = new User(null, "admin", "admin", User.Role.ADMIN);
        userRepository.save(admin);
    }

    @Override
    public void registerUser(RegistrationDto registrationDto) {
        Objects.requireNonNull(registrationDto,
                "RegistrationDto cannot be null during the registration");
        Objects.requireNonNull(registrationDto.getUsername(),
                "Username is mandatory, cannot be null during the registration");
        Objects.requireNonNull(registrationDto.getPassword(),
                "Password is mandatory, cannot be null during the registration");
        User user = new User(null, registrationDto.getUsername(), registrationDto.getPassword(), User.Role.USER);
        // TODO: 2021. 05. 17. azonos username validálás
        userRepository.save(user);
    }

    @Override
    public Optional<UserDto> retrieveUserByNameAndPassword(String username, String password) {
        return convertToDto(userRepository.findByUsernameAndPassword(username, password));
    }

    private Optional<UserDto> convertToDto(Optional<User> user) {
        Optional<UserDto> userDto = Optional.empty();
        if (user.isPresent()) {
            userDto = Optional.of(convertToDto(user.get()));
        }
        return userDto;
    }

    private UserDto convertToDto(User user) {
        return new UserDto(user.getUsername(), user.getRole());
    }
}
