package scoremanager.main;

import tool.Action;

import jakarta.servlet.http.*;

import bean.School;
import dao.SubjectDao;

public class SubjectCSVExecuteAction extends Action {

    @Override
    public void execute(
            HttpServletRequest request,
            HttpServletResponse response)
            throws Exception {

        // CSV取得
        Part csvPart = request.getPart("csv");

        // ログイン学校取得
        HttpSession session = request.getSession();

        School school =
            (School)session.getAttribute("school");

        SubjectDao dao = new SubjectDao();

        boolean result =
            dao.readInsertCSV(csvPart, school);

        response.sendRedirect("subject_CSV.jsp");
    }
}