package unit;

import main.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class TicketTest {

    public Ticket ticketTest = new Ticket();
    private JPanel panel = new JPanel();

    @BeforeEach
    void initTicket() throws IOException {
        ticketTest.setId("CS001");
        ticketTest.setTicketid("14");
        ticketTest.setFlightid("F0001");
        ticketTest.setTxtfirstname("Jhon");
        ticketTest.setPassport("test");
        ticketTest.setTxtlastname("Alex");
        ticketTest.settxtprice("50");
        ticketTest.setTxtseats(10);
        ticketTest.setTxttotal(500);
        ticketTest.setDate("2020-09-19");
    }

    @Test
    void verifyValues() throws IOException {
        Assertions.assertNotNull(ticketTest);
    }

    @Test
    public void autoIDExceptionTest() {

        Assertions.assertDoesNotThrow(() -> ticketTest.autoID());

    }

    @Test
    public void jButton3ExceptionTest() throws SQLException {

        ticketTest.setPst("Select");

        Assertions.assertThrows(SQLException.class, () -> ticketTest.jButton3ActionPerformed(null));


    }

    @Test
    public void jButton4ExceptionTest() throws SQLException {

        ticketTest.setPst("Select");

        Assertions.assertThrows(SQLException.class, () -> ticketTest.jButton4ActionPerformed(null));


    }

    @Test
    public void jButton1ExceptionTest() throws SQLException {

        ticketTest.setPst("Select");

        Assertions.assertThrows(SQLException.class, () -> ticketTest.jButton1ActionPerformed(null));


    }


    @Test
    public void getTableValuesTest() {

        int expected = 45;

        ticketTest.jButton3ActionPerformed(null);

        Assertions.assertEquals(expected, ticketTest.jTable1MouseClicked(null));

        ticketTest.getjTable1().setRowSelectionInterval(0, 0);

        Assertions.assertEquals(expected, ticketTest.jTable1MouseClicked(null));



    }

    @Test
    void jButton1ActionPerformedTest(){
        Assertions.assertTrue(ticketTest.jButton1ActionPerformed(null));
    }

    @Test
    void invalidJButton1ActionPerformedTest(){

        ticketTest.setId("");
        Assertions.assertFalse(ticketTest.jButton1ActionPerformed(null));

        ticketTest.setId("CS001");
        ticketTest.setDate("12-90-09");
        Assertions.assertFalse(ticketTest.jButton1ActionPerformed(null));
    }
    @Test
    void destinationSourceSelection(){
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
    void destinationSourceSelectionFalse(){
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

    @Test
    void verifyTxtSeatsStateChanged(){
        ticketTest.settxtprice("48");
        ticketTest.setTxtseats(22);
        int total = 48 * 22;

        Assertions.assertEquals(total, ticketTest.txtseatsStateChanged(null) );
    }
}