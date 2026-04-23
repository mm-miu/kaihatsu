package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;
import dao.TestDao;
import bean.Test;

import java.util.List;

public class TestListAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //パラメーターを取得
        String studentNo = request.getParameter("studentNo");

        //初期表示(検索なしの状態)
        if(studentNo == null || studentNo.isEmpty()) {
            request.getRequestDispatcher("test_list.jsp").forward(request, response);
            return;
        }

        //DAO呼び出し
        TestDao dao = new TestDao();
        List<Test> list = dao.findByStudent(studentNo);

        //JSPに渡す
        request.setAttribute("list", list);
        request.setAttribute("studentNo", studentNo);

        //画面遷移
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}
