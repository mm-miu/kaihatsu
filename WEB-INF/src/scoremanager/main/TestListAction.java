package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;
import dao.TestListStudentDao;
import bean.TestListStudent;

import java.util.List;

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // パラメータ取得
        String studentNo = request.getParameter("studentNo");
        String schoolCd = (String) request.getSession().getAttribute("school_cd");

        // 初期表示（検索なし）
        if (studentNo == null || studentNo.isEmpty()) {
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        // DAO 呼び出し
        TestListStudentDao dao = new TestListStudentDao();
        List<TestListStudent> list = dao.findByStudent(studentNo, schoolCd);

        // JSP に渡す
        request.setAttribute("list", list);
        request.setAttribute("studentNo", studentNo);

        // 画面遷移
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}
