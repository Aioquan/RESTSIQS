package View.Dialogs.Student;

import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;

public class StudentDeleteDialog extends JDialog {
    String id;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;

    public StudentDeleteDialog() {
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
        StudentDeleteDialog dialog = new StudentDeleteDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        try {
            HTTPJSONHelper.delete(Constant.STUDENT_URL + "student/" + this.id);
            dispose();
        } catch (ConnectException e) {
            StudentDeleteDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
            e.printStackTrace();
        }

    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(String id, String studentName) {
        this.id = id;
        this.label.setText("Delete:" + studentName + "?");
        this.pack();
        this.setLocation(330, 250);
        this.setVisible(true);
    }
}
