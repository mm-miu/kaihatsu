package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Student;
import bean.School;
import bean.Teacher;
import dao.StudentDao;

public class StudentCreateCSVExecuteAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession();
        Teacher teacher=(Teacher)session.getAttribute("user");

        StudentDao dao = new StudentDao();
        School school = teacher.getSchool();

        // 全件検索
        String csvData = dao.createCSV(school, false);

        request.setAttribute("csvData", csvData);
        request.setAttribute("title", "学生");

        request.getRequestDispatcher("create_CSV_done.jsp")
            .forward(request, response);
   
    }
}