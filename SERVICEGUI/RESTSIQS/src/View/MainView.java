package View;

import Utils.Constant;
import Utils.FitJTableHeaderUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by devouty on 2015/10/28.
 */
public class MainView {
    int width;
    int height;
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel coursePanel;
    private JPanel noticePanel;
    private JPanel studentPanel;
    private JPanel academyPanel;
    private JLabel courseStatus;
    private JButton courseButtonAdd;
    private JTable courseTable;
    private JButton noticeButtonAdd;
    private JTable noticeTable;
    private JLabel noticeStatus;
    private JTable studentTable;
    private JButton studentBtnAdd;
    private JLabel studentStatus;
    private JTree academyTree;
    private JTable teacherTable;
    private JLabel academyStatus;
    private JButton academyButtonAddAcademy;
    private JButton academyButtonAddTeacher;

    public MainView() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screensize.getWidth();
        height = (int) screensize.getHeight();
        tabbedPane1.setBackground(Color.GRAY);
        JFrame frame = new JFrame(Constant.APPLICATION_NAME);
        frame.setContentPane(this.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, (width / 4) * 3, (height / 4) * 3);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        new CoursePanel(MainView.this);
        new NoticePanel(MainView.this);
        new StudentPanel(MainView.this);
        new AcademyPanel(MainView.this);
        FitJTableHeaderUtil.fitTableColumns(courseTable);
        FitJTableHeaderUtil.fitTableColumns(noticeTable);
        FitJTableHeaderUtil.fitTableColumns(studentTable);
        FitJTableHeaderUtil.fitTableColumns(teacherTable);

    }

    public JButton getAcademyButtonAddAcademy() {
        return academyButtonAddAcademy;
    }

    public JButton getAcademyButtonAddTeacher() {
        return academyButtonAddTeacher;
    }

    public JLabel getAcademyStatus() {
        return academyStatus;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JButton getNoticeButtonAdd() {
        return noticeButtonAdd;
    }

    public JTable getNoticeTable() {
        return noticeTable;
    }

    public JLabel getNoticeStatus() {
        return noticeStatus;
    }

    public JTable getStudentTable() {
        return studentTable;
    }

    public JButton getStudentBtnAdd() {
        return studentBtnAdd;
    }

    public JLabel getStudentStatus() {
        return studentStatus;
    }

    //    private String processURL;
    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public JTree getAcademyTree() {
        return academyTree;
    }

    public JTable getTeacherTable() {
        return teacherTable;
    }

    public JPanel getCoursePanel() {
        return coursePanel;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JPanel getNoticePanel() {
        return noticePanel;
    }

    public JPanel getStudentPanel() {
        return studentPanel;
    }

    public JPanel getAcademyPanel() {
        return academyPanel;
    }

    public JLabel getCourseStatus() {
        return courseStatus;
    }

    public JButton getCourseButtonAdd() {
        return courseButtonAdd;
    }

    public JTable getCourseTable() {
        return courseTable;
    }


}
