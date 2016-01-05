package View.Dialogs.Course;

import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;

public class CourseDeleteDialog extends JDialog {
    String id;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel btnPanel;
    private JPanel lblPanel;
    private JLabel label;

    public CourseDeleteDialog() {
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
        CourseDeleteDialog dialog = new CourseDeleteDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        try {
            HTTPJSONHelper.delete(Constant.COURSE_URL + this.id);
            dispose();
        } catch (ConnectException e) {
            e.printStackTrace();
        }

    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(String courseName, String id) {
        this.id = id;
        this.label.setText("Delete:" + courseName + "?");
        this.pack();
        this.setLocation(130, 150);
        this.setVisible(true);
    }
}
