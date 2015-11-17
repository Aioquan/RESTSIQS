package View.Dialog.Student;

import Beans.EditButtonRenderer;
import Utils.Constant;
import Utils.HTTPJSONHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class StudentEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel studentMessagePanel;
    private JPanel studentCoursePanel;
    private JPanel studentTEPanel;
    private JTable studentCourseTable;
    private JTable studentTETable;
    private JTextField tfStudentPassword;
    private JTextField tfSex;
    private JTextField tfIdentityCard;
    private JTextField tfBankCard;
    private JTextField tfAcademyId;
    private JTextField tfStudentId;

    public StudentEditDialog() {
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
        StudentEditDialog dialog = new StudentEditDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    Object[][] d;
    HashMap<String, Object> map;
    EditButtonRenderer studentTableBtnEdit,studentTableBtnDelete;
    public void show(Object[][] data, HashMap<String, Object> map) {
        this.d = data;
        this.map = map;
        tfAcademyId.setText((String) map.get("academyId"));
        tfBankCard.setText((String) map.get("bankCard"));
        tfIdentityCard.setText((String) map.get("identityCard"));
        tfSex.setText((String) map.get("sex"));
        tfStudentId.setText((String) map.get("studentId"));
        tfStudentPassword.setText((String) map.get("studentPassword"));
        studentTableBtnEdit = new EditButtonRenderer("edit");
        studentTableBtnDelete = new EditButtonRenderer("delete");

        readTable();
        this.setBounds(230,150,650,450);
        this.setVisible(true);
    }

    public void readTable() {
        try{

            JSONObject courseJSONObject = HTTPJSONHelper.get(Constant.COURSE_URL + "student/" + this.map.get("studentId"));
            JSONArray jsonArray = (JSONArray) courseJSONObject.get("result");
            int length = jsonArray.size();
            JSONObject obj;
            Object[][] data = null;
            data = new Object[length][18];
            for (int i = 0; i < length; i++) {
                obj = (JSONObject) jsonArray.get(i);
                data[i][0] = "edit";
                data[i][1] = "delete";

                data[i][2] = obj.getDouble("credit");
                data[i][3] = obj.getString("teacherId");
                data[i][4] = obj.getString("studentId");
                data[i][5] = obj.getString("courseName");
                data[i][6] = obj.getDouble("sum");

                data[i][7] = obj.getDouble("finalTest");
                data[i][8] = obj.getDouble("dailyMark");
                data[i][9] = obj.getDouble("test1");
                data[i][10] = obj.getDouble("test2");
                data[i][11] = obj.getDouble("test3");

                data[i][12] = obj.getDouble("exercises1");
                data[i][13] = obj.getDouble("exercises2");
                data[i][14] = obj.getDouble("exercises3");
                data[i][15] = obj.getDouble("exercises4");
                data[i][16] = obj.getDouble("exercises5");

                data[i][17] = obj.getString("courseId");

            }
            Object[] names = {
                    "edit",
                    "delete",

                    "credit",
                    "teacherId",
                    "studentId",
                    "courseName",
                    "sum",

                    "finalTest",
                    "dailyMark",
                    "test1",
                    "test2",
                    "test3",

                    "exercises1",
                    "exercises2",
                    "exercises3",
                    "exercises4",
                    "exercises5",
                    "courseId"};

            DefaultTableModel model = new DefaultTableModel(data, names) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            try {
                studentCourseTable.setModel(model);
            } catch (ArrayIndexOutOfBoundsException e) {
                StudentEditDialog.this.setTitle("");
            }
            studentCourseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            studentCourseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            studentCourseTable.getColumn("edit").setCellRenderer(studentTableBtnEdit);
            studentCourseTable.getColumn("delete").setCellRenderer(studentTableBtnDelete);
            studentCourseTable.setDoubleBuffered(false);
            /*
            tId                  varchar(255) not null,
            tName                varchar(255) default "нч",
            tDate                varchar(255) default "нч",
            tSorce               double default 0,
            studentId		varchar(255) default "нч",
             */
            JSONObject TEJSONObject = HTTPJSONHelper.get(Constant.TECNOLOGICALEXAM_URL + "student/" + this.map.get("studentId"));
            jsonArray = (JSONArray) courseJSONObject.get("result");
            length = jsonArray.size();

            data = null;
            data = new Object[length][7];
            for (int i = 0; i < length; i++) {
                obj = (JSONObject) jsonArray.get(i);
                data[i][0] = "edit";
                data[i][1] = "delete";

                data[i][2] = obj.getDouble("tId");
                data[i][3] = obj.getString("tName");
                data[i][4] = obj.getString("tDate");
                data[i][5] = obj.getString("tSorce");
                data[i][6] = obj.getDouble("studentId").intValue();
            }
            Object[] tNames = {
                    "edit",
                    "delete",

                    "tId",
                    "tName",
                    "tDate",
                    "tSorce",
                    "studentId"};

             model = new DefaultTableModel(data, tNames) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            try {
                studentTETable.setModel(model);
            } catch (ArrayIndexOutOfBoundsException e) {
                StudentEditDialog.this.setTitle("");
            }
            studentTETable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            studentTETable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            studentTETable.getColumn("edit").setCellRenderer(studentTableBtnEdit);
            studentTETable.getColumn("delete").setCellRenderer(studentTableBtnDelete);
            studentTETable.setDoubleBuffered(false);
        }catch (ConnectException e)
        {
            e.printStackTrace();
        }
    }

}
