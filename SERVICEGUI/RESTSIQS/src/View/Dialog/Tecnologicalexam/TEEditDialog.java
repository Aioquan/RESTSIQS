package View.Dialog.Tecnologicalexam;

import Beans.HTTPEntities.Technologicalexam;
import Beans.NumLimitListener;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class TEEditDialog extends JDialog {
    HashMap<String, Object> map;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfName;
    private JTextField tfDate;
    private JTextField tfScore;
    private JLabel lblTId;
    private JLabel lblSId;

    public TEEditDialog() {
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
        TEEditDialog dialog = new TEEditDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        if (!hasEmpty()) {
            updateTE();
            dispose();
        } else {
            TEEditDialog.this.setTitle(Constant.ERROR_HAS_EMPTY);
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(HashMap<String, Object> map) {

        this.map = map;

        lblSId.setText("studentId:" + map.get("studentId"));
        lblTId.setText("Tid:" + map.get("Tid"));
        tfDate.setText(map.get("tDate") + "");
        tfName.setText(map.get("tName") + "");
        tfScore.setText(map.get("tSorce") + "");
        this.setLocation(150, 250);
        this.pack();
        this.setVisible(true);

        KeyListener numLimitListener = new NumLimitListener();
        tfScore.addKeyListener(numLimitListener);
    }

    private void updateTE() {
        try {
            String tId = (String) map.get("Tid");
            String studentId =  map.get("studentId")+"";
            Technologicalexam technologicalexam = new Technologicalexam();
            technologicalexam.setStudentId(studentId);
            technologicalexam.setTid(tId);
            technologicalexam.setTdate(tfDate.getText());
            technologicalexam.setTname(tfName.getText());
            technologicalexam.setTsorce(Double.parseDouble(tfScore.getText()));
//            String pojo2json = JSONObject.toJSONString(technologicalexam);
            HTTPJSONHelper.put(Constant.TECNOLOGICALEXAM_URL + "technologicalexam/", technologicalexam);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
    }

    public boolean hasEmpty() {
        boolean flag = false;

        if (tfScore.getText().isEmpty())
            flag = true;
        if (tfName.getText().isEmpty())
            flag = true;
        if (tfDate.getText().isEmpty())
            flag = true;

        return flag;
    }
}
