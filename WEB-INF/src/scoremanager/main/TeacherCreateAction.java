package scoremanager.main;

import tool.Action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import dao.SchoolDao;
import bean.Teacher;
import bean.School;

import dao.TeacherDao;
import jakarta.servlet.http.*;

public class TeacherCreateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");
        String authority =teacher.getAuthority(); //ユーザーの権限の取得
        String id="";
        String password="";
        String name="";
        LocalDate todaysDate=LocalDate.now();// LocalDateインスタンスを取得
        
        TeacherDao tDao=new TeacherDao();// 先生DAO
        SchoolDao sDao=new SchoolDao();
        Map<String, String> errors=new HashMap<>();// エラーメッセージ
        List<School> sList= new ArrayList<>();
        sList=sDao.getAll();
        
        

        request.setAttribute("schoolList", sList);
        request.setAttribute("authority", authority);


        // JSPへフォワード
        request.getRequestDispatcher("teacher_create.jsp").forward(request, response);

    }
}
