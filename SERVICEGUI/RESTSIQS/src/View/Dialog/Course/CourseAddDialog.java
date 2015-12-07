package View.Dialog.Course;

import Beans.HTTPEntities.Course;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class CourseAddDialog extends JDialog {
    //number input limitation
    KeyListener NumLimitListener = new KeyListener() {
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar() < '0' || e.getKeyChar() > '9') {
                e.consume();
                return;
            }
        }

        public void keyPressed(KeyEvent e) {
        }

        public void keyReleased(KeyEvent e) {
        }
    };
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblStudentId;
    private JLabel lblCredit;
    private JPanel dPanel;
    private JPanel btnPanel;
    private JPanel contextPanel;
    private JLabel lblCoureseName;
    private JTextField tfCourseName;
    private JTextField tfCredit;
    private JTextField tfStudentId;
    private JLabel lblTeacherId;
    private JTextField tfTeacherId;
    private JLabel lblSum;
    private JTextField tfSum;
    private JLabel lblFinalTest;
    private JTextField tfFinalTest;
    private JLabel lblDailyMark;
    private JTextField tfDailyMark;
    private JLabel lblTest1;
    private JLabel lblTest3;
    private JLabel lblTest2;
    private JTextField tfTest1;
    private JTextField tfTest2;
    private JTextField tfTest3;
    private JLabel blank;
    private JLabel lblE1;
    private JLabel lblE2;
    private JLabel lblE3;
    private JLabel lblE4;
    private JLabel lblE5;
    private JTextField tfE1;
    private JTextField tfE2;
    private JTextField tfE3;
    private JTextField tfE4;
    private JTextField tfE5;
    private JTextField tfCourseDate;
    private JTextField tfCourseTime;
    private JLabel lblCourseTime;
    private JLabel lblCourseDate;
    private JLabel courseDate;
    private HashMap<String, Object> map;
    private Object[][] data;

    public CourseAddDialog() {
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

        //number limit
        tfTest1.addKeyListener(NumLimitListener);
        tfTest2.addKeyListener(NumLimitListener);
        tfTest3.addKeyListener(NumLimitListener);
        tfE1.addKeyListener(NumLimitListener);
        tfE2.addKeyListener(NumLimitListener);
        tfE3.addKeyListener(NumLimitListener);
        tfE4.addKeyListener(NumLimitListener);
        tfE5.addKeyListener(NumLimitListener);
        tfFinalTest.addKeyListener(NumLimitListener);
        tfDailyMark.addKeyListener(NumLimitListener);
        tfCredit.addKeyListener(NumLimitListener);
        tfSum.addKeyListener(NumLimitListener);
    }

    public static void main(String[] args) {
        CourseAddDialog dialog = new CourseAddDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        if (hasEmpty()) {
            this.setTitle(Constant.ERROR_HAS_EMPTY);
        } else {
            addCourse();
            tfCourseDate.setText("");
            tfCourseTime.setText("");
            tfCourseName.setText("");
            tfCredit.setText("");
            tfTeacherId.setText("");
            tfStudentId.setText("");

            dispose();
        }

    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(Object[][] data, HashMap<String, Object> map) {
        this.data = data;
        this.map = map;
        this.pack();
        this.setLocation(130, 150);
        this.setVisible(true);
    }

    private void addCourse() {
        int newId = (Integer) map.get("newId");

        try {
//            JSONObject jsonObject = HTTPJSONHelper.get(Constant.COURSE_URL + id);
//            JSONObject requestObj = (JSONObject) ((JSONArray) (jsonObject.get("result"))).get(0);
            Course course = new Course();

            course.setCourseDate(tfCourseDate.getText());
            course.setCourseId(newId + "");
            course.setCourseName(tfCourseName.getText());
            course.setCourseTime(tfCourseTime.getText());
            course.setCredit(Double.parseDouble(tfCredit.getText()));

            course.setDailyMark(Double.parseDouble(tfDailyMark.getText()));
            course.setStudentId(tfStudentId.getText());
            course.setSum(Double.parseDouble(tfSum.getText()));
            course.setFinalTest(Double.parseDouble(tfFinalTest.getText()));
            course.setTeacherId(tfTeacherId.getText());

            course.setExercises1(Double.parseDouble(tfE1.getText()));
            course.setExercises2(Double.parseDouble(tfE2.getText()));
            course.setExercises3(Double.parseDouble(tfE3.getText()));
            course.setExercises4(Double.parseDouble(tfE4.getText()));
            course.setExercises5(Double.parseDouble(tfE5.getText()));

            course.setTest1(Double.parseDouble(tfTest1.getText()));
            course.setTest2(Double.parseDouble(tfTest2.getText()));
            course.setTest3(Double.parseDouble(tfTest3.getText()));


            HTTPJSONHelper.post(Constant.COURSE_URL + "course/", course);

//            System.out.println(jsonObject.toJSONString());
        } catch (ArrayIndexOutOfBoundsException e) {
//            e.printStackTrace();
        } catch (ConnectException connectException) {
            connectException.printStackTrace();
        }

    }

    public boolean hasEmpty() {
        boolean flag = false;

        if (tfCourseName.getText().isEmpty())
            flag = true;
        if (tfCredit.getText().isEmpty())
            flag = true;
        if (tfDailyMark.getText().isEmpty())
            flag = true;
        if (tfE1.getText().isEmpty())
            flag = true;
        if (tfE2.getText().isEmpty())
            flag = true;
        if (tfE3.getText().isEmpty())
            flag = true;
        if (tfE4.getText().isEmpty())
            flag = true;
        if (tfE5.getText().isEmpty())
            flag = true;
        if (tfTest1.getText().isEmpty())
            flag = true;
        if (tfTest2.getText().isEmpty())
            flag = true;
        if (tfTest3.getText().isEmpty())
            flag = true;
        if (tfFinalTest.getText().isEmpty())
            flag = true;
        if (tfStudentId.getText().isEmpty())
            flag = true;
        if (tfTeacherId.getText().isEmpty())
            flag = true;
        if (tfCourseDate.getText().isEmpty())
            flag = true;
        if (tfCourseTime.getText().isEmpty())
            flag = true;

        return flag;
    }
}
