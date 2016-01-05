package View.Dialogs.Student;

import Beans.HTTPEntities.Student;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class StudentAddDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfStudentPassword;
    private JTextField tfAcademyId;
    private JTextField tfSex;
    private JTextField tfIdentityCard;
    private JTextField tfBankCard;
    private JLabel lblStudentId;

    public StudentAddDialog() {
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
        StudentAddDialog dialog = new StudentAddDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        if (!hasEmpty()) {
            Student student = new Student();
            student.setStudentPassword(tfStudentPassword.getText());
            student.setSex(tfSex.getText());
            student.setAcademyId(tfAcademyId.getText());
            student.setIdentityCard(tfIdentityCard.getText());
            student.setBankCard(tfBankCard.getText());
            student.setStudentId((Integer)this.map.get("newId")+"");
            try {
                HTTPJSONHelper.post(Constant.STUDENT_URL + "student/", student);
                tfAcademyId.setText("");
                tfBankCard.setText("");
                tfIdentityCard.setText("");
                tfSex.setText("");
                tfStudentPassword.setText("");
                dispose();
            } catch (ConnectException e) {
                StudentAddDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
                e.printStackTrace();
            } catch (Exception ee) {
                StudentAddDialog.this.setTitle(Constant.ERROR_NOT_LEGAL);
                ee.printStackTrace();
            }
        } else {
            StudentAddDialog.this.setTitle(Constant.ERROR_HAS_EMPTY);
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
    HashMap<String, Object> map;
    public void show(Object[][] data, HashMap<String, Object> map) {
        this.map = map;
        this.pack();
        lblStudentId.setText((Integer)map.get("newId")+"");
        this.setLocation(300, 200);
        this.setVisible(true);
    }

    private boolean hasEmpty() {
        boolean flag = false;

        if (tfStudentPassword.getText().isEmpty())
            flag = true;
        if (tfSex.getText().isEmpty())
            flag = true;
        if (tfIdentityCard.getText().isEmpty())
            flag = true;
        if (tfAcademyId.getText().isEmpty())
            flag = true;
        if (tfBankCard.getText().isEmpty())
            flag = true;

        return flag;
    }
}
