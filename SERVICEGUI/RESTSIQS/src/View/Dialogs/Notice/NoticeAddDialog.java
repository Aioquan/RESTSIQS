package View.Dialogs.Notice;

import Beans.HTTPEntities.Notice;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class NoticeAddDialog extends JDialog {
    Object[][] data;
    HashMap<String, Object> map;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel btnPanel;
    private JPanel contextPanel;
    private JTextField tfTitle;
    private JTextField tfOperator;
    private JTextField tfAcademyId;
    private JTextArea tfContext;

    public NoticeAddDialog() {
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

    public static void main(String[] args) {
        NoticeAddDialog dialog = new NoticeAddDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        if (hasEmpty()) {
            NoticeAddDialog.this.setTitle(Constant.ERROR_HAS_EMPTY);
        } else {
            addNotice();
            tfTitle.setText("");
            tfOperator.setText("");
            tfContext.setText("");
            tfAcademyId.setText("");
        }
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(Object[][] data, HashMap<String, Object> map) {
        this.data = data;
        this.map = map;
        tfContext.setLineWrap(true);
        this.pack();
        this.setBounds(130, 150, 650, 450);
        this.setVisible(true);
    }

    private void addNotice() {
        try {
            Notice notice = new Notice();
            notice.setNoticeTitle(tfTitle.getText());
            notice.setNoticeOperator(tfOperator.getText());
            notice.setAcademyId(tfAcademyId.getText());
            notice.setNoticeContext(tfContext.getText());
            notice.setNoticeId(this.map.get("newId") + "");
//            String pojo2json = JSONObject.toJSONString(notice);
            HTTPJSONHelper.post(Constant.NOTICE_URL + "notice/", notice);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }

    private boolean hasEmpty() {
        boolean flag = false;
        if (tfAcademyId.getText().isEmpty())
            flag = true;
        if (tfContext.getText().isEmpty())
            flag = true;
        if (tfOperator.getText().isEmpty())
            flag = true;
        if (tfTitle.getText().isEmpty())
            flag = true;

        return flag;
    }
}
