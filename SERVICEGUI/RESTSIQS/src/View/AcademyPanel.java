package View;

import Beans.EditButtonRenderer;
import Beans.HTTPEntities.Academy;
import Utils.Constant;
import Utils.FitJTableHeaderUtil;
import Utils.HTTPJSONHelper;
import View.Dialogs.Academy.AcademyAddDialog;
import View.Dialogs.Academy.AcademyDeleteDialog;
import View.Dialogs.Academy.AcademyEditDialog;
import View.Dialogs.Teacher.TeacherAddDialog;
import View.Dialogs.Teacher.TeacherDeleteDialog;
import View.Dialogs.Teacher.TeacherEditDialog;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    TeacherEditDialog teacherEditDialog;
    TeacherDeleteDialog teacherDeleteDialog;
    TeacherAddDialog teacherAddDialog;
    AcademyAddDialog academyAddDialog;
    AcademyDeleteDialog academyDeleteDialog;
    AcademyEditDialog academyEditDialog;
    JButton academyButtonAddAcademy, academyButtonAddTeacher;
    JPopupMenu popMenu;
    JMenuItem delItem, editItem;
    private EditButtonRenderer teacherTableBtnEdit, teacherTableBtnDelete;

    AcademyPanel(MainView mainView) {
        this.academyButtonAddAcademy = mainView.getAcademyButtonAddAcademy();
        this.academyButtonAddTeacher = mainView.getAcademyButtonAddTeacher();
        this.academyTree = mainView.getAcademyTree();
        this.teacherTable = mainView.getTeacherTable();
        this.academyStatus = mainView.getAcademyStatus();
        teacherEditDialog = new TeacherEditDialog();
        teacherDeleteDialog = new TeacherDeleteDialog();
        teacherAddDialog = new TeacherAddDialog();
        academyAddDialog = new AcademyAddDialog();
        academyDeleteDialog = new AcademyDeleteDialog();
        academyEditDialog = new AcademyEditDialog();
        teacherTableBtnEdit = new EditButtonRenderer();
        teacherTableBtnDelete = new EditButtonRenderer();
        updateTree();

//        this.academyTree.addTreeSelectionListener(new TreeSelectionListener() {
//            public void valueChanged(TreeSelectionEvent e) {
//                AcademyPanel.this.treeSelectionId = ((Academy) ((DefaultMutableTreeNode) (e.getPath().getPathComponent(1))).getUserObject()).getAcademyId();
//                updateTable(treeSelectionId);
//            }
//        });
        this.academyTree.setSelectionRow(0);
        teacherTable.getTableHeader().setReorderingAllowed(false);
//        teacherTable.getTableHeader().setBackground(Color.GREEN);
        teacherTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                academyStatus.setText("Editing:" + teacherTable.getSelectedRow());

                if (teacherTable.getSelectedColumn() == 0) {

                    int y = teacherTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("teacherId", data[y][2]);
                    map.put("teacherName", data[y][3]);
                    map.put("teacherDepartment", data[y][4]);
                    map.put("teacherStatus", data[y][5]);
                    map.put("dataRow", teacherTable.getSelectedRow());
                    map.put("academyId", AcademyPanel.this.treeSelectionId);
                    teacherEditDialog.show(AcademyPanel.this.data, map);
                }
                if (teacherTable.getSelectedColumn() == 1) {
                    teacherDeleteDialog.show((String) data[teacherTable.getSelectedRow()][2], (String) data[teacherTable.getSelectedRow()][3]);
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
                    map.put("academyId", AcademyPanel.this.treeSelectionId);
                    teacherEditDialog.show(AcademyPanel.this.data, map);
                }
                updateTable(AcademyPanel.this.treeSelectionId);
            }
        });
        academyButtonAddAcademy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                academyAddDialog.show();
                updateTree();
            }
        });
        academyButtonAddTeacher.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("academyId", AcademyPanel.this.treeSelectionId);
                teacherAddDialog.show(map);
                updateTable(treeSelectionId);
            }
        });

        //JTree menu
        academyTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                super.mousePressed(e);
                if (e.getButton() == 1) {
                    treeSelectionId = ((Academy) ((DefaultMutableTreeNode) academyTree.getSelectionPath().getPathComponent(1)).getUserObject()).getAcademyId();
                    updateTable(treeSelectionId);
                } else if (e.getButton() == 3) {
                    popMenu.show(academyTree, e.getX(), e.getY());
                    updateTable(treeSelectionId);
                }
            }
        });
        ActionListener treeMenuListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == delItem) {
                    Academy academy = (Academy) ((DefaultMutableTreeNode) academyTree.getSelectionPath().getPathComponent(1)).getUserObject();
                    academyDeleteDialog.show(academy.getAcademyId(), academy.getAcademyName());
                } else if (e.getSource() == editItem) {
                    Academy academy = (Academy) ((DefaultMutableTreeNode) academyTree.getSelectionPath().getPathComponent(1)).getUserObject();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("academyId", academy.getAcademyId());
                    map.put("academyName", academy.getAcademyName());
                    map.put("academyAddress", academy.getAcademyAddress());
                    academyEditDialog.show(map);
                }
                updateTree();
            }
        };

        popMenu = new JPopupMenu();

        delItem = new JMenuItem(Constant.TREE_BUTTUN_DELETE);
        delItem.addActionListener(treeMenuListener);
        editItem = new JMenuItem(Constant.TREE_BUTTUN_EDIT);
        editItem.addActionListener(treeMenuListener);
        popMenu.add(editItem);
        popMenu.add(delItem);
        treeSelectionId = academyTree.getSelectionPath().getPathComponent(1).toString();
        updateTable(treeSelectionId);
    }

    private void updateTree() {
        JSONObject jsonObject = null;
        try {
            jsonObject = HTTPJSONHelper.get(Constant.ACADEMY_URL + "academylist");
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
//        System.out.println(teacherArray);
        JSONObject jsonObject = null;
        try {
            jsonObject = HTTPJSONHelper.get(Constant.TEACHER_URL + "teacherlist");
//            System.out.println(jsonObject);
        } catch (ConnectException e) {
            this.academyStatus.setText(Constant.ERROR_CONNECTION_FAILED);
        }
        teacherArray = (JSONArray) jsonObject.get("result");

        int length = teacherArray.size();
        JSONObject obj;
        Object[] names = {
                Constant.TABLE_BUTTUN_EDIT,
                Constant.TABLE_BUTTUN_DELETE,
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
        int j = 0;
        data = new Object[l][6];
        for (int i = 0; i < length; i++) {
            obj = (JSONObject) teacherArray.get(i);
            if (obj.get("academyId").equals(academyId)) {
                data[j][0] = Constant.TABLE_BUTTUN_EDIT;
                data[j][1] = Constant.TABLE_BUTTUN_DELETE;
                data[j][2] = obj.get("teacherId");
                data[j][3] = obj.get("teacherName");
                data[j][4] = obj.get("teacherStatus");
                data[j][5] = obj.get("teacherDepartment");
                j++;
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
            academyStatus.setText(Constant.STATUS_NULL);
        }
        teacherTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        teacherTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        teacherTable.getColumn(Constant.TABLE_BUTTUN_EDIT).setCellRenderer(teacherTableBtnEdit);

        teacherTable.getColumn(Constant.TABLE_BUTTUN_DELETE).setCellRenderer(teacherTableBtnDelete);

        teacherTable.setDoubleBuffered(false);

        FitJTableHeaderUtil.fitTableColumns(teacherTable);
    }
}
