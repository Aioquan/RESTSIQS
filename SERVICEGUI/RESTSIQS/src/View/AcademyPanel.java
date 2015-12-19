package View;

import Beans.EditButtonRenderer;
import Beans.HTTPEntities.Academy;
import Utils.Constant;
import Utils.HTTPJSONHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.ConnectException;
import java.util.HashMap;

/**
 * Created by devouty on 2015/12/7.
 */
public class AcademyPanel {
    JTree academyTree;
    JTable teacherTable;
    JLabel academyStatus;
    DefaultTreeModel treeModel;
    JSONArray teacherArray;
    DefaultTableModel model;
    Object[][] data;
    String treeSelectionId;
    private EditButtonRenderer teacherTableBtnEdit, teacherTableBtnDelete;


    AcademyPanel(MainView mainView) {
        this.academyTree = mainView.getAcademyTree();
        this.teacherTable = mainView.getTeacherTable();
        this.academyStatus = mainView.getAcademyStatus();
        updateTree();
        JSONObject jsonObject = null;
        try {
            jsonObject = HTTPJSONHelper.get(Constant.TEACHER_URL + "teacherlist");
//            System.out.println(jsonObject);
        } catch (ConnectException e) {
            this.academyStatus.setText(Constant.ERROR_CONNECTION_FAILED);
        }
        teacherArray = (JSONArray) jsonObject.get("result");

        updateTree();
        this.academyTree.addTreeSelectionListener(new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                AcademyPanel.this.treeSelectionId = ((Academy) ((DefaultMutableTreeNode) (e.getPath().getPathComponent(1))).getUserObject()).getAcademyId();
                updateTable(treeSelectionId);
            }
        });
        this.academyTree.setSelectionRow(0);

        teacherTable.getTableHeader().setReorderingAllowed(false);
        teacherTable.getTableHeader().setBackground(Color.GREEN);
        teacherTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                academyStatus.setText("Editing:" + teacherTable.getSelectedRow());
                /*
                create table Teacher
                (
                   teacherId            varchar(255) not null,
                   teacherName          varchar(255) default "无",
                   teacherDepartment    varchar(255) default "无",
                   teacherStatus        varchar(255) default "无",
                   academyId		varchar(255) default "无",
                   primary key (teacherId)
                )engine=InnoDB default charset=utf8;
                 */
                if (teacherTable.getSelectedColumn() == 0) {

                    int y = teacherTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("teacherId", data[y][2]);
                    map.put("teacherName", data[y][3]);
                    map.put("teacherDepartment", data[y][4]);
                    map.put("teacherStatus", data[y][5]);
                    map.put("dataRow", teacherTable.getSelectedRow());
                    editDialog.show(AcademyPanel.this.data, map);

                }
                if (teacherTable.getSelectedColumn() == 1) {
                    deleteDialog.show((String) data[courseTable.getSelectedRow()][5], (String) data[courseTable.getSelectedRow()][17]);
                }
//                System.out.println(e.getClickCount());
                if (e.getClickCount() == 2) {
                    int y = teacherTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("teacherId", data[y][2]);
                    map.put("teacherName", data[y][3]);
                    map.put("teacherDepartment", data[y][4]);
                    map.put("teacherStatus", data[y][5]);
                    map.put("dataRow", teacherTable.getSelectedRow());
                    editDialog.show(CoursePanel.this.data, map);
                }
                updateTable(AcademyPanel.this.treeSelectionId);
            }
        });
    }

    private void updateTree() {
        JSONObject jsonObject = null;
        try {
            jsonObject = HTTPJSONHelper.get(Constant.ACADEMY_URL + "academylist/");
//            System.out.println(jsonObject);
        } catch (ConnectException e) {
            this.academyStatus.setText(Constant.ERROR_CONNECTION_FAILED);
        }
        JSONArray jsonArray = (JSONArray) jsonObject.get("result");
        int length = jsonArray.size();
        JSONObject obj;
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("AcademyList");

        for (int i = 0; i < length; i++) {
            obj = (JSONObject) jsonArray.get(i);
            Academy academy = new Academy();
            academy.setAcademyId(obj.get("academyId") + "");
            academy.setAcademyAddress(obj.get("academyAddress") + "");
            academy.setAcademyName(obj.get("academyName") + "");
            top.add(new DefaultMutableTreeNode(academy));
        }
        treeModel = new DefaultTreeModel(top);
        this.academyTree.setModel(treeModel);
        this.academyTree.setRootVisible(false);

    }

    private void updateTable(String academyId) {
        System.out.println(teacherArray);
        int length = teacherArray.size();
        JSONObject obj;
        Object[] names = {
                "edit",
                "delete",
                "teacherId",
                "teacherName",
                "teacherDepartment",
                "teacherStatus"
        };

        int l = 0;
        for (int i = 0; i < length; i++) {
            obj = (JSONObject) teacherArray.get(i);
            if (obj.get("academyId").equals(academyId)) {
                l++;
            }
        }
        data = new Object[l][6];
        for (int i = 0; i < length; i++) {
            obj = (JSONObject) teacherArray.get(i);
            if (obj.get("academyId").equals(academyId)) {
                l++;

                data[i][0] = "edit";
                data[i][1] = "delete";
                data[i][2] = obj.get("teacherId");
                data[i][2] = obj.get("teacherName");
                data[i][2] = obj.get("teacherStatus");
                data[i][2] = obj.get("teacherDepartment");
            }
        }
        model = new DefaultTableModel(data, names) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            teacherTable.setModel(model);
        } catch (ArrayIndexOutOfBoundsException e) {
            academyStatus.setText("");
        }
        teacherTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        teacherTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teacherTable.getColumn("edit").setCellRenderer(teacherTableBtnEdit);

        teacherTable.getColumn("delete").setCellRenderer(teacherTableBtnDelete);

        teacherTable.setDoubleBuffered(false);
    }
}
