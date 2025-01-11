package crio.coding_leaderboard.coding_leaderboard.exception;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserExistException extends Throwable {
    private String message;
    public UserExistException(String message) {
        this.message = message;
    }
}
