package main;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

public class AddflightPerformanceTest {

	private Addflight addFlight = new Addflight();
	private Main main = new Main();


	@BeforeEach
	public void initCustomer() throws IOException, ParseException {
		addFlight.setID("1");
		addFlight.setFlightName("American Airlines");
		addFlight.setSource("USA");
		addFlight.setDepart("Canada");
		addFlight.setDate("1997-08-02");
		addFlight.setDepartTime("12:00");
		addFlight.setArrTime("4:00");
		addFlight.setFlightCharge("200");
	}

	//Will open multiple threads
	@Test
	public void multipleLoginWindowsTest() {

for(int i = 0; i < 10; i++) {
	new Thread(() -> {
		main.jMenuItemAddflightActionPerformed(null);
		System.out.println(Thread.currentThread().getName());
	}).start();

}

	}

	@Test
	public void oneUserMultipleFlightsTest() {

		Assertions.assertTrue(addFlight.jButtonAddActionPerformed(null));
	}
	@Test
	public void multipleUsersOneFlightTest() {

		Assertions.assertTrue(addFlight.jButtonAddActionPerformed(null));

	}


}
