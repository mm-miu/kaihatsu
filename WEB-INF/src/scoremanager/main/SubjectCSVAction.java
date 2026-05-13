package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;

public class SubjectCSVAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession();
        request.setAttribute("user", session.getAttribute("user"));
        request.getRequestDispatcher("subject_CSV.jsp").forward(request, response);
   
    }
}
