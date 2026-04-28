package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import tool.Action;
import dao.TestListStudentDao;
import dao.StudentDao;
import bean.School;
import bean.Teacher;
import bean.Student;
import bean.TestListStudent;

import java.util.List;

public class TestListStudentExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // 画面パラメータ（学生番号）
        String studentNo = req.getParameter("studentNo");

        // セッションからログインユーザー（学校情報）を取得（ログインチェック用）
        HttpSession session = req.getSession(false);
        School school = null;
        if (session != null) {
            Object obj = session.getAttribute("user");
            if (obj instanceof Teacher) {
                school = ((Teacher) obj).getSchool();
            }
        }

        // 学生番号未入力チェック
        if (studentNo == null || studentNo.trim().isEmpty()) {
            req.setAttribute("error_student", "学生番号を入力してください");
            req.setAttribute("studentNo", studentNo);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // StudentDao#get(String) を使用して学生情報を取得（StudentDao に合わせる）
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(studentNo);

        if (student == null) {
            // 学生が存在しない場合は元画面にエラーを返す
            req.setAttribute("error_student", "学生情報が存在しませんでした");
            req.setAttribute("studentNo", studentNo);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            return;
        }

        // 成績一覧取得（TestListStudentDao#filter を使用）
        TestListStudentDao dao = new TestListStudentDao();
        List<TestListStudent> tests = dao.filter(student);

        // JSP に渡す属性
        req.setAttribute("student", student);
        req.setAttribute("tests", tests);
        req.setAttribute("studentNo", studentNo);

        // 表示 JSP へフォワード
        req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
    }
}
