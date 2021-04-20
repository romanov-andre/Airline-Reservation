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
        ticketTest.setId("CS001");
        ticketTest.setTicketid("14");
        ticketTest.setFlightid("F0001");
        ticketTest.setTxtfirstname("Jhon");
        ticketTest.setTxtlastname("Alex");
        ticketTest.settxtprice("50");
        ticketTest.setTxtseats(10);
        ticketTest.setTxttotal(500);
        ticketTest.setDate("2020-09-09");
    }

    @Test
    void verifyValues() throws IOException {

        Assertions.assertNotNull(ticketTest);
    }

    @Test
    void jButton1ActionPerformedTest(){
        Assertions.assertTrue(ticketTest.jButton1ActionPerformed(null));
    }
    @Test
    void desitnationSourceSelection(){
        ticketTest.setTxtdepart("Uk");
        ticketTest.setTxtsource("India");
        Assertions.assertTrue(ticketTest.jButton3ActionPerformed(null));
    }
    @Test
    void flightNotFound(){
        ticketTest.setTxtdepart("Uk");
        ticketTest.setTxtsource("Usa");
        Assertions.assertFalse(ticketTest.jButton3ActionPerformed(null));
    }
    @Test
    void desitnationSourceSelectionFalse(){
        ticketTest.setTxtdepart("Usa");
        ticketTest.setTxtsource("Usa");
        Assertions.assertFalse(ticketTest.jButton3ActionPerformed(null));
    }
    @Test
    void wrongIdInput(){
        ticketTest.setId("S01");
        Assertions.assertFalse(ticketTest.jButton4ActionPerformed(null));
    }
    @Test
    void emptyIdInput(){
        ticketTest.setId("");
        Assertions.assertFalse(ticketTest.jButton4ActionPerformed(null));
    }
    @Test
    void correctIdInput(){
        ticketTest.setId("CS001");
        Assertions.assertTrue(ticketTest.jButton4ActionPerformed(null));
    }

    void verifyTxtSeatsStateChanged(){
        ticketTest.settxtprice("48");
        ticketTest.setTxtseats(22);
        int total = 48 * 22;

        Assertions.assertEquals(total, 1056);
    }
}