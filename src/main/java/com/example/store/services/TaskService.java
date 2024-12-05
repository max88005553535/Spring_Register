package com.example.store.services;

import com.example.store.models.AppUser;
import com.example.store.models.Category;
import com.example.store.models.Task;
import com.example.store.repositories.AppUserRepository;
import com.example.store.repositories.CategoriesRepository;
import com.example.store.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository tasksRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Task> getTasksByUser(String username) {
        AppUser user=appUserRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        return tasksRepository.findByAppUser(user);
    }
    public Task addTask( String username,Task task, Long categoryId) {
        AppUser user=appUserRepository.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
        task.setAppUser(user);
        if (categoryId != null) {
            Category category = categoriesRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
            task.setCategory(category);
        }
        return tasksRepository.save(task);
    }
    public List<Category> getCategories() {
        return categoriesRepository.findAll();
    }
}