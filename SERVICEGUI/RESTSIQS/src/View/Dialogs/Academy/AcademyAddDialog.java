package View.Dialogs.Academy;

import Beans.HTTPEntities.Academy;
import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;

public class AcademyAddDialog extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField tfAcademyId;
    private JTextField tfAcademyName;
    private JTextField tfAcademyAddress;

    public AcademyAddDialog() {
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
        AcademyAddDialog dialog = new AcademyAddDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        if (!hasEmpty()) {
            if (saveAcademy()) {
                dispose();
            } else {
                AcademyAddDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
            }
        } else {
            AcademyAddDialog.this.setTitle(Constant.ERROR_HAS_EMPTY);
        }
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show() {
        this.pack();
        this.setLocation(330, 150);
        this.setVisible(true);
    }

    private boolean saveAcademy() {
        Academy academy = new Academy();
        academy.setAcademyId(tfAcademyId.getText());
        academy.setAcademyName(tfAcademyName.getText());
        academy.setAcademyAddress(tfAcademyAddress.getText());
        tfAcademyId.setText("");
        tfAcademyName.setText("");
        tfAcademyAddress.setText("");
        try {
            HTTPJSONHelper.post(Constant.ACADEMY_URL + "academy/", academy);
            return true;
        } catch (ConnectException e) {
            e.printStackTrace();
            AcademyAddDialog.this.setTitle(Constant.ERROR_CONNECTION_FAILED);
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
