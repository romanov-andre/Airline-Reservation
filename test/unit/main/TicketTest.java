package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

public class TicketTest {

  public Ticket ticketTest = new Ticket();
  private JPanel panel = new JPanel();

  @BeforeEach
  void initTicket() throws IOException {
    ticketTest.setId("12");
    ticketTest.setTicketid("13");
    ticketTest.setFlightid("14");
    ticketTest.setFlightid("14");
    ticketTest.setTxtfirstname("test");
    ticketTest.setTxtlastname("name");
    ticketTest.settxtprice("50");
    ticketTest.setTxtseats(10);
    ticketTest.setTxtseats(10);
    ticketTest.setTxttotal(500);
ticketTest.setDate();
  }

  @Test
  void verifyValues() throws IOException {

    Assertions.assertNotNull(ticketTest);
  }

  @Test
  void jButton1ActionPerformedTest(){
    Assertions.assertTrue(ticketTest.jButton1ActionPerformed(null));
  }

  void verifyTxtSeatsStateChanged(){
    ticketTest.settxtprice("48");
    ticketTest.setTxtseats(22);
    int total = 48 * 22;

    Assertions.assertEquals(total, 1056);
  }
}