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
        dao.delete(studentNo, subjectCd, schoolCd, no);

        // 削除後は学生別一覧に戻る
        res.sendRedirect("test_list_student.do?studentNo=" + studentNo);
    }
}
