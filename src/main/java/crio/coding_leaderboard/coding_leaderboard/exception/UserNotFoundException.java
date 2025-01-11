package crio.coding_leaderboard.coding_leaderboard.exception;

import lombok.Data;

@Data
public class UserNotFoundException extends Exception{

    private String message;
    public UserNotFoundException(String message) {
        this.message = message;
    }
}
