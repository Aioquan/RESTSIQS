package View;

import Beans.EditButtonRenderer;
import Utils.Constant;
import Utils.HTTPJSONHelper;
import View.Dialog.Notice.NoticeAddDialog;
import View.Dialog.Notice.NoticeDeleteDialog;
import View.Dialog.Notice.NoticeEditDialog;
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
 * Created by devouty on 2015/11/4.
 */
public class NoticePanel {
    Thread thread;
    Object[][] data;
    DefaultTableModel model;
    private JButton noticeButtonAdd;
    private JTable noticeTable;
    private JLabel noticeStatus;
    private NoticeAddDialog noticeAddDialog;
    private NoticeEditDialog noticeEditDialog;
    private NoticeDeleteDialog noticeDeleteDialog;
    private EditButtonRenderer noticeTableBtnEdit;
    private EditButtonRenderer noticeTableBtnDelete;

    NoticePanel(MainView mainView) {
        this.noticeButtonAdd = mainView.getNoticeButtonAdd();
        this.noticeStatus = mainView.getNoticeStatus();
        this.noticeTable = mainView.getNoticeTable();
        noticeAddDialog = new NoticeAddDialog();
        noticeDeleteDialog = new NoticeDeleteDialog();
        noticeEditDialog = new NoticeEditDialog();
        noticeTableBtnEdit = new EditButtonRenderer("edit");
        noticeTableBtnDelete = new EditButtonRenderer("delete");
        updateData();

        noticeTable.getTableHeader().setReorderingAllowed(false);
        noticeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                noticeStatus.setText("Editing:" + noticeTable.getSelectedRow());
                if (noticeTable.getSelectedColumn() == 0) {
                    int y = noticeTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("noticeId", data[y][2]);
                    map.put("noticeTitle", data[y][3]);
                    map.put("noticeContext", data[y][4]);
                    map.put("noticeOperator", data[y][5]);
                    map.put("academyId", data[y][6]);
                    noticeEditDialog.show(NoticePanel.this.data, map);
                    updateData();
                }
                if (noticeTable.getSelectedColumn() == 1) {
                    int y = noticeTable.getSelectedRow();
                    noticeDeleteDialog.show((String) data[y][2], (String) data[y][3]);
                    updateData();
                }
                if (e.getClickCount() == 2) {
                    int y = noticeTable.getSelectedRow();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("noticeId", data[y][2]);
                    map.put("noticeTitle", data[y][3]);
                    map.put("noticeContext", data[y][4]);
                    map.put("noticeOperator", data[y][5]);
                    map.put("academyId", data[y][6]);
                    map.put("dataRow", noticeTable.getSelectedRow());
                    noticeEditDialog.show(NoticePanel.this.data, map);
                    updateData();
                }
            }
        });

        noticeButtonAdd.addActionListener(new ActionListener() {
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
                noticeAddDialog.show(NoticePanel.this.data, map);
                updateData();
            }
        });
    }

    private void updateData() {
        if (thread != null)
            thread.stop();
        thread = new Thread() {
            @Override
            public void run() {
                JSONObject jsonObject = null;
                try {
                    jsonObject = HTTPJSONHelper.get(Constant.PROSESS_URL + "notice/noticelist");
                } catch (ConnectException e) {
                    noticeStatus.setText("Connection is failed!Check your host.");
                }

                JSONArray jsonArray = (JSONArray) jsonObject.get("result");

                //new data for table


                //insert data into data[][]
                int length = jsonArray.size();
                JSONObject obj;
                data = null;
                data = new Object[length][7];
                for (int i = 0; i < length; i++) {
                    obj = (JSONObject) jsonArray.get(i);
                    data[i][0] = "edit";
                    data[i][1] = "delete";
                    data[i][2] = obj.getString("noticeId");
                    data[i][3] = obj.getString("noticeTitle");
                    data[i][4] = obj.getString("noticeContext");
                    data[i][5] = obj.getString("noticeOperator");
                    data[i][6] = obj.getString("academyId");
                }

//                courseTable.getColumn(0);
                Object[] names = {
                        "edit",
                        "delete",

                        "noticeId",
                        "noticeTitle",
                        "noticeContext",
                        "noticeOperator",
                        "academyId"
                };

                model = new DefaultTableModel(data, names) {
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                try {
                    noticeTable.setModel(model);
                } catch (ArrayIndexOutOfBoundsException e) {
                    noticeStatus.setText("");
                }
                noticeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                noticeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                noticeTable.getColumn("edit").setCellRenderer(noticeTableBtnEdit);
                noticeTable.getColumn("delete").setCellRenderer(noticeTableBtnDelete);
                noticeTable.setDoubleBuffered(false);
//                courseTable.setCellSelectionEnabled(false);

            }
        };
        try {
            thread.start();
        } catch (ArrayIndexOutOfBoundsException e) {

        }

    }
}
