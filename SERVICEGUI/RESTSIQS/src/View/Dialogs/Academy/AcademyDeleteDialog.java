package View.Dialogs.Academy;

import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;

public class AcademyDeleteDialog extends JDialog {
    String id;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;

    public AcademyDeleteDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public static void main(String[] args) {
        AcademyDeleteDialog dialog = new AcademyDeleteDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        try {
            HTTPJSONHelper.delete(Constant.ACADEMY_URL + this.id);
            dispose();
        } catch (ConnectException e) {
            AcademyDeleteDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
            e.printStackTrace();
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(String id, String academyName) {
        this.id = id;
        this.label.setText("Delete:" + academyName + "?");
        this.pack();
        this.setLocation(330, 250);
        this.setVisible(true);
    }
}
