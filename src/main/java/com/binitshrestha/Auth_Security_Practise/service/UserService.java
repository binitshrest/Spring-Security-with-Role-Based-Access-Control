package com.binitshrestha.Auth_Security_Practise.service;

import com.binitshrestha.Auth_Security_Practise.model.RegisterUserDto;
import com.binitshrestha.Auth_Security_Practise.model.entity.Role;
import com.binitshrestha.Auth_Security_Practise.model.entity.RoleEnum;
import com.binitshrestha.Auth_Security_Practise.model.entity.User;
import com.binitshrestha.Auth_Security_Practise.repository.RoleRepository;
import com.binitshrestha.Auth_Security_Practise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    // for admin and higher roles
    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = User
                .builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(
                        passwordEncoder.encode(input.getPassword())
                )
                .role(optionalRole.get())
                .build();

        return userRepository.save(user);
    }
}