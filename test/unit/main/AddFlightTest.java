package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

public class AddFlightTest {

  public Addflight addFlight = new Addflight();

  @BeforeEach
  public void initCustomer() throws IOException, ParseException {
    addFlight.setID("1");
    addFlight.setFlightName("American Airlines");
    addFlight.setSource("USA");
    addFlight.setDepart("Canada");
    addFlight.setDate("01 JAN 2001");
    addFlight.setDepartTime("12:00");
    addFlight.setArrTime("4:00");
    addFlight.setFlightCharge("200");
  }

  @Test
  public void validAddFlightTest(){
    boolean result = addFlight.jButtonAddActionPerformed(null);
    Assertions.assertTrue(result);
  }

  @Test
  public void emptyFieldAddFlightTest(){

    addFlight.setID("");

    boolean result = addFlight.jButtonAddActionPerformed(null);
    Assertions.assertFalse(result);
  }



}

