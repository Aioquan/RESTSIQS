package View;

import Beans.HTTPEntities.Academy;
import Utils.Constant;
import Utils.HTTPJSONHelper;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.net.ConnectException;

/**
 * Created by devouty on 2015/12/7.
 */
public class AcademyPanel {
    JTree academyTree;
    JTable teacherTable;
    JLabel academyStatus;
    DefaultTreeModel treeModel;
    AcademyPanel(MainView mainView) {
        this.academyTree = mainView.getAcademyTree();
        this.teacherTable = mainView.getTeacherTable();
        this.academyStatus = mainView.getAcademyStatus();
        updateTree();
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
            academy.setAcademyId(obj.get("academyId")+"");
            academy.setAcademyAddress(obj.get("academyAddress")+"");
            academy.setAcademyName(obj.get("academyName")+"");
            top.add(new DefaultMutableTreeNode(academy));
        }
        treeModel = new DefaultTreeModel(top);
        this.academyTree.setModel(treeModel);
    }
}
