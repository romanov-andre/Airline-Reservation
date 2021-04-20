package main;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TicketReportIntegrationTest {

	@Mock
	private MysqlDataSource ds;

	@Mock
	private Connection c;

	@Mock
	private PreparedStatement stmt;

	@Mock
	private ResultSet rs;

	@Mock
	private ResultSetMetaData rsmd;

	private Ticketreport reportTester;
	private AutoCloseable closeable;

	@BeforeEach
	public void setUp() throws Exception {

		closeable = MockitoAnnotations.openMocks(this);

		Assertions.assertNotNull(ds);

		reportTester = new Ticketreport(ds);

		System.out.println("Before");

	}

	@AfterEach
	public void teardown() throws Exception {
		System.out.println("Closing");
		closeable.close();
	}

	@Test
	public void mockLoadDataTest() throws SQLException {

		when(ds.getConnection()).thenReturn(c);
		when(c.prepareStatement(any(String.class))).thenReturn(stmt);
		when(stmt.executeQuery()).thenReturn(rs);
		when(rs.getMetaData()).thenReturn(rsmd);
		when(rsmd.getColumnCount()).thenReturn(1);
		when(rs.getString("id")).thenReturn("13");
		when(rs.getString("flightid")).thenReturn("14");
		when(rs.getString("custid")).thenReturn("12");
		when(rs.getString("class")).thenReturn("Economy");
		when(rs.getString("price")).thenReturn("50");
		when(rs.getString("seats")).thenReturn("500");
		when(rs.getString("date")).thenReturn("2021-04-06");
		when(rs.next()).thenReturn(true).thenReturn(false);

		reportTester.LoadData();

		verify(rs, times(7)).getString(any(String.class));

	}

	@Test
	public void mockTicketReportExceptionTest() {

		try {
			when(ds.getConnection()).thenReturn(c);
			when(c.prepareStatement(any(String.class)))
					.thenThrow(new SQLException("Failed to connect to db"));

			reportTester.LoadData();

		} catch (SQLException e) {
			Assertions.assertEquals(e.getMessage(), "Failed to connect to db");
		}

	}

}

