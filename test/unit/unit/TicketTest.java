package unit;

import main.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Unit test for Tikcet
 * Created by: Mushfique Shafi
 */
public class TicketTest {

    public Ticket ticketTest = new Ticket();
    private JPanel panel = new JPanel();

    /**
     * @throws ParseException When invalid date is given
     *                        Method to create a valid ticket before testing
     */
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

    /**
     * This is the method to verify all the value
     */
    @Test
    void verifyValues() throws IOException {
        Assertions.assertNotNull(ticketTest);
    }

    /**
     * Method for AutoID Exception Test
     */

    @Test
    public void autoIDExceptionTest() {

        Assertions.assertDoesNotThrow(() -> ticketTest.autoID());

    }

    /**
     * Exception test for Search ticket
     */
    @Test
    public void jButton3ExceptionTest() throws SQLException {
        try {
            ticketTest.jButton3ActionPerformed(null);
        } catch (Exception e) {
            Assertions.assertThrows(SQLException.class, () -> ticketTest.jButton3ActionPerformed(null));
        }
    }

    /**
     * Exception test for search customer
     *
     * @throws SQLException
     */

    @Test
    public void jButton4ExceptionTest() throws SQLException {
        try {
            ticketTest.jButton4ActionPerformed(null);

        } catch (Exception e) {
            Assertions.assertThrows(SQLException.class, () -> ticketTest.jButton4ActionPerformed(null));
        }
    }

    /**
     * Exception test to book ticket
     *
     * @throws SQLException
     */
    @Test
    public void jButton1ExceptionTest() throws SQLException {
        try {
            ticketTest.jButton1ActionPerformed(null);

        } catch (Exception e) {
            Assertions.assertThrows(SQLException.class, () -> ticketTest.jButton1ActionPerformed(null));
        }
    }


    /**
     * Get tables data to the ticket section auto fill test
     */
    @Test
    public void getTableValuesTest() {

        int expected = 13;

        ticketTest.jButton3ActionPerformed(null);

        Assertions.assertEquals(expected, ticketTest.jTable1MouseClicked(null));

        ticketTest.getjTable1().setRowSelectionInterval(0, 0);

        Assertions.assertEquals(expected, ticketTest.jTable1MouseClicked(null));


    }

    /**
     * Add valid ticket test
     */
    @Test
    void jButton1ActionPerformedTest() {
        Assertions.assertTrue(ticketTest.jButton1ActionPerformed(null));
    }

    /**
     * Invalid ticket book method to test
     */
    @Test
    void invalidJButton1ActionPerformedTest() {

        ticketTest.setId("");
        Assertions.assertFalse(ticketTest.jButton1ActionPerformed(null));

        ticketTest.setId("CS001");
        ticketTest.setDate("12-90-09");
        Assertions.assertFalse(ticketTest.jButton1ActionPerformed(null));
    }

    /**
     * Positive test for search ticket
     */
    @Test
    void destinationSourceSelection() {
        ticketTest.setTxtdepart("Uk");
        ticketTest.setTxtsource("India");
        Assertions.assertTrue(ticketTest.jButton3ActionPerformed(null));
    }

    /**
     * Ticket search
     * Negative test to find a invalid search
     */
    @Test
    void flightNotFound() {
        ticketTest.setTxtdepart("Uk");
        ticketTest.setTxtsource("Usa");
        Assertions.assertFalse(ticketTest.jButton3ActionPerformed(null));
    }

    /**
     * Negative test for ticket search
     * both location same
     */
    @Test
    void destinationSourceSelectionFalse() {
        ticketTest.setTxtdepart("Usa");
        ticketTest.setTxtsource("Usa");
        Assertions.assertFalse(ticketTest.jButton3ActionPerformed(null));
    }

    /**
     * Negative test for search customer incorrect Id
     */
    @Test
    void wrongIdInput() {
        ticketTest.setId("S01");
        Assertions.assertFalse(ticketTest.jButton4ActionPerformed(null));
    }

    /**
     * Negative test for search customer empty string
     */
    @Test
    void emptyIdInput() {
        ticketTest.setId("");
        Assertions.assertFalse(ticketTest.jButton4ActionPerformed(null));
    }

    /**
     * Search customer positive test
     */
    @Test
    void correctIdInput() {
        ticketTest.setId("CS001");
        Assertions.assertTrue(ticketTest.jButton4ActionPerformed(null));
    }

    /**
     * Tickets seat quantity test
     */
    @Test
    void verifyTxtSeatsStateChanged() {
        ticketTest.settxtprice("48");
        ticketTest.setTxtseats(22);
        int total = 48 * 22;

        Assertions.assertEquals(total, ticketTest.txtseatsStateChanged(null));
    }
}