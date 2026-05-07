package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import dao.StudentDao;

public class StudentCSVExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Part csvPart = request.getPart("csv");

        StudentDao sDao = new StudentDao();
        boolean result = sDao.readInsertCSV(csvPart);

        response.sendRedirect("student_create_done.jsp");
    }
}