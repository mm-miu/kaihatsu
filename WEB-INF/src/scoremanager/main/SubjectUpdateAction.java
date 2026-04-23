package scoremanager.main;

import tool.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bean.Teacher;
import bean.Subject;
import dao.SchoolDao;
import dao.SubjectDao;
import jakarta.servlet.http.*;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        SubjectDao subDao=new SubjectDao();// 科目DAO
        SchoolDao scDao=new SchoolDao();// 学校DAO

        // データ取得
        String school_cd=request.getParameter("school_cd");
        String cd=request.getParameter("cd");

        // レスポンス値をセット
            // 科目コードと学校コードから科目を取得
            Subject subject=subDao.get(cd, scDao.get(school_cd));
            request.setAttribute("subject", subject);

        // JSPへフォワード
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);

    }
}
