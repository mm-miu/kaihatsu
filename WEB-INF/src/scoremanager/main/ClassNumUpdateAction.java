package scoremanager.main;

import tool.Action;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import bean.Teacher;
import bean.ClassNum;
import dao.SchoolDao;
import dao.ClassNumDao;
import jakarta.servlet.http.*;

public class ClassNumUpdateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        ClassNumDao cNumDao=new ClassNumDao();// クラスDAO
        SchoolDao scDao=new SchoolDao();// 学校DAO

        // データ取得
        String school_cd=request.getParameter("school_cd");
        String cd=request.getParameter("cd");

        // レスポンス値をセット
            // 科目コードと学校コードから科目を取得
            ClassNum cNum=cNumDao.get(cd, scDao.get(school_cd));
            request.setAttribute("num", cNum.getClass_num());

        // JSPへフォワード
        request.getRequestDispatcher("classNum_update.jsp").forward(request, response);

    }
}
