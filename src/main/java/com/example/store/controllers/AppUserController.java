package com.example.store.controllers;

import com.example.store.exceptions.ResourceNotFoundException;
import com.example.store.models.AppUser;
import com.example.store.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppUserController {
    @Autowired
    AppUserRepository userRepository;
    @GetMapping("/user")
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }
    @PostMapping("/addUser")
    public void addUser(@RequestBody AppUser user) {
        userRepository.save(user);
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(value = "id" ) long id, @RequestBody AppUser user) throws ResourceNotFoundException {
        AppUser user1= userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPassword(user.getPassword());
        user1.setCreatedAt(user.getCreatedAt());
        final AppUser updatedUser = userRepository.save(user1);
        return ResponseEntity.ok().body(updatedUser);
    }
    @DeleteMapping("/deleteUser/{id}")
    public  Map<String,Boolean> deleteUser(@PathVariable(value = "id" ) long id) throws ResourceNotFoundException {
        AppUser user1=userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found"));
        userRepository.delete(user1);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}