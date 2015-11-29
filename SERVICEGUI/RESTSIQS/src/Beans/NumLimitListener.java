package Beans;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by devouty on 2015/11/29.
 */
public class NumLimitListener implements KeyListener {
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() < '0' ||
                e.getKeyChar() > '9') {
            e.consume();
            return;
        }
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {
    }
}
