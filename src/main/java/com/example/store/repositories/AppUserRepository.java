package com.example.store.repositories;

import com.example.store.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    AppUser findByEmail(String email);
}
