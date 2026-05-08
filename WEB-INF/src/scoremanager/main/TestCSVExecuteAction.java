package scoremanager.main;

import tool.Action;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;

import bean.Teacher;
import bean.School;
import dao.TestDao;

public class TestCSVExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // CSV取得
        Part csvPart = request.getPart("csv");

        // セッション取得
        HttpSession session = request.getSession();

        // ログインユーザ（Teacher）取得
        Teacher teacher = (Teacher) session.getAttribute("user");

        // Teacher から School を取得
        School school = teacher.getSchool();

        // DAO 呼び出し
        TestDao dao = new TestDao();

        boolean result = dao.readInsertCSV(csvPart, school);

        response.sendRedirect("test_regist_done.jsp");
    }
}
