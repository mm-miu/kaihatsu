package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.School;
import bean.Teacher;
import dao.StudentDao;

public class StudentCSVExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();
        
        Part csvPart = request.getPart("csv");

        StudentDao sDao = new StudentDao();
        boolean result = sDao.readInsertCSV(csvPart, school);

        response.sendRedirect("student_create_done.jsp");
    }
}