package View;

import Beans.DeleteButtonRenderer;
import Beans.EditButtonRenderer;
import Utils.Constant;
import Utils.HTTPJSONGetter;
import View.Dialog.CourseEditDialog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by devouty on 2015/11/1.
 */
public class CoursePanel {
    private JPanel coursePanel;
    private JSpinner courseSpinner;
    private JLabel courseDate;
    private JButton courseButtonSearch;
    private JLabel courseStatus;
    private JButton courseButtonAdd;
    private DefaultTableModel model = null;
    private JTable courseTable;
    private EditButtonRenderer courseTableBtnEdit;
    private EditButtonRenderer courseTableBtnDelete;
    private Object[][] data;
    JDialog editDialog;
    JDialog deleteDialog;

    CoursePanel(MainView mainView) {
        this.courseButtonAdd = mainView.getCourseButtonAdd();
        this.courseButtonSearch = mainView.getCourseButtonSearch();
        this.courseDate = mainView.getCourseDate();
        this.coursePanel = mainView.getCoursePanel();
        this.courseSpinner = mainView.getCourseSpinner();
        this.courseStatus = mainView.getCourseStatus();
        this.courseTable = mainView.getCourseTable();
        editDialog = new CourseEditDialog();
        JPanel editPanel = new JPanel();
        new Thread() {
            @Override
            public void run() {
//                try {
//                    sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                JSONObject jsonObject = HTTPJSONGetter.get(Constant.PROSESS_URL + "course/courselist");
//                System.out.println(jsonObject.toJSONString());
//                System.out.println(((JSONArray)jsonObject.get("result")).toJSONString());

                JSONArray jsonArray = (JSONArray) jsonObject.get("result");

                //new data for table


                //insert data into data[][]
                int length = jsonArray.size();
                JSONObject obj;
                data = new Object[length][17];
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

                    data[i][16] = "delete";
                }

//                courseTable.getColumn(0);
                courseTableBtnEdit = new EditButtonRenderer("Edit");
                courseTableBtnDelete = new EditButtonRenderer("Delete");
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

                        "delete"};

                model = new DefaultTableModel(data, names);
                courseTable.setModel(model);
                courseTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                courseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                courseTable.getColumn("edit").setCellRenderer(new EditButtonRenderer());
                courseTable.getColumn("delete").setCellRenderer(new DeleteButtonRenderer());
                courseTable.setDoubleBuffered(false);
//                courseTable.setCellSelectionEnabled(false);
            }
        }.start();
        courseTable.getTableHeader().setReorderingAllowed(false);
        courseTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() == 0) {

                    e.getY();
                }
                if (e.getY() == 16) {

                }
                if (e.getClickCount() == 2) {

                }
            }
        });
    }
}
