import View.MainView;

import javax.swing.*;

/**
 * Created by devouty on 2015/10/28.
 */
public class Main {
    public static final void main(String[] args) {
        startGUI();
    }

    private static void startGUI() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            new MainView();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
