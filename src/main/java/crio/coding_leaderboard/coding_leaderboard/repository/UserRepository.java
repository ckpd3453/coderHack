package crio.coding_leaderboard.coding_leaderboard.repository;

import crio.coding_leaderboard.coding_leaderboard.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String username);
}
