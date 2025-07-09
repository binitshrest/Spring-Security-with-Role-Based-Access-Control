package com.binitshrestha.Auth_Security_Practise.bootstrap;
import com.binitshrestha.Auth_Security_Practise.model.RegisterUserDto;
import com.binitshrestha.Auth_Security_Practise.model.entity.Role;
import com.binitshrestha.Auth_Security_Practise.model.entity.RoleEnum;
import com.binitshrestha.Auth_Security_Practise.model.entity.User;
import com.binitshrestha.Auth_Security_Practise.repository.RoleRepository;
import com.binitshrestha.Auth_Security_Practise.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Order(2) //Executes after RoleSeeder
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        RegisterUserDto userDto = RegisterUserDto.builder()
                .fullName("Super Admin")
                .email("super.admin@email.com")
                .password("123456")
                .build();

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() &&
                optionalUser.isPresent()) {
            return;
        }

        var user = User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(optionalRole.get())
                .build();

        userRepository.save(user);
    }
}
