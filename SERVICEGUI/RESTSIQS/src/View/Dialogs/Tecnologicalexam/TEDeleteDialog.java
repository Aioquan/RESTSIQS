package View.Dialogs.Tecnologicalexam;

import Utils.Constant;
import Utils.HTTPJSONHelper;

import javax.swing.*;
import java.awt.event.*;
import java.net.ConnectException;

public class TEDeleteDialog extends JDialog {
    String id, name;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel label;

    public TEDeleteDialog() {
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
        TEDeleteDialog dialog = new TEDeleteDialog();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void onOK() {
// add your code here
        try {
            HTTPJSONHelper.delete(Constant.TECNOLOGICALEXAM_URL + this.id);
        } catch (ConnectException e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }

    public void show(String id, String name) {
        this.id = id;
        this.name = name;
        this.pack();
        this.label.setText("Delete:" + name + "?");
        this.setLocation(230, 150);
        this.setVisible(true);
    }
}
