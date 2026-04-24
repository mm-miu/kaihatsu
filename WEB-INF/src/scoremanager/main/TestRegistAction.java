package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import bean.Test;
import bean.Subject;
import bean.School;
import bean.Teacher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        //画面パラメータ
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2"); // クラス
        String subjectCd = req.getParameter("f3"); // 科目(cd)
        String noStr = req.getParameter("f4"); // 回数 (no)

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

        // 入学年度一覧を作成
        LocalDate todaysDate=LocalDate.now();// LocalDateインスタンスを取得
        int year=todaysDate.getYear();// 現在の年を取得
        List<Integer> entYearSet=new ArrayList<>();
        for (int i=year-10; i<year+1; i++) {
            entYearSet.add(i);
        }

        //クラス一覧を作成
        ClassNumDao cnDao = new ClassNumDao();
        List<String> classList = cnDao.filter(school);

        //科目一覧を作成
        SubjectDao subDao = new SubjectDao();
        List<Subject> subjectList = subDao.filter(school);

        // テスト回数のリストを作成
        List<Integer> countSet=new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            countSet.add(i);
        }

        // JSP に渡す属性
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("subject_set", subjectList);
        req.setAttribute("count_set", countSet);


        //検索は4つすべて指定されている場合のみ実行
        boolean allSpecified = (entYearStr != null && !entYearStr.isEmpty())
                            && (classNum != null && !classNum.isEmpty())
                            && (subjectCd != null && !subjectCd.isEmpty())
                            && (noStr != null && !noStr.isEmpty());

        //初期表示または一部入力の場合は JSP に戻す
        if (!allSpecified) {
            if ((entYearStr != null || classNum != null || subjectCd != null || noStr != null) &&
                !(entYearStr == null && classNum == null && subjectCd == null && noStr == null)) {
                req.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
            }
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("f4", noStr);
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        int entYear;
        int no;
        try {
            entYear = Integer.parseInt(entYearStr);
            no = Integer.parseInt(noStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "入学年度とクラスと科目と回数を選択してください");
            req.getRequestDispatcher("test_regist.jsp").forward(req, res);
            return;
        }

        Subject subject = new Subject();
        subject.setCd(subjectCd);

        TestDao dao = new TestDao();
        List<Test> list = dao.filter(entYear, classNum, subject, no, school);

        //JSP に渡す属性
        req.setAttribute("list", list);
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);
        req.setAttribute("f4", noStr);
        req.setAttribute("subjectCd", subjectCd);

        //成績入力画面へ
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
