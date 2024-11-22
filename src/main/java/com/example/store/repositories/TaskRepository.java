package com.example.store.repositories;

import com.example.store.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<com.example.store.models.Task, Long> {
}