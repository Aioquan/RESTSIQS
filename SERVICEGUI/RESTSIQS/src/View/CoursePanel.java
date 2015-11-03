package View;

import Beans.EditButtonRenderer;
import Utils.Constant;
import Utils.HTTPJSONHelper;
import View.Dialog.CourseEditDialog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ConnectException;
import java.util.HashMap;

/**
 * Created by devouty on 2015/11/1.
 */
public class CoursePanel {
    private JPanel coursePanel;
    private JComboBox courseComboBox;
    private JLabel courseDate;
    private JButton courseButtonSearch;
    private JLabel courseStatus;
    private JButton courseButtonAdd;
    private DefaultTableModel model = null;
    private JTable courseTable;
    private EditButtonRenderer courseTableBtnEdit;
    private EditButtonRenderer courseTableBtnDelete;
    private Object[][] data;
    CourseEditDialog editDialog;
    JDialog deleteDialog;
    Thread thread;

    public Object[][] getData() {
        return data;
    }

    CoursePanel(MainView mainView) {
        this.courseButtonAdd = mainView.getCourseButtonAdd();
        this.courseButtonSearch = mainView.getCourseButtonSearch();
        this.courseDate = mainView.getCourseDate();
        this.coursePanel = mainView.getCoursePanel();
        this.courseComboBox = mainView.getCourseComboBox();
        this.courseStatus = mainView.getCourseStatus();
        this.courseTable = mainView.getCourseTable();
        editDialog = new CourseEditDialog();
        courseTableBtnEdit = new EditButtonRenderer("edit");
        courseTableBtnDelete = new EditButtonRenderer("delete");
        JPanel editPanel = new JPanel();

        updateData();
        courseTable.getTableHeader().setReorderingAllowed(false);

        courseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                courseStatus.setText("Editing:" + courseTable.getSelectedRow());
                if (courseTable.getSelectedColumn() == 0) {

                    int y = courseTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("credit", data[y][1]);
                    map.put("teacherId", data[y][2]);
                    map.put("studentId", data[y][3]);
                    map.put("courseName", data[y][4]);
                    map.put("sum", data[y][5]);

                    map.put("finalTest", data[y][6]);
                    map.put("dailyMark", data[y][7]);
                    map.put("test1", data[y][8]);
                    map.put("test2", data[y][9]);
                    map.put("test3", data[y][10]);

                    map.put("exercises1", data[y][11]);
                    map.put("exercises2", data[y][12]);
                    map.put("exercises3", data[y][13]);
                    map.put("exercises4", data[y][14]);
                    map.put("exercises5", data[y][15]);
                    map.put("dataRow", courseTable.getSelectedRow());
                    editDialog.show(CoursePanel.this.data, map);
                    updateData();
                }
                if (courseTable.getSelectedColumn() == 17) {

                }
                if (e.getClickCount() == 2) {

                }
            }
        });
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
                    courseStatus.setText("Connection is failed!Check your host.");
                }

//                System.out.println(jsonObject.toJSONString());
//                System.out.println(((JSONArray)jsonObject.get("result")).toJSONString());

                JSONArray jsonArray = (JSONArray) jsonObject.get("result");

                //new data for table


                //insert data into data[][]
                int length = jsonArray.size();
                JSONObject obj;
                data = null;
                data = new Object[length][18];
                for (int i = 0; i < length; i++) {
                    obj = (JSONObject) jsonArray.get(i);
                    data[i][0] = "edit";

                    data[i][1] = obj.getDouble("credit");
                    data[i][2] = obj.getString("teacherId");
                    data[i][3] = obj.getString("studentId");
                    data[i][4] = obj.getString("courseName");
                    data[i][5] = obj.getDouble("sum");

                    data[i][6] = obj.getDouble("finalTest");
                    data[i][7] = obj.getDouble("dailyMark");
                    data[i][8] = obj.getDouble("test1");
                    data[i][9] = obj.getDouble("test2");
                    data[i][10] = obj.getDouble("test3");

                    data[i][11] = obj.getDouble("exercises1");
                    data[i][12] = obj.getDouble("exercises2");
                    data[i][13] = obj.getDouble("exercises3");
                    data[i][14] = obj.getDouble("exercises4");
                    data[i][15] = obj.getDouble("exercises5");

                    data[i][16] = obj.getString("courseId");
                    data[i][17] = "delete";
                }

//                courseTable.getColumn(0);
                Object[] names = {
                        "edit",

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
                        "courseId",
                        "delete"};

                model = new DefaultTableModel(data, names);
                try {
                    courseTable.setModel(model);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
                courseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                courseTable.getColumn("edit").setCellRenderer(courseTableBtnEdit);
                courseTable.getColumn("delete").setCellRenderer(courseTableBtnDelete);
                courseTable.setDoubleBuffered(false);
//                courseTable.setCellSelectionEnabled(false);

            }
        };
        thread.start();

    }

}
