package Utils;

/**
 * Created by devouty on 2015/10/23.
 */
public class Constant {

    //connection part
    public static final String IP = "115.28.167.136";
    public static final String PROSESS_URL = "http://" + Constant.IP + ":8080/RESTSIQS/";
    public static final String COURSE_URL = PROSESS_URL+"course/";
    public static final String NOTICE_URL = PROSESS_URL+"notice/";
    public static final String STUDENT_URL = PROSESS_URL+"student/";
    public static final String TEACHER_URL = PROSESS_URL+"teacher/";
    public static final String TECNOLOGICALEXAM_URL = PROSESS_URL+"technologicalexam/";
    public static final String ACADEMY_URL = PROSESS_URL+"academy/";

    //language part
    public static final String ERROR_CONNECTION_FAILED = "ConnectException,please check your connection";
    public static final String ERROR_HAS_EMPTY = "ConnectException,please check your connection";
    public static final String ERROR_NOT_LEGAL = "Input is not legal";

    public static final String STATUS_NULL = "Status:";

    public static final String APPLICATION_NAME = "RESTSIQS";


    public static final String TABLE_BUTTUN_EDIT = "edit";
    public static final String TABLE_BUTTUN_DELETE = "delete";
    public static final String TREE_BUTTUN_EDIT = "edit";
    public static final String TREE_BUTTUN_DELETE = "delete";
}
