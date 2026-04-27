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
        String entYearStr = req.getParameter("f1");     // 入学年度
        String classNum   = req.getParameter("f2");     // クラス
        String subjectCd  = req.getParameter("f3");     // 科目
        String studentNo  = req.getParameter("studentNo"); // 学生番号（下段検索）

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

        // -----------------------------
        // 選択肢（入学年度・クラス・科目）
        // -----------------------------
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

        // -----------------------------
        // 検索判定
        // -----------------------------
        boolean isStudentSearch = (studentNo != null && !studentNo.trim().isEmpty());

        // 科目検索の「入力されているかどうか」
        boolean isSubjectInputted =
                (entYearStr != null && !"0".equals(entYearStr) && !entYearStr.isEmpty()) ||
                (classNum   != null && !"0".equals(classNum)   && !classNum.isEmpty()) ||
                (subjectCd  != null && !"0".equals(subjectCd)  && !subjectCd.isEmpty());

        // 科目検索の「3つ全部揃っているか」
        boolean isSubjectSearch =
                (entYearStr != null && !"0".equals(entYearStr) && !entYearStr.isEmpty()) &&
                (classNum   != null && !"0".equals(classNum)   && !classNum.isEmpty()) &&
                (subjectCd  != null && !"0".equals(subjectCd)  && !subjectCd.isEmpty());

        // -----------------------------
        // 科目検索：入力不備
        // -----------------------------
        if (isSubjectInputted && !isSubjectSearch) {
            req.setAttribute("error", "入学年度とクラスと科目が選択されていません");
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("studentNo", studentNo);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // -----------------------------
        // 学生検索：未入力エラー
        // -----------------------------
        boolean isStudentInputted = (studentNo != null && !studentNo.trim().isEmpty());

        if (!isStudentInputted && studentNo != null) {
            // 「学生番号欄に触ったが空」のケース
            req.setAttribute("error_student", "学生番号を入力してください");
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("studentNo", studentNo);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // -----------------------------
        // 初期表示
        // -----------------------------
        if (!isStudentSearch && !isSubjectSearch) {
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);
            req.setAttribute("studentNo", studentNo);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        TestDao testDao = new TestDao();

        // -----------------------------
        // 科目別検索（上段）
        // -----------------------------
        if (isSubjectSearch) {

            int entYear = Integer.parseInt(entYearStr);

            Subject subject = new Subject();
            subject.setCd(subjectCd);

            Subject full = subDao.get(subjectCd, school);
            if (full != null) subject.setName(full.getName());

            List<Test> list = testDao.filter(entYear, classNum, subject, 0, school);

            req.setAttribute("tests", list);
            req.setAttribute("subject", subject);
            req.setAttribute("f1", entYearStr);
            req.setAttribute("f2", classNum);
            req.setAttribute("f3", subjectCd);

            req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
            return;
        }

        // -----------------------------
        // 学生別検索（下段）
        // -----------------------------
        if (isStudentSearch) {

            List<Test> all = testDao.filter(0, "", null, 0, school);
            List<Test> filtered = new ArrayList<>();

            for (Test t : all) {
                if (t.getStudent() != null &&
                    studentNo.equals(t.getStudent().getNo())) {
                    filtered.add(t);
                }
            }

            req.setAttribute("tests", filtered);
            req.setAttribute("studentNo", studentNo);

            req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
            return;
        }

        // フォールバック
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
