package com.binitshrestha.Auth_Security_Practise.controller;

import com.binitshrestha.Auth_Security_Practise.model.RegisterUserDto;
import com.binitshrestha.Auth_Security_Practise.model.entity.User;
import com.binitshrestha.Auth_Security_Practise.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admins")
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @PostMapping("/")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        User createdAdmin = userService.createAdministrator(registerUserDto);

        return ResponseEntity.ok(createdAdmin);
    }
}
