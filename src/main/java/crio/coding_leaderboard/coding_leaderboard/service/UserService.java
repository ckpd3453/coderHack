package crio.coding_leaderboard.coding_leaderboard.service;

import crio.coding_leaderboard.coding_leaderboard.exception.UserExistException;
import crio.coding_leaderboard.coding_leaderboard.exception.UserNotFoundException;
import crio.coding_leaderboard.coding_leaderboard.model.User;
import crio.coding_leaderboard.coding_leaderboard.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        users.sort(Comparator.comparingInt(User::getScore).reversed()); // O(nlogn)
        return users;
    }

    public User getUserById(String userId) throws UserNotFoundException {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found."));
    }

    public User registerUser(User user) throws UserExistException {

        Optional<User> byUserName = userRepository.findByName(user.getName());

        if(byUserName.isPresent()){
            throw new UserExistException("User with name "+user.getName()+" already exist.");
        }
        user.setScore(0);
        user.setBadges(new ArrayList<>());
        return userRepository.save(user);
    }

    public User updateUserScore(String userId, int score) throws UserNotFoundException {
        User user = getUserById(userId);

        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }

        user.setScore(score);

        // Assign badges based on score
        List<String> badges = new ArrayList<>();
        if (score >= 1) badges.add("Code Ninja");
        if (score >= 30) badges.add("Code Champ");
        if (score >= 60) badges.add("Code Master");

        user.setBadges(badges);
        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}

