package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import dao.TestDao;

public class DeleteTestAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String studentNo = req.getParameter("student_no");
        String subjectCd = req.getParameter("subject_cd");
        String schoolCd = req.getParameter("school_cd");
        int no = Integer.parseInt(req.getParameter("no"));

        TestDao dao = new TestDao();
        boolean deleted = dao.delete(studentNo, subjectCd, schoolCd, no);

        // JSP に削除結果と学生番号を渡してフォワード
        req.setAttribute("deleted", deleted);
        req.setAttribute("studentNo", studentNo);

        req.getRequestDispatcher("test_delete.jsp").forward(req, res);
    }
}
