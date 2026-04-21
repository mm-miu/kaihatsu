package scoremanager;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;
import jakarta.servlet.http.*;

public class LoginAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession();

        String id=request.getParameter("id");
        String password=request.getParameter("password");
        TeacherDao dao=new TeacherDao();
        Teacher user=dao.login(id, password);

        if (user!=null) {
            session.setAttribute("user", user);
            response.sendRedirect("main/menu.jsp");
            return;
        }
        
        request.setAttribute("id", id);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
}
