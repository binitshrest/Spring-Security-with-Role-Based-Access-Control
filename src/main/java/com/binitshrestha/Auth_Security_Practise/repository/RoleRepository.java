package com.binitshrestha.Auth_Security_Practise.repository;

import com.binitshrestha.Auth_Security_Practise.model.entity.Role;
import com.binitshrestha.Auth_Security_Practise.model.entity.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByName(RoleEnum name);
}
