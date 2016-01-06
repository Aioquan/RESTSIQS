package View.Dialogs.Teacher;

import Beans.HTTPEntities.Teacher;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class TeacherEditDialog extends JDialog {
    Object[][] data;
    HashMap<String, Object> map;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfTeacherName;
    private JTextField tfTeacherDepartment;
    private JTextField tfTeacherStatus;
    private JTextField tfTeacherId;

    public TeacherEditDialog() {
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
        TeacherEditDialog dialog = new TeacherEditDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        if (!hasEmpty()) {
            if (updateTeacher()) {
                dispose();
            } else {
                TeacherEditDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
            }
        } else {
            TeacherEditDialog.this.setTitle(Constant.ERROR_HAS_EMPTY);
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(Object[][] data, HashMap<String, Object> map) {
        this.data = data;
        this.map = map;
        this.tfTeacherId.setText((String) this.map.get("teacherId"));
        this.tfTeacherName.setText((String) this.map.get("teacherName"));
        this.tfTeacherDepartment.setText((String) this.map.get("teacherDepartment"));
        this.tfTeacherStatus.setText((String) this.map.get("teacherStatus"));
        this.pack();
        this.setBounds(230, 150, 650, 450);
        this.setVisible(true);

    }

    private boolean updateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setAcademyId((String) this.map.get("academyId"));
        teacher.setTeacherDepartment(tfTeacherDepartment.getText());
        teacher.setTeacherId(tfTeacherId.getText());
        teacher.setTeacherName(tfTeacherName.getText());
        teacher.setTeacherStatus(tfTeacherStatus.getText());
        try {
            HTTPJSONHelper.put(Constant.TEACHER_URL + "teacher/", teacher);
            return true;
        } catch (ConnectException e) {
            e.printStackTrace();
            TeacherEditDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
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
