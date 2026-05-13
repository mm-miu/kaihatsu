package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import dao.TeacherDao;
import bean.School;
import java.time.LocalDate;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherCreateExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        
        String authority =teacher.getAuthority(); //ユーザーの権限の取得
        String id="";
        String name="";
        String school="";
        Teacher te=null;
        TeacherDao tDao = new TeacherDao();
        LocalDate todaysDate=LocalDate.now();// LocalDateインスタンスを取得

        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        id=request.getParameter("id");
        name=request.getParameter("name");
        if ("1".equals(authority)){
            school=request.getParameter("school");
            System.out.println(school);
        }else{
            school=teacher.getSchool().getCd();
        }

        // ビジネスロジック
        if (id==null) {
            errors.put("id", "idを入力してください");
            request.setAttribute("errors", errors);
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("school", school);
            
            request.getRequestDispatcher("student_create.jsp")
                .forward(request, response);
            return;}
        
        // 数値に変換
        
        // 学校コード

        if (tDao.existsTeacherNo(school, id)) {
            errors.put("no", "学生番号が重複しています");
            request.setAttribute("errors", errors);
            request.setAttribute("id", id);
            request.setAttribute("name", name);
            request.setAttribute("school", school);
            request.getRequestDispatcher("student_create.jsp")
                .forward(request, response);
            return;
        }

        te=new Teacher();
        te.setId(id);
        te.setName(name);
        if ("1".equals(authority)){
            School sch= new School();
            sch.setCd(school);
            te.setSchool(sch);
            
        }else{
            te.setSchool(teacher.getSchool());
        }
        
        

        boolean result=tDao.save(te);

        // JSPへフォワード
        request.getRequestDispatcher("teacher_create_done.jsp").forward(request, response);

    }
}
