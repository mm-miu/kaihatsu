package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;
import dao.TestDao;
import bean.Test;
import bean.Subject;
import bean.School;
import bean.Teacher;

import java.util.List;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        //画面パラメータ
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目(cd)
        String f4 = req.getParameter("f4"); // 回数 (no)

        //セッションからログインユーザーを取得
        HttpSession session = req.getSession(false);
        School school = null;
        if (session != null) {
            Object obj = session.getAttribute("user");
            if (obj instanceof Teacher) {
                Teacher teacher = (Teacher) obj;
                school = teacher.getSchool();
            }
        }

        //未ログインまたはセッション切れはログイン画面へリダイレクト
        if (school == null) {
            res.sendRedirect("login.jsp");
            return;
        }

        //検索は4つすべて指定されている場合のみ実行
        boolean allSpecified = (f1 != null && !f1.isEmpty())
                            && (f2 != null && !f2.isEmpty())
                            && (f3 != null && !f3.isEmpty())
                            && (f4 != null && !f4.isEmpty());

        //初期表示または一部入力の場合は JSP に戻す
        if (!allSpecified) {
            if ((f1 != null || f2 != null || f3 != null || f4 != null) &&
                !(f1 == null && f2 == null && f3 == null && f4 == null)) {
                req.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
            }
            req.setAttribute("f1", f1);
            req.setAttribute("f2", f2);
            req.setAttribute("f3", f3);
            req.setAttribute("f4", f4);
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        int entYear;
        int no;
        try {
            entYear = Integer.parseInt(f1);
            no = Integer.parseInt(f4);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        Subject subject = new Subject();
        subject.setCd(f3);

        TestDao dao = new TestDao();
        List<Test> list = dao.filter(entYear, f2, subject, no, school);

        //JSP に渡す属性
        req.setAttribute("list", list);
        req.setAttribute("f1", f1);
        req.setAttribute("f2", f2);
        req.setAttribute("f3", f3);
        req.setAttribute("f4", f4);
        req.setAttribute("subjectCd", f3);

        //成績入力画面へ
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
