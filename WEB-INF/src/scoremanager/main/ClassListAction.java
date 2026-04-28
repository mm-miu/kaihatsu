package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.ClassNum;
import dao.ClassNumDao;
import java.util.List;

public class ClassListAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        ClassNumDao cnDao=new ClassNumDao();// クラスDAO

        // DBからデータ取得
        // ログインユーザーの学校コードをもとにクラスの一覧を取得
        List<String> list=cnDao.filter(teacher.getSchool());

        // レスポンス値をセット
        request.setAttribute("classes", list);
        request.setAttribute("school", teacher.getSchool());

        // JSPへフォワード
        request.getRequestDispatcher("class_list.jsp").forward(request, response);

    }
}
