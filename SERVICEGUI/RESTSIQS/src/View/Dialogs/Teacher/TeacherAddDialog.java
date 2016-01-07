package View.Dialogs.Teacher;

import Beans.HTTPEntities.Teacher;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class TeacherAddDialog extends JDialog {
    HashMap<String, Object> map;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfTeacherId;
    private JTextField tfTeacherName;
    private JTextField tfTeacherDepartment;
    private JTextField tfTeacherStatus;

    public TeacherAddDialog() {
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
        TeacherAddDialog dialog = new TeacherAddDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        if (!hasEmpty()) {
            if (saveTeacher()) {
                dispose();
            } else {
                TeacherAddDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
            }
        } else {
            TeacherAddDialog.this.setTitle(Constant.ERROR_HAS_EMPTY);
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(HashMap<String, Object> map) {

        this.map = map;
        this.tfTeacherId.setText("");
        this.tfTeacherName.setText("");
        this.tfTeacherDepartment.setText("");
        this.tfTeacherStatus.setText("");
        this.pack();
        this.setBounds(230, 150, 650, 450);
        this.setVisible(true);
    }

    private boolean saveTeacher() {
        Teacher teacher = new Teacher();
        teacher.setAcademyId((String) this.map.get("academyId"));
        teacher.setTeacherDepartment(tfTeacherDepartment.getText());
        teacher.setTeacherId(tfTeacherId.getText());
        teacher.setTeacherName(tfTeacherName.getText());
        teacher.setTeacherStatus(tfTeacherStatus.getText());
        try {
            HTTPJSONHelper.post(Constant.TEACHER_URL + "teacher/", teacher);
            return true;
        } catch (ConnectException e) {
            e.printStackTrace();
            TeacherAddDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
        }
        return false;
    }

    public boolean hasEmpty() {
        boolean flag = false;

        if (tfTeacherId.getText().isEmpty())
            flag = true;
        if (tfTeacherName.getText().isEmpty())
            flag = true;
        if (tfTeacherDepartment.getText().isEmpty())
            flag = true;
        if (tfTeacherStatus.getText().isEmpty())
            flag = true;

        return flag;
    }
}
