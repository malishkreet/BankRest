package com.example.bankcards.service;


import com.example.bankcards.entity.User;
import com.example.bankcards.exception.NotFoundException;
import com.example.bankcards.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public User getById(Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public User create(User user) {
        return userRepo.save(user);
    }

    public User update(Long id, User updated) {
        User u = getById(id);
        u.setUsername(updated.getUsername());
        u.setPassword(updated.getPassword());
        u.setRoles(updated.getRoles());
        return userRepo.save(u);
    }

    public void delete(Long id) {
        if (!userRepo.existsById(id)) {
            throw new NotFoundException("User not found with id: " + id);
        }
        userRepo.deleteById(id);
    }
}
