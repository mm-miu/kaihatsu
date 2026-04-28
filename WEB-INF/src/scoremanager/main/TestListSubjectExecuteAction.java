package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import bean.Subject;
import bean.School;
import bean.Teacher;
import bean.TestListSubject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 画面パラメータ
        String entYearStr = req.getParameter("f1");
        String classNum   = req.getParameter("f2");
        String subjectCd  = req.getParameter("f3");

        // セッションからログインユーザーを取得
        HttpSession session = req.getSession(false);
        School school = null;
        if (session != null) {
            Object obj = session.getAttribute("user");
            if (obj instanceof Teacher) {
                Teacher teacher = (Teacher) obj;
                school = teacher.getSchool();
            }
        }

        // 選択肢（test_list.jsp と同じものを用意）
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        ClassNumDao cnDao = new ClassNumDao();
        List<String> classList = cnDao.filter(school);

        SubjectDao subDao = new SubjectDao();
        List<Subject> subjectList = subDao.filter(school);

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("subject_set", subjectList);

        // 入力チェック（3つ全部必須）
        boolean missing =
            entYearStr == null || entYearStr.isEmpty() || "0".equals(entYearStr)
         || classNum   == null || classNum.isEmpty()   || "0".equals(classNum)
         || subjectCd  == null || subjectCd.isEmpty()  || "0".equals(subjectCd);

        if (missing) {
            req.setAttribute("error", "入学年度とクラスと科目が選択されていません");
            // 検索欄を表示する test_list.jsp に戻す（上部に検索欄を表示する仕様）
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        int entYear;
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            req.setAttribute("error", "入学年度の形式が不正です");
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // 科目情報取得（表示用）
        Subject subject = subDao.get(subjectCd, school);
        if (subject == null) {
            subject = new Subject();
            subject.setCd(subjectCd);
            subject.setName(subjectCd);
        }

        // 検索実行（TestListSubjectDao を使用）
        TestListSubjectDao dao = new TestListSubjectDao();
        List<TestListSubject> list = dao.filter(entYear, classNum, subject, school);

        // JSP に渡す属性
        req.setAttribute("tests", list);
        req.setAttribute("subject", subject);
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", subjectCd);

        // 検索欄（上）＋結果（下）を表示する JSP へ
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}
