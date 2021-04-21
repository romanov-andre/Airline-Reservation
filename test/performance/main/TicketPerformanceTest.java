package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.IOException;

public class TicketPerformanceTest {


	private JPanel panel = new JPanel();
	private Main main = new Main();
	private Ticket ticketTest = new Ticket();

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

	//Will open multiple threads
	@Test
	public void multipleTicketWindowsTest() {
		Assertions.assertDoesNotThrow(() -> main.jMenuItemBookTicketActionPerformed(null));
	}

	//Should use threads to have one users add multiple tickets to the database
	@Test
	public void oneUserMultipleTicketsTest() {

		Assertions.assertTrue(ticketTest.jButton1ActionPerformed(null));

	}

	//Should use threads to have multiples users add a ticket to the database
	@Test
	public void multipleUsersOneTicketTest() {

		Assertions.assertTrue(ticketTest.jButton1ActionPerformed(null));

	}





}
