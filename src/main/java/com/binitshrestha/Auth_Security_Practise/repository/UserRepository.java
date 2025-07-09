package com.binitshrestha.Auth_Security_Practise.repository;

import com.binitshrestha.Auth_Security_Practise.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
