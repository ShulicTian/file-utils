import utils.filecopy.HomePage;

import javax.swing.*;

/**
 * @author T6094
 */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage();
            }
        });
    }

}
