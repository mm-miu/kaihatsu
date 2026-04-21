package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.Subject;
import dao.SubjectDao;
import java.util.List;

public class SubjectListAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        SubjectDao sDao=new SubjectDao();// 学生DAO

        // DBからデータ取得
        // ログインユーザーの学校コードをもとに科目の一覧を取得
        List<Subject> list=sDao.filter(teacher.getSchool());

        // レスポンス値をセット
        request.setAttribute("subjects", list);

        // JSPへフォワード
        request.getRequestDispatcher("subject_list.jsp").forward(request, response);

    }
}
