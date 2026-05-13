package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Test;
import bean.School;
import bean.Teacher;
import dao.TestDao;

public class TestCreateCSVAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        TestDao dao = new TestDao();
        School school = teacher.getSchool();

        // 全件検索
        String csvData = dao.createCSV(school, false);

        request.setAttribute("csvData", csvData);
        request.setAttribute("title", "成績");

        request.getRequestDispatcher("create_CSV_done.jsp")
            .forward(request, response);

    }
}
