package scoremanager.main;


import tool.Action;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.*;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション

        SubjectDao sjdao= new SubjectDao();
        Teacher teacher=(Teacher)session.getAttribute("user");
        String subjectCd=request.getParameter("cd");
        Subject sbj = sjdao.get(subjectCd,teacher.getSchool());

        session.setAttribute("subject",sbj);
        // JSPへフォワード
        request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
    }

}