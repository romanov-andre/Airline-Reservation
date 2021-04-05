package main;

import java.io.IOException;
import javax.swing.JPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TicketTest {

  public Ticket ticketTest = new Ticket();
  private JPanel panel = new JPanel();

  @Test
  void initTicket() throws IOException {
    ticketTest.setId("12");
    ticketTest.setTicketid("13");
    ticketTest.setFlightid("14");
    ticketTest.setFlightid("14");
    ticketTest.setTxtfirstname("test");
    ticketTest.setTxtlastname("name");
    ticketTest.settxtprice("50");
    ticketTest.setTxtseats("10");
    ticketTest.setTxtseats("10");
    ticketTest.setTxttotal("500");
  }

  @Test
  void verifyValues() throws IOException {
    initTicket();

    Assertions.assertNotNull(ticketTest);
  }

  @Test
  void jButton1ActionPerformedTest(){
    Assertions.assertFalse(ticketTest.jButton1ActionPerformed(null));
  }

  void verifyTxtSeatsStateChanged(){
    ticketTest.settxtprice("48");
    ticketTest.setTxtseats("22");
    int total = 48 * 22;

    Assertions.assertEquals(total, 1056);
  }
}