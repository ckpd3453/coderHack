package crio.coding_leaderboard.coding_leaderboard.controller;

import crio.coding_leaderboard.coding_leaderboard.exception.UserExistException;
import crio.coding_leaderboard.coding_leaderboard.exception.UserNotFoundException;
import crio.coding_leaderboard.coding_leaderboard.model.User;
import crio.coding_leaderboard.coding_leaderboard.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable String userId) throws UserNotFoundException {
        return userService.getUserById(userId);
    }

    @PostMapping
    public User registerUser(@Valid @RequestBody User user) throws UserExistException {
        return userService.registerUser(user);
    }

    @PutMapping("/{userId}")
    public User updateUserScore(@PathVariable String userId, @RequestParam int score) throws UserNotFoundException {
        return userService.updateUserScore(userId, score);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}

