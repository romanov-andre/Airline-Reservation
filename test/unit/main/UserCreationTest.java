package main;

import java.io.IOException;
import javax.swing.JPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserCreationTest {

  private UserCreation userCreationTester = new UserCreation();
  private JPanel panel = new JPanel();


  @Test
  void initUser() throws IOException {
    userCreationTester.setTxtuserid("1234");
    userCreationTester.setTxtfirstname("Test");
    userCreationTester.setTxtlastname("Name");
    userCreationTester.setTxtpassword("1111");
    userCreationTester.setTxtusername("testUser");

  }


  @Test
  void emptyFirstNameTest() throws IOException {
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
  void allEmptyField() {
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
    userCreationTester.setTxtpassword("123");
    Assertions.assertTrue(userCreationTester.jButtonAddActionPerformed(null));
  }
}