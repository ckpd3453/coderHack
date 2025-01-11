package crio.coding_leaderboard.coding_leaderboard.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document("users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userId;

    @NotBlank(message = "Username is mandatory")
    private String name;

    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 100, message = "Score must not exceed 100")
    private int score;

    private List<String> badges;

    // Constructors, Getters, and Setters

}
