package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.Student;
import dao.StudentDao;
import dao.ClassNumDao;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentUpdateExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        String entYearStr="";// 入力された入学年度
        String no="";// 入力された学生番号
        String name="";// 入力された氏名
        String classNum="";//入力されたクラス番号
        Boolean isAttend;
        int entYear=0;// 入学年度
        Student student=null;// 学生
        StudentDao sDao=new StudentDao();// 学生DAO
        ClassNumDao cNumDao=new ClassNumDao();
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        entYearStr=request.getParameter("ent_year");
        no=request.getParameter("no");
        name=request.getParameter("name");
        classNum=request.getParameter("class_num");
        isAttend = request.getParameter("is_attend") != null;

        // 数値に変換
        entYear=Integer.parseInt(entYearStr);
        // 学校コード
        String schoolCd = teacher.getSchool().getCd();

        student=new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(isAttend);
        student.setSchool(teacher.getSchool());

        boolean result=sDao.save(student);

        // JSPへフォワード
        request.getRequestDispatcher("student_update_done.jsp").forward(request, response);

    }
}
