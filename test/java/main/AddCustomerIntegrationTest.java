package main;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class AddCustomerIntegrationTest {


  @Test
  public void mockAddUser(){
    AddCustomer mock = Mockito.mock(AddCustomer.class);
    when(mock.jButtonAddActionPerformed(null)).thenReturn(true);
    boolean test = mock.jButtonAddActionPerformed(null);
    assertTrue(test);
  }

}