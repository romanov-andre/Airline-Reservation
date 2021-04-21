package main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;

class MainIntegrationTest {


    public Main main = new Main();

    @Mock
    private JOptionPane pane;


    @BeforeEach
    public void setUp() throws Exception {

        closeable = MockitoAnnotations.openMocks(this);

        System.out.println("Before");

    }

    @AfterEach
    public void teardown() throws Exception {
        System.out.println("Closing");
        closeable.close();
    }

    //methods 1-6 tests valid inputs with assertion true, with relation to the main, with boolean methods returning true
    @Test
    void testMainValid() throws Exception{

        main.jMenuItemAddCusActionPerformed(null);

        Assertions.assertTrue(main.jMenuItemAddCusActionPerformed(null));
    }

    @Test
    void testMainValid2() throws Exception{
        main.jMenuItemSearchCusActionPerformed(null);

        Assertions.assertTrue(main.jMenuItemSearchCusActionPerformed(null));
    }

    @Test
    void testMainValid3() throws Exception{
        main.jMenuItemAddflightActionPerformed(null);

        Assertions.assertTrue(main.jMenuItemAddflightActionPerformed(null));
    }

    @Test
    void testMainValid4() throws Exception{
        main.jMenuItemBookTicketActionPerformed(null);

        Assertions.assertTrue(main.jMenuItemBookTicketActionPerformed(null));
    }

    @Test
    void testMainValid5() throws Exception{
        main.jMenuItemUserCreationActionPerformed(null);

        Assertions.assertTrue(main.jMenuItemUserCreationActionPerformed(null));
    }

    @Test
    void testMainValid6() throws Exception{
        main.jMenuItemTicketReportActionPerformed(null);

        Assertions.assertTrue(main.jMenuItemTicketReportActionPerformed(null));
    }

    //Handles exceptions, also tests Invalids tests by catching asserted false button presses
   @Test
    public void testMainExceptionHandlerTest() {

        try {
            main.jMenuItemAddCusActionPerformed(null);
            main.jMenuItemSearchCusActionPerformed(null);
            main.jMenuItemAddflightActionPerformed(null);
            main.jMenuItemBookTicketActionPerformed(null);
            main.jMenuItemUserCreationActionPerformed(null);
            main.jMenuItemTicketReportActionPerformed(null);


        } catch (Exception e) {
            Assertions.assertFalse(main.jMenuItemAddCusActionPerformed(null));
            Assertions.assertFalse(main.jMenuItemSearchCusActionPerformed(null));
            Assertions.assertFalse(main.jMenuItemAddflightActionPerformed(null));
            Assertions.assertFalse(main.jMenuItemBookTicketActionPerformed(null));
            Assertions.assertFalse(main.jMenuItemUserCreationActionPerformed(null));
            Assertions.assertFalse(main.jMenuItemTicketReportActionPerformed(null));
            Assertions.assertEquals(e.getMessage(), "Failed");
        }

    }

    private AutoCloseable closeable;


}