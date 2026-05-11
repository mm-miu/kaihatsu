package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Subject;
import bean.School;
import bean.Teacher;
import dao.SubjectDao;

public class SubjectCreateCSVAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        SubjectDao dao = new SubjectDao();
        School school = teacher.getSchool();

        // 全件検索
        String csvData = dao.createCSV(school, false);

        request.setAttribute("csvData", csvData);

        request.getRequestDispatcher("create_student_CSV_done.jsp")
            .forward(request, response);

    }
}
