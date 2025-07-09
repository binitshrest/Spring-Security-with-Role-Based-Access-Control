package com.binitshrestha.Auth_Security_Practise.service;

import com.binitshrestha.Auth_Security_Practise.model.LoginUserDto;
import com.binitshrestha.Auth_Security_Practise.model.RegisterUserDto;
import com.binitshrestha.Auth_Security_Practise.model.entity.Role;
import com.binitshrestha.Auth_Security_Practise.model.entity.RoleEnum;
import com.binitshrestha.Auth_Security_Practise.model.entity.User;
import com.binitshrestha.Auth_Security_Practise.repository.RoleRepository;
import com.binitshrestha.Auth_Security_Practise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    //  for normal user
    public User signup(RegisterUserDto input){
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = User
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

    public User authenticate(LoginUserDto input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
