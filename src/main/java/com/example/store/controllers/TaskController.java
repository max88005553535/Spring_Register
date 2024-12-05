package com.example.store.controllers;

import com.example.store.models.Task;
import com.example.store.repositories.AppUserRepository;
import com.example.store.repositories.CategoriesRepository;
import com.example.store.repositories.TaskRepository;
import com.example.store.services.TaskService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final AppUserRepository appUserRepository;
    TaskRepository tasksRepository;
    CategoriesRepository categoriesRepository;

    public TaskController(TaskService taskService, AppUserRepository appUserRepository) {
        this.taskService = taskService;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping
    public String getTasks(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        model.addAttribute("tasks",taskService.getTasksByUser(username));
        return "tasks";
    }

    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("categories", taskService.getCategories());
        return "add_task";

    }
    @PostMapping("/add")
    public String addTask(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute Task task, @RequestParam(required = false) Long categoryId) {
        taskService.addTask(userDetails.getUsername(), task,categoryId);
        return "redirect:/home";
    }


}