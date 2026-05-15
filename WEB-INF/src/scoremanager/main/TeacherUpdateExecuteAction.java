package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;

import bean.School;
import dao.TeacherDao;
import dao.ClassNumDao;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherUpdateExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");
        String id=(String)request.getParameter("C_Id");
        

        School sch=null;
        String name="";// 入力された名前
        // 入力されたID
        String password="";// 入力されたパスワード
        String school="";//入力された学校コード
        String inauthority="";//入力された権限
        String authority="";//権限
        Teacher te=null;// 先生
        String f1 =null;
        String f2 =null;
        TeacherDao tDao=new TeacherDao();// 学生DAO
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        

        password=request.getParameter("password");
        name=request.getParameter("name");
        school=request.getParameter("school_cd");
        inauthority = request.getParameter("authority");
        if (null==school){f1="0";}
        else{if ("0".equals(school)){f1="0";}
        else{ f1="1"; }
    }
    if (null==inauthority){f2="0";}
        else{if ("0".equals(inauthority)){f2="0";}
        else{ f2="1"; }
    }
    
        

        
        if (name==null){
            name=tDao.get(id).getName();
        }

        switch (f1) {
            case "1" : {
                sch = new School();
                sch.setCd(school); 
                
            
        }
        default:{
            
            sch=tDao.get(id).getSchool();
        }
        }

        switch (f2) {
            case "1" : {
                break;
            
        }
        default:{
            
            inauthority=tDao.get(id).getAuthority();
        }
        }

        
           
        
        if (password==null){
            password=tDao.get(id).getPassword();
        }

        // 数値に変換
        
        // 学校コード
        

        te=new Teacher();
        te.setId(id);
        te.setPassword(password);
        te.setName(name);
        te.setAuthority(inauthority);
        te.setSchool(sch);
        

        boolean result=tDao.update(te);

        // JSPへフォワード
        request.getRequestDispatcher("teacher_update_done.jsp").forward(request, response);

    }
}
