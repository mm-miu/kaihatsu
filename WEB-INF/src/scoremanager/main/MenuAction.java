package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;

public class MenuAction extends Action {
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession();
        request.setAttribute("user", session.getAttribute("user"));
        request.getRequestDispatcher("menu.jsp").forward(request, response);
   
    }
}
