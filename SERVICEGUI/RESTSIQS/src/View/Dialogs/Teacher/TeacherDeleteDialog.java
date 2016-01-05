package View.Dialogs.Teacher;

import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;

public class TeacherDeleteDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;

    public TeacherDeleteDialog() {
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

    private void onOK() {
// add your code here
        try {
            HTTPJSONHelper.delete(Constant.TEACHER_URL+ "teacher/" + this.id);
        } catch (ConnectException e) {
            TeacherDeleteDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        TeacherDeleteDialog dialog = new TeacherDeleteDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    String id;
    public void show(String id, String teacherName) {
        this.id = id;
        this.label.setText("Delete:" + teacherName + "?");
        this.pack();
        this.setLocation(230, 150);
        this.setVisible(true);
    }
}
