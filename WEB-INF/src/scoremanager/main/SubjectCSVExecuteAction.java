package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;

import bean.School;
import bean.Teacher;
import dao.SubjectDao;

public class SubjectCSVExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        Part csvPart = request.getPart("csv");

        SubjectDao dao = new SubjectDao();
        boolean result = dao.readInsertCSV(csvPart, school);

        response.sendRedirect("subject_create_done.jsp");
    }
}
