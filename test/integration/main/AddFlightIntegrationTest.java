package main;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddFlightIntegrationTest {

	@Mock
	private MysqlDataSource ds;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;

	private Addflight flightTester;
	private AutoCloseable closeable;

	private Addflight sampleFlight;


	@BeforeEach
	public void setUp() throws Exception {

		closeable = MockitoAnnotations.openMocks(this);

		Assertions.assertNotNull(ds);

sampleFlight = new Addflight();

		sampleFlight.setID("1");
		sampleFlight.setFlightName("American Airlines");
		sampleFlight.setSource("USA");
		sampleFlight.setDepart("Canada");
		sampleFlight.setDate("1997-08-02");
		sampleFlight.setDepartTime("12:00");
		sampleFlight.setArrTime("4:00");
		sampleFlight.setFlightCharge("200");

		flightTester = new Addflight(ds);

		flightTester.setID("1");
		flightTester.setFlightName("American Airlines");
		flightTester.setSource("USA");
		flightTester.setDepart("Canada");
		flightTester.setDate("1997-08-02");
		flightTester.setDepartTime("12:00");
		flightTester.setArrTime("4:00");
		flightTester.setFlightCharge("200");

		System.out.println("Before");

	}

	@AfterEach
	public void teardown() throws Exception {
		System.out.println("Closing");
		closeable.close();
	}

	@Test
	void positiveAddFlightTest() throws Exception {

		Assertions.assertTrue(sampleFlight.jButtonAddActionPerformed(null));

	}

	@Test
	void testValidFlightWithMock() throws Exception {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeUpdate()).thenReturn(1);

		flightTester.jButtonAddActionPerformed(null);


		verify(stmt, times(1)).executeUpdate();


	}

	@Test
	void testInvalidFlightWithMock() throws Exception {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeUpdate()).thenReturn(1);

		flightTester.setDepartTime("");
		flightTester.jButtonAddActionPerformed(null);


		verify(stmt, times(0)).executeUpdate();


	}

	@Test
	void testFlightExceptionWithMock() throws Exception {

		try {
			when(ds.getConnection()).thenReturn(c);
			when(c.prepareStatement(any(String.class))).thenReturn(stmt);
			when(stmt.executeUpdate()).thenThrow(new SQLException("Failed to connect to db"));

			flightTester.jButtonAddActionPerformed(null);


		} catch (SQLException e){
			Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
		}


		verify(stmt, times(0)).executeUpdate();


	}

	@Test
	public void testCancelButton() {
		sampleFlight.jButtonCancelActionPerformed(null);
	}



}
