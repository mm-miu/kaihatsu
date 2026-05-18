package scoremanager.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import javax.naming.InitialContext;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import dao.TeacherDao;
import bean.School;
import dao.SchoolDao;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherUpdateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");
        
        //権限
        String authority =teacher.getAuthority();

        TeacherDao tDao=new TeacherDao();// 先生DAO
        SchoolDao sDao=new SchoolDao();// 学校DAO
        // // クラス番号Daoを初期化
        Map<String, String> errors=new HashMap<>();// エラーメッセージ
        List<School> sList= new ArrayList<>();

        String id=request.getParameter("id");
        
        
        Teacher te=tDao.get(id);
        // DBからデータ取得
        // ログインユーザーの権限をもとに学校コードの一覧を取得
        sList=sDao.getAll();
        // リクエストにデータをセット
        request.setAttribute("C_Id", id);
        request.setAttribute("name", te.getName());
        request.setAttribute("password",te.getPassword());
        request.setAttribute("school", te.getSchool().getCd());
        request.setAttribute("schoolList", sList);
        request.setAttribute("authority", authority);
        
        

        // JSPへフォワード
        request.getRequestDispatcher("teacher_update.jsp").forward(request, response);

    }
}
