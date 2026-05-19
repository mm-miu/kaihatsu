package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.ClassNum;
import dao.ClassNumDao;
import dao.StudentDao;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassNumUpdateExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        String newNum="";// 入力されたクラス
        String num=""; // 元のクラス
        ClassNum classNum=null;// クラス
        ClassNumDao cNumDao=new ClassNumDao();// クラスDAO
        StudentDao sDao=new StudentDao();// 学生DAO
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        newNum=request.getParameter("newNum");
        num=request.getParameter("num");

        // ビジネスロジック
        if (newNum.length()> 3) {
            errors.put("num_count", "クラス番号は3文字以下で入力してください");
            request.setAttribute("errors", errors);
            request.setAttribute("num", newNum);
            request.getRequestDispatcher("classNum_update.jsp")
                .forward(request, response);
            return;
        }
        if (cNumDao.get(newNum, teacher.getSchool())!=null) {
            errors.put("num", "クラス番号が重複しています");
            request.setAttribute("errors", errors);
            request.setAttribute("num", newNum);
            request.getRequestDispatcher("classNum_update.jsp")
                .forward(request, response);
            return;
        }

        classNum=new ClassNum();
        classNum.setClass_num(num);
        classNum.setSchool(teacher.getSchool());

        boolean result = cNumDao.update(classNum, newNum);
        boolean result2 = sDao.cNumUpdate(num, newNum);

        if (result && result2) {

            request.getRequestDispatcher(
                "classNum_update_done.jsp"
            ).forward(request, response);
            return;

        } else {

            errors.put("update", "更新に失敗しました");

            request.setAttribute("errors", errors);

            request.getRequestDispatcher(
                "classNum_update.jsp"
            ).forward(request, response);
            return;
        }

    }
}
