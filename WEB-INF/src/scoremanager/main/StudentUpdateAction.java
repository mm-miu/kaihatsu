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

public class StudentUpdateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        StudentDao sDao=new StudentDao();// 学生DAO
        ClassNumDao cNumDAO=new ClassNumDao();// クラス番号Daoを初期化
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        String no=request.getParameter("no");
        Student student=sDao.get(no);

        // DBからデータ取得
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<String> list=cNumDAO.filter(teacher.getSchool());

        // レスポンス値をセット
        // リクエストにデータをセット
        request.setAttribute("ent_year", student.getEntYear());
        request.setAttribute("no", no);
        request.setAttribute("name", student.getName());
        request.setAttribute("class_num", student.getClassNum());
        request.setAttribute("is_attend", student.isAttend());
        request.setAttribute("class_num_set", list);

        // JSPへフォワード
        request.getRequestDispatcher("student_update.jsp").forward(request, response);

    }
}
