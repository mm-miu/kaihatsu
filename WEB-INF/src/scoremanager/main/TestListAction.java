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

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 画面パラメータ
        String entYearStr = req.getParameter("f1"); // 入学年度
        String classNum = req.getParameter("f2");   // クラス
        String subjectCd = req.getParameter("f3");  // 科目(cd)
        String studentNo = req.getParameter("studentNo"); // 学生番号（下段検索）

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

        // --- 選択肢準備（入学年度・クラス・科目）
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        ClassNumDao cnDao = new ClassNumDao();
        List<String> classList = cnDao.filter(school);

        SubjectDao subDao = new SubjectDao();
        List<Subject> subjectList = subDao.filter(school);

        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_num_set", classList);
        req.setAttribute("subject_set", subjectList);

        // --- 判定：どちらの検索か
        boolean isStudentSearch = (studentNo != null && !studentNo.trim().isEmpty());
        boolean isSubjectSearch = (entYearStr != null && !entYearStr.isEmpty() && !"0".equals(entYearStr))
                               && (classNum != null && !classNum.isEmpty() && !"0".equals(classNum))
                               && (subjectCd != null && !subjectCd.isEmpty() && !"0".equals(subjectCd));

        // 初期表示（どちらも未指定）は検索フォームを表示するだけ
        if (!isStudentSearch && !isSubjectSearch) {
            // 保持用
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("studentNo", studentNo);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        TestDao testDao = new TestDao();

        if (isSubjectSearch) {
            // 科目別一覧検索（entYear, classNum, subjectCd）
            int entYear;
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                req.setAttribute("error", "入学年度の形式が不正です");
                req.getRequestDispatcher("test_list.jsp").forward(req, res);
                return;
            }

            Subject subject = new Subject();
            subject.setCd(subjectCd);
            // subject.name を表示したい場合は DAO から取得してセット
            Subject sFull = subDao.get(subjectCd, school);
            if (sFull != null) subject.setName(sFull.getName());

            // num=0 で回数指定なし（全回）を取得
            List<Test> list = testDao.filter(entYear, classNum, subject, 0, school);

            req.setAttribute("tests", list);
            req.setAttribute("subject", subject);
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);

            // 科目別一覧 JSP へ
            req.getRequestDispatcher("TestListSubject.jsp").forward(req, res);
            return;
        }

        if (isStudentSearch) {
            // 学生別一覧検索（studentNo）
            // 全件取得（学校内）を取り、studentNo でフィルタする（TestDao に専用メソッドが無い場合の実装）
            List<Test> all = testDao.filter(0, "", null, 0, school);
            List<Test> filtered = new ArrayList<>();
            if (all != null) {
                for (Test t : all) {
                    if (t != null && t.getStudent() != null && studentNo.equals(t.getStudent().getNo())) {
                        filtered.add(t);
                    }
                }
            }

            req.setAttribute("tests", filtered);
            req.setAttribute("studentNo", studentNo);

            // 学生別一覧 JSP へ
            req.getRequestDispatcher("TestListStudent.jsp").forward(req, res);
            return;
        }

        
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
