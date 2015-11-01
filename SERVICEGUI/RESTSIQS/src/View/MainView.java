package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by devouty on 2015/10/28.
 */
public class MainView {
    public MainView() {
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screensize.getWidth();
        height = (int) screensize.getHeight();

        JFrame frame = new JFrame("MainView");
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
    }
    int width;
    int height;
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;
    private JPanel coursePanel;
    private JPanel noticePanel;
    private JPanel studentPanel;
    private JPanel teacherPanel;
    private JPanel technologicalExamPanel;
    private JPanel academyPanel;
    private JSpinner courseSpinner;
    private JLabel courseDate;
    private JButton courseButtonSearch;
    private JLabel courseStatus;
    private JButton courseButtonAdd;
    private JTable courseTable;

    //    private String processURL;
    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
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

    public JPanel getTeacherPanel() {
        return teacherPanel;
    }

    public JPanel getTechnologicalExamPanel() {
        return technologicalExamPanel;
    }

    public JPanel getAcademyPanel() {
        return academyPanel;
    }

    public JSpinner getCourseSpinner() {
        return courseSpinner;
    }

    public JLabel getCourseDate() {
        return courseDate;
    }

    public JButton getCourseButtonSearch() {
        return courseButtonSearch;
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
