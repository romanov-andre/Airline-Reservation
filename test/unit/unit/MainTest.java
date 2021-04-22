package unit;

import main.Main;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class MainTest {
    public Main main = new Main();
    private JPanel panel = new JPanel();


    //Tests determine an action performed for main, by testing each specific menu action
    @Test
    void jMenuItemAddCusActionPerformed(){
        main.jMenuItemAddCusActionPerformed(null);
    }
    @Test
    void jMenuItemSearchCusActionPerformed(){
        main.jMenuItemSearchCusActionPerformed(null);
    }
    @Test
    void jMenuItemAddflightActionPerformed(){
        main.jMenuItemAddflightActionPerformed(null);
    }
    @Test
    void jMenuItemBookTicketActionPerformed(){
        main.jMenuItemBookTicketActionPerformed(null);
    }
    @Test
    void jMenuItemTicketReportActionPerformed(){
        main.jMenuItemUserCreationActionPerformed(null);
    }
    @Test
    void jMenuItemUserCreationActionPerformed(){
        main.jMenuItemTicketReportActionPerformed(null);
    }

}