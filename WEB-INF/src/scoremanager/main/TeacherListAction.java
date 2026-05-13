package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import dao.TeacherDao;
import java.time.LocalDate;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeacherListAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");
        String authority =teacher.getAuthority();
        String f1 =null;
        String f2 =null;
        String id = teacher.getId();
        f1 = request.getParameter("f1");
        f2 = request.getParameter("f2");
        TeacherDao TDao= new TeacherDao();
        
        
        
        

        // リクエストパラメーターの取得
        

        // 在学フラグが送信されていた場合
        
        // ビジネスロジック
        

        // リストを初期化
        List<Teacher> Mylist=new ArrayList<>();
        List<Teacher> AllList = new ArrayList<>();

        // 10年前から1年後まで年をリストに追加
        
        // DBからデータ取得
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        Mylist=TDao.myfilter(teacher.getSchool(),teacher.getId());
        AllList=TDao.Sort(f1,f2,teacher);
            
        // レスポンス値をセット
        // リクエストに入学年度をセット
        request.setAttribute("authority",authority);
        request.setAttribute("MyList",Mylist);
        request.setAttribute("AllList",AllList);
        

        // JSPへフォワード
        request.getRequestDispatcher("teacher_list.jsp").forward(request, response);

    }
}
