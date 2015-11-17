package View;

import Beans.EditButtonRenderer;
import Utils.Constant;
import Utils.HTTPJSONHelper;
import View.Dialog.Student.StudentAddDialog;
import View.Dialog.Student.StudentDeleteDialog;
import View.Dialog.Student.StudentEditDialog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ConnectException;
import java.util.HashMap;

/**
 * Created by devouty on 2015/11/13.
 */
public class StudentPanel {
    private JTable studentTable;
    private JButton studentBtnAdd;
    private JLabel studentLblStatus;
    StudentAddDialog studentAddDialog;
    StudentDeleteDialog studentDeleteDialog;
    StudentEditDialog studentEditDialog;
    private EditButtonRenderer studentTableBtnEdit;
    private EditButtonRenderer studentTableBtnDelete;
    StudentPanel(MainView mainView)
    {
        this.studentBtnAdd = mainView.getStudentBtnAdd();
        this.studentLblStatus = mainView.getStudentLblStatus();
        this.studentTable = mainView.getStudentTable();
        studentAddDialog = new StudentAddDialog();
        studentDeleteDialog = new StudentDeleteDialog();
        studentEditDialog = new StudentEditDialog();
        studentTableBtnEdit = new EditButtonRenderer("edit");
        studentTableBtnDelete = new EditButtonRenderer("delete");
        updateData();
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                studentLblStatus.setText("Editing:" + studentTable.getSelectedRow());
                if (studentTable.getSelectedColumn() == 0) {

                    int y = studentTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("studentId", data[y][2]);
                    map.put("studentPassword", data[y][3]);
                    map.put("sex", data[y][4]);
                    map.put("identityCard", data[y][5]);
                    map.put("bankCard", data[y][6]);
                    map.put("academyId", data[y][7]);
                    map.put("dataRow", studentTable.getSelectedRow());
                    studentEditDialog.show(StudentPanel.this.data, map);
                    updateData();
                }
                if (studentTable.getSelectedColumn() == 1) {
                    studentDeleteDialog.show((String) data[studentTable.getSelectedRow()][2], (String) data[studentTable.getSelectedRow()][7]);
                    updateData();
                }
                if (e.getClickCount() == 2) {
                    int y = studentTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("studentId", data[y][2]);
                    map.put("studentPassword", data[y][3]);
                    map.put("sex", data[y][4]);
                    map.put("identityCard", data[y][5]);
                    map.put("bankCard", data[y][6]);
                    map.put("academyId", data[y][7]);
                    map.put("dataRow", studentTable.getSelectedRow());
                    studentEditDialog.show(StudentPanel.this.data, map);
                    updateData();
                }
            }
        });

        studentBtnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                double newId = 0;
                for (int i = 0; i < data.length; i++) {
                    if (newId < Double.parseDouble((String) data[i][2])) {
                        newId = Double.parseDouble((String) data[i][2]);
                    }
                }
                newId++;
                map.put("newId", (int) newId);
                studentAddDialog.show(StudentPanel.this.data, map);
                updateData();
            }
        });
    }

    Thread thread;
    Object[][] data;
    DefaultTableModel model;

    private void updateData() {
        if (thread != null)
            thread.stop();
        thread = new Thread() {
            @Override
            public void run() {
                JSONObject jsonObject = null;
                try {
                    jsonObject = HTTPJSONHelper.get(Constant.PROSESS_URL + "student/studentlist");
                } catch (ConnectException e) {
                    studentLblStatus.setText("Connection is failed!Check your host.");
                }

                JSONArray jsonArray = (JSONArray) jsonObject.get("result");

                //new data for table

                /*
                   studentId            varchar(255) not null,
                   studentPassword      varchar(255) default "无",
                   sex                  varchar(255) default "无",
                   identityCard         varchar(255) default "无",
                   bankCard             varchar(255) default "无",
                   academyId		varchar(255) default "无",
                 */
                //insert data into data[][]
                int length = jsonArray.size();
                JSONObject obj;
                data = null;
                data = new Object[length][8];
                for (int i = 0; i < length; i++) {
                    obj = (JSONObject) jsonArray.get(i);
                    data[i][0] = "edit";
                    data[i][1] = "delete";
                    data[i][2] = obj.getString("studentId");
                    data[i][3] = obj.getString("studentPassword");
                    data[i][4] = obj.getString("sex");
                    data[i][5] = obj.getString("identityCard");
                    data[i][6] = obj.getString("bankCard");
                    data[i][7] = obj.getString("academyId");
                }

                Object[] names = {
                        "edit",
                        "delete",

                        "studentId",
                        "studentPassword",
                        "sex",
                        "identityCard",
                        "bankCard",
                        "academyId"
                };

                model = new DefaultTableModel(data, names) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                try {
                    studentTable.setModel(model);
                } catch (ArrayIndexOutOfBoundsException e) {
                    studentLblStatus.setText("");
                }
                studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                studentTable.getColumn("edit").setCellRenderer(studentTableBtnEdit);
                studentTable.getColumn("delete").setCellRenderer(studentTableBtnDelete);
                studentTable.setDoubleBuffered(false);
            }
        };
        try {
            thread.start();
        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }
}
