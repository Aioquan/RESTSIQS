package View;

import View.Dialog.Student.StudentAddDialog;
import View.Dialog.Student.StudentDeleteDialog;
import View.Dialog.Student.StudentEditDialog;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by devouty on 2015/11/13.
 */
public class StudentPanel {
    private JTable studentTable;
    private JButton studentBtnAdd;
    private JLabel studentLblStatus;
    StudentAddDialog studentAddDialog;
    StudentDeleteDialog studentDeleteDialog;
    StudentEditDialog studentEditDialog;
    StudentPanel(MainView mainView)
    {
        this.studentBtnAdd = mainView.getStudentBtnAdd();
        this.studentLblStatus = mainView.getStudentLblStatus();
        this.studentTable = mainView.getStudentTable();
        studentAddDialog = new StudentAddDialog();
        studentDeleteDialog = new StudentDeleteDialog();
        studentEditDialog = new StudentEditDialog();

    }

    Thread thread;
    Object[][] data;
    DefaultTableModel model;
}
