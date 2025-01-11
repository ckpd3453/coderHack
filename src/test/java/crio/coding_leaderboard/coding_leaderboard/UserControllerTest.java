package crio.coding_leaderboard.coding_leaderboard;
import crio.coding_leaderboard.coding_leaderboard.controller.UserController;
import crio.coding_leaderboard.coding_leaderboard.exception.UserExistException;
import crio.coding_leaderboard.coding_leaderboard.exception.UserNotFoundException;
import crio.coding_leaderboard.coding_leaderboard.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void testRegisterUser() throws UserExistException {
        User user = new User();
        user.setUserId("1");
        user.setName("TestUser1");

        User registeredUser = userController.registerUser(user);
        assertNotNull(registeredUser);
        assertEquals(0, registeredUser.getScore());
        assertTrue(registeredUser.getBadges().isEmpty());
    }

    @Test
    void testUpdateUserScore() throws UserExistException, UserNotFoundException {
        User user = new User();
        user.setUserId("1");
        user.setName("TestUser2");

        userController.registerUser(user);
        User updatedUser = userController.updateUserScore("1", 35);

        assertEquals(35, updatedUser.getScore());
        assertTrue(updatedUser.getBadges().contains("Code Champ"));
    }
}
