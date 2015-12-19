package View;

import Beans.EditButtonRenderer;
import Utils.Constant;
import Utils.HTTPJSONHelper;
import View.Dialog.Course.CourseAddDialog;
import View.Dialog.Course.CourseDeleteDialog;
import View.Dialog.Course.CourseEditDialog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ConnectException;
import java.util.HashMap;

/**
 * Created by devouty on 2015/11/1.
 */
public class CoursePanel {
    CourseEditDialog editDialog;
    CourseDeleteDialog deleteDialog;
    CourseAddDialog addDialog;
    Thread thread;
    //    private JPanel coursePanel;
//    private JComboBox courseComboBox;
//    private JLabel courseDate;
//    private JButton courseButtonSearch;
    private JLabel courseStatus;
    private JButton courseButtonAdd;
    private DefaultTableModel model = null;
    private JTable courseTable;
    private EditButtonRenderer courseTableBtnEdit;
    private EditButtonRenderer courseTableBtnDelete;
    private Object[][] data;

    CoursePanel(MainView mainView) {
        this.courseButtonAdd = mainView.getCourseButtonAdd();
//        this.courseButtonSearch = mainView.getCourseButtonSearch();
//        this.courseDate = mainView.getCourseDate();
//        this.coursePanel = mainView.getCoursePanel();
//        this.courseComboBox = mainView.getCourseComboBox();
        this.courseStatus = mainView.getCourseStatus();
        this.courseTable = mainView.getCourseTable();

        editDialog = new CourseEditDialog();
        deleteDialog = new CourseDeleteDialog();
        addDialog = new CourseAddDialog();

        courseTableBtnEdit = new EditButtonRenderer("edit");
        courseTableBtnDelete = new EditButtonRenderer("delete");
//        JPanel editPanel = new JPanel();
//        courseTable.getTableHeader().setFont(new Font("宋体", Font.BOLD, 18));
//        courseTable.setFont(new Font("宋体", 0, 20));
//        courseTable.setRowHeight(23);
        TableColumnModel tableColumnModel = courseTable.getColumnModel();

        updateData();

        courseTable.getTableHeader().setReorderingAllowed(false);
        courseTable.getTableHeader().setBackground(Color.GREEN);
        courseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                courseStatus.setText("Editing:" + courseTable.getSelectedRow());
                if (courseTable.getSelectedColumn() == 0) {

                    int y = courseTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("credit", data[y][2]);
                    map.put("teacherId", data[y][3]);
                    map.put("studentId", data[y][4]);
                    map.put("courseName", data[y][5]);
                    map.put("sum", data[y][6]);

                    map.put("finalTest", data[y][7]);
                    map.put("dailyMark", data[y][8]);
                    map.put("test1", data[y][9]);
                    map.put("test2", data[y][10]);
                    map.put("test3", data[y][11]);

                    map.put("exercises1", data[y][12]);
                    map.put("exercises2", data[y][13]);
                    map.put("exercises3", data[y][14]);
                    map.put("exercises4", data[y][15]);
                    map.put("exercises5", data[y][16]);
                    map.put("dataRow", courseTable.getSelectedRow());
                    editDialog.show(CoursePanel.this.data, map);
                    updateData();
                }
                if (courseTable.getSelectedColumn() == 1) {
                    deleteDialog.show((String) data[courseTable.getSelectedRow()][5], (String) data[courseTable.getSelectedRow()][17]);
                    updateData();
                }
//                System.out.println(e.getClickCount());
                if (e.getClickCount() == 2) {
                    int y = courseTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("credit", data[y][2]);
                    map.put("teacherId", data[y][3]);
                    map.put("studentId", data[y][4]);
                    map.put("courseName", data[y][5]);
                    map.put("sum", data[y][6]);

                    map.put("finalTest", data[y][7]);
                    map.put("dailyMark", data[y][8]);
                    map.put("test1", data[y][9]);
                    map.put("test2", data[y][10]);
                    map.put("test3", data[y][11]);

                    map.put("exercises1", data[y][12]);
                    map.put("exercises2", data[y][13]);
                    map.put("exercises3", data[y][14]);
                    map.put("exercises4", data[y][15]);
                    map.put("exercises5", data[y][16]);
                    map.put("dataRow", courseTable.getSelectedRow());
                    editDialog.show(CoursePanel.this.data, map);
                    updateData();
                }
            }
        });

        courseButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                double newId = 0;
                for (int i = 0; i < data.length; i++) {
                    if (newId < Double.parseDouble((String) data[i][17])) {
                        newId = Double.parseDouble((String) data[i][17]);
                    }
                }
                newId++;
                map.put("newId", (int) newId);
                addDialog.show(CoursePanel.this.data, map);
                updateData();
            }
        });

    }

    public Object[][] getData() {
        return data;
    }

    private void updateData() {
        if (thread != null)
            thread.stop();
        thread = new Thread() {
            @Override
            public void run() {
//                try {
//                    sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                JSONObject jsonObject = null;
                try {
                    jsonObject = HTTPJSONHelper.get(Constant.PROSESS_URL + "course/courselist");
                } catch (ConnectException e) {
                    courseStatus.setText(Constant.ERROR_CONNECTION_FAILED);
                }

//                System.out.println(jsonObject.toJSONString());
//                System.out.println(((JSONArray)jsonObject.get("result")).toJSONString());

                JSONArray jsonArray = (JSONArray) jsonObject.get("result");

                //insert data into data[][]
                int length = jsonArray.size();
                JSONObject obj;
                data = null;
                data = new Object[length][18];
                for (int i = 0; i < length; i++) {
                    obj = (JSONObject) jsonArray.get(i);
                    data[i][0] = "edit";

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
                    data[i][1] = "delete";
                }

//                courseTable.getColumn(0);
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

                model = new DefaultTableModel(data, names) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                try {
                    courseTable.setModel(model);
                } catch (ArrayIndexOutOfBoundsException e) {
                    courseStatus.setText("");
                }

                courseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                courseTable.getColumn("edit").setCellRenderer(courseTableBtnEdit);

                courseTable.getColumn("delete").setCellRenderer(courseTableBtnDelete);

                courseTable.setDoubleBuffered(false);
//                courseTable.setCellSelectionEnabled(false);

            }
        };
        try {
            thread.start();
        } catch (ArrayIndexOutOfBoundsException e) {
        }
    }
}
