package unit;

import main.UserCreation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

public class UserCreationTest {

  private UserCreation userCreationTester = new UserCreation();
  private JPanel panel = new JPanel();



  @Test
  void initUser() {
    userCreationTester.setTxtuserid("1234");
    userCreationTester.setTxtfirstname("Test");
    userCreationTester.setTxtlastname("Name");
    userCreationTester.setTxtpassword("111111111");
    userCreationTester.setTxtusername("testUser");
  }

  @Test
  public void autoIDExceptionTest() {

   userCreationTester.autoID();
Assertions.assertDoesNotThrow(() -> userCreationTester.autoID());
  }

  @Test
  public void addUserExceptionTest() {
    Assertions.assertDoesNotThrow(() -> userCreationTester.jButtonAddActionPerformed(null));
  }

  @Test
  void emptyFirstNameTest() {
    //Empty first name
      userCreationTester.setTxtfirstname("");
      Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));

  }
  @Test
  void emptyLastNameTest() {
    //Empty last name
    userCreationTester.setTxtlastname("");
    Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));

  }

  @Test
  void emptyUserName() {
    //Empty nic value
    userCreationTester.setTxtuserid("");
    Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));
  }
  @Test
  void emptyPasswordTest() {
    userCreationTester.setTxtpassword("");
    Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));
  }

  @Test
  public void allEmptyField() {
    userCreationTester.setTxtlastname("");
    userCreationTester.setTxtfirstname("");
    userCreationTester.setTxtuserid("");
    userCreationTester.setTxtpassword("");
    Assertions.assertFalse(userCreationTester.jButtonAddActionPerformed(null));
  }
  @Test
  void validUserCreate() {

    userCreationTester.setTxtlastname("Jhon");
    userCreationTester.setTxtfirstname("Roberto");
    userCreationTester.setTxtusername("jrob");
    userCreationTester.setTxtpassword("123123123");
    Assertions.assertTrue(userCreationTester.jButtonAddActionPerformed(null));
    Assertions.assertDoesNotThrow(() -> userCreationTester.jButtonAddActionPerformed(null));
  }
}