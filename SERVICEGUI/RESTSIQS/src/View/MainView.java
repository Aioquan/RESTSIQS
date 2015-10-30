package View;

import Utils.Constant;
import Utils.HTTPJSONGetter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by devouty on 2015/10/28.
 */
public class MainView {
    private JTabbedPane tabbedPane1;
    private JPanel mainPanel;

    private JSpinner courseSpinner;
    private JButton courseButtonSearch;
    private JTable courseTable;
    private JButton courseButtonAdd;
    private JLabel courseDate;
    private JLabel courseStatus;
    private JPanel coursePanel;

    private JPanel noticePanel;

    private JPanel studentPanel;

    private JPanel teacherPanel;

    private JPanel technologicalExamPanel;

    private JPanel academyPanel;

    private DefaultTableModel model = null;
    private String processURL;
    public MainView() {

        processURL = "http://"+ Constant.IP+":8080/RESTSIQS/";
        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screensize.getWidth();
        int height = (int) screensize.getHeight();

        JFrame frame = new JFrame("MainView");
        frame.setContentPane(new MainView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(100, 100, (width / 4) * 3, (height / 4) * 3);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
/*
   courseId             varchar(255) not null,
   credit               double default -1,
   teacherId		varchar(255) default "无",
   studentId		varchar(255) default "无",
   courseName           varchar(255) default "无",
   courseTime           varchar(255) default "无",
   courseDate           varchar(255) default "无",
   test1                double default -1,
   test2                double default -1,
   test3                double default -1,
   exercises1           double default -1,
   exercises2           double default -1,
   exercises3           double default -1,
   exercises4           double default -1,
   exercises5           double default -1,
   finalTest            double default -1,
   dailyMark            double default -1,
   sum                  double default -1,
 */
        String[] names = {"credit",
                "teacherId",
                "studentId",
                "courseName",
                "sum",

                "finalTest",
                "dailyMark",
                "test1",
                "test2",
                "test3",

                "exercises1",
                "exercises2",
                "exercises3",
                "exercises4",
                "exercises5"};

        Object[][] data = new Object[][]{};

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(250);
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                JSONObject jsonObject = HTTPJSONGetter.get(processURL+"course/courselist");
                System.out.println(jsonObject.toJSONString());
                System.out.println(((JSONArray)jsonObject.get("result")).toJSONString());
            }
        }.start();
        model = new DefaultTableModel(data,names);
        courseTable.setModel(model);

        courseTable.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent evt) {

            }
        });
    }


}
