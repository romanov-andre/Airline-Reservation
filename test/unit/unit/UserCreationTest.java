package unit;

import main.UserCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.SQLException;

/**
 * Unit test for UserCreation
 * Created by: Mushfique Shafi
 */
public class UserCreationTest {

    private UserCreation userCreationTester = new UserCreation();
    private JPanel panel = new JPanel();

  /**
   * preset the value to test
   */
  @Test
    void initUser() {
        userCreationTester.setTxtuserid("1234");
        userCreationTester.setTxtfirstname("Test");
        userCreationTester.setTxtlastname("Name");
        userCreationTester.setTxtpassword("111111111");
        userCreationTester.setTxtusername("testUser");
    }

  /**
   * Auto ID exception test
   */
  @Test
    public void autoIDExceptionTest() {

        userCreationTester.autoID();
        Assertions.assertDoesNotThrow(() -> userCreationTester.autoID());
    }

  /**
   * Add user exception test
   */
  @Test
    public void addUserExceptionTest() {
        Assertions.assertDoesNotThrow(() -> userCreationTester.jButtonAddActionPerformed(null));
    }


  /**
   * Negative to verify outcome when first name is empty
   */
  @Test
    void emptyFirstNameTest() {
        //Empty first name
        userCreationTester.setTxtfirstname("");
        Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));

    }
  /**
   * Negative to verify outcome when last name is empty
   */
    @Test
    void emptyLastNameTest() {
        //Empty last name
        userCreationTester.setTxtlastname("");
        Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));

    }

  /**
   * Negative to verify outcome when username is empty
   */
    @Test
    void emptyUserName() {
        //Empty nic value
        userCreationTester.setTxtuserid("");
        Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));
    }

  /**
   * Negative to verify outcome when password is empty
   */
    @Test
    void emptyPasswordTest() {
        userCreationTester.setTxtpassword("");
        Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));
    }

  /**
   * Negative to verify outcome when all fields are empty
   */
    @Test
    public void allEmptyField() {
        userCreationTester.setTxtlastname("");
        userCreationTester.setTxtfirstname("");
        userCreationTester.setTxtuserid("");
        userCreationTester.setTxtpassword("");
        Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));
    }

  /**
   * Exception test for create user
   * @throws SQLException
   */
    @Test
    public void userCreateException() throws SQLException {
        try {
            userCreationTester.jButtonAddActionPerformed(null);
        } catch (Exception e) {
            Assertions.assertThrows(SQLException.class, () -> userCreationTester.jButtonAddActionPerformed(null));
        }
    }
}