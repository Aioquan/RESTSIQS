package View.Dialog;

import javax.swing.*;
import java.awt.event.*;

public class CourseEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblCoureseName;
    private JTextField tfCourseName;
    private JPanel btnPanel;
    private JPanel contextPanel;
    private JTextField tfCredit;
    private JLabel lblCredit;
    private JLabel lblStudentId;
    private JTextField tfStudentId;
    private JLabel lblTeacherId;
    private JTextField textField1;
    private JLabel lblSum;
    private JTextField tfSum;
    private JTextField tfFinalTest;
    private JTextField tfDailyMark;
    private JLabel lblFinalTest;
    private JLabel lblDailyMark;
    private JTextField tfTest1;
    private JTextField tfTest2;
    private JTextField tfTest3;
    private JTextField tfE1;
    private JTextField tfE2;
    private JTextField tfE3;
    private JTextField tfE4;
    private JTextField tfE5;
    private JLabel blank;
    private JLabel lblTest1;
    private JLabel lblTest2;
    private JLabel lblTest3;
    private JLabel lblE1;
    private JLabel lblE2;
    private JLabel lblE3;
    private JLabel lblE4;
    private JLabel lblE5;

    public CourseEditDialog() {
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
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CourseEditDialog dialog = new CourseEditDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
