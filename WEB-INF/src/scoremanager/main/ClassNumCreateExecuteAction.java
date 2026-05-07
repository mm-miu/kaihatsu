package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.ClassNum;
import dao.ClassNumDao;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassNumCreateExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        String num="";// 入力されたクラス番号
        ClassNum classNum=null;// クラス
        ClassNumDao cnDao=new ClassNumDao();// クラスDAO
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        num=request.getParameter("num");

        // ビジネスロジック
        if (num.length()> 3) {
            errors.put("num_count", "クラス番号は3文字以下で入力してください");
            request.setAttribute("errors", errors);
            request.setAttribute("num", num);
            request.getRequestDispatcher("classNum_create.jsp")
                .forward(request, response);
            return;
        }
        if (cnDao.get(num, teacher.getSchool())!=null) {
            errors.put("num", "クラス番号が重複しています");
            request.setAttribute("errors", errors);
            request.setAttribute("num", num);
            request.getRequestDispatcher("classNum_create.jsp")
                .forward(request, response);
            return;
        }

        classNum=new ClassNum();
        classNum.setSchool(teacher.getSchool());
        classNum.setClass_num(num);

        boolean result=cnDao.save(classNum);

        // JSPへフォワード
        request.getRequestDispatcher("classNum_create_done.jsp").forward(request, response);

    }
}
