package com.example.store.repositories;

import com.example.store.models.AppUser;
import com.example.store.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByAppUser(AppUser appUser);
}