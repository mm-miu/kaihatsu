package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.Subject;
import dao.SubjectDao;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubjectUpdateExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        String cd="";// 入力された科目コード
        String name="";// 入力された科目名
        Subject subject=null;// 科目
        SubjectDao sDao=new SubjectDao();// 科目DAO
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        cd=request.getParameter("cd");
        name=request.getParameter("name");

        // ビジネスロジック
         // 変更中に別画面から対象の科目が削除された場合
        if (sDao.get(cd, teacher.getSchool())==null) {
            errors.put("cd", "科目が存在していません");
            request.setAttribute("errors", errors);
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_update.jsp")
                .forward(request, response);
            return;
        }

        subject=new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        boolean result=sDao.save(subject);

        // JSPへフォワード
        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);

    }
}
