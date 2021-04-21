package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

/**
 * Test class to verify methods in the Addflight class
 * Created By: Alan Norman and Shafi
 */
public class AddFlightTest {

  public Addflight addFlight = new Addflight();

  /**
   *
   * @throws ParseException When invalid date is given
   * Method to create a valid customer before testing
   */
  @BeforeEach
  public void initCustomer() throws ParseException {
    addFlight.setID("1");
    addFlight.setFlightName("American Airlines");
    addFlight.setSource("USA");
    addFlight.setDepart("Canada");
    addFlight.setDate("1997-08-02");
    addFlight.setDepartTime("12:00");
    addFlight.setArrTime("4:00");
    addFlight.setFlightCharge("200");
  }

  /**
   * Method to verify a flight is added correctly
   */
  @Test
  public void validAddFlightTest(){
    boolean result = addFlight.jButtonAddActionPerformed(null);
    Assertions.assertTrue(result);
  }

  /**
   * Method to verify a flight field was left empty
   */
  @Test
  public void emptyFieldAddFlightTest(){

    addFlight.setID("");

    boolean result = addFlight.jButtonAddActionPerformed(null);
    Assertions.assertFalse(result);
  }


}

