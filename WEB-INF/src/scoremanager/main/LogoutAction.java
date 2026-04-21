package scoremanager.main;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;
import java.util.List;
import jakarta.servlet.http.*;

public class LogoutAction extends Action {

    public void execute (
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession();

        if (session.getAttribute("user")!=null) {
            session.removeAttribute("user");
            request.getRequestDispatcher("logout.jsp").forward(request, response);
            return;
        }

        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
