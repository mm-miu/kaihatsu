package scoremanager.main;

import tool.Action;
import bean.Teacher;
import jakarta.servlet.http.*;

public class ClassCreateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        // JSPへフォワード
        request.getRequestDispatcher("classNum_create.jsp").forward(request, response);

    }
}
