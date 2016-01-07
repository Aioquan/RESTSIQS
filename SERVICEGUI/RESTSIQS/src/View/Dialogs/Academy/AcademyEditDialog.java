package View.Dialogs.Academy;

import Beans.HTTPEntities.Academy;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.util.HashMap;

public class AcademyEditDialog extends JDialog {

    HashMap<String, Object> map;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfAcademyId;
    private JTextField tfAcademyName;
    private JTextField tfAcademyAddress;

    public AcademyEditDialog() {
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
        AcademyEditDialog dialog = new AcademyEditDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        if (!hasEmpty()) {
            if (updateAcademy()) {
                dispose();
            } else {
                AcademyEditDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
            }
        } else {
            AcademyEditDialog.this.setTitle(Constant.ERROR_HAS_EMPTY);
        }
    }

    public void show(HashMap<String, Object> map) {

        this.map = map;
        this.pack();
        this.tfAcademyId.setText((String) this.map.get("academyId"));
        this.tfAcademyName.setText((String) this.map.get("academyName"));
        this.tfAcademyAddress.setText((String) this.map.get("academyAddress"));
        this.setLocation(330, 150);
        this.setVisible(true);
    }

    private boolean updateAcademy() {
        Academy academy = new Academy();
        academy.setAcademyId(tfAcademyId.getText());
        academy.setAcademyName(tfAcademyName.getText());
        academy.setAcademyAddress(tfAcademyAddress.getText());

        try {
            HTTPJSONHelper.put(Constant.ACADEMY_URL + "academy/", academy);
            return true;
        } catch (ConnectException e) {
            e.printStackTrace();
            AcademyEditDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
        }
        return false;
    }

    public boolean hasEmpty() {
        boolean flag = false;

        if (tfAcademyAddress.getText().isEmpty())
            flag = true;
        if (tfAcademyName.getText().isEmpty())
            flag = true;
        if (tfAcademyId.getText().isEmpty())
            flag = true;

        return flag;
    }
}
