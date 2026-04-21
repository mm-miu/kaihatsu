package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.Student;
import dao.StudentDao;
import dao.ClassNumDao;
import java.time.LocalDate;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentCreateAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {

        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");

        String entYearStr="";// 入力された入学年度
        String no="";// 入力された学生番号
        String name="";// 入力された氏名
        String classNum="";//入力されたクラス番号
        int entYear=0;// 入学年度
        LocalDate todaysDate=LocalDate.now();// LocalDateインスタンスを取得
        int year=todaysDate.getYear();// 現在の年を取得
        StudentDao sDao=new StudentDao();// 学生DAO
        ClassNumDao cNumDAO=new ClassNumDao();// クラス番号Daoを初期化
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        entYearStr=request.getParameter("ent_year");
        no=request.getParameter("no");
        name=request.getParameter("name");
        classNum=request.getParameter("class_num");

        // リストを初期化
        List<Integer> entYearSet=new ArrayList<>();

        // 10年前から1年後まで年をリストに追加
        for (int i=year-10; i<year+1; i++) {
            entYearSet.add(i);
        }

        // DBからデータ取得
        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
        List<String> list=cNumDAO.filter(teacher.getSchool());

        // レスポンス値をセット
        // リクエストにデータをセット
        request.setAttribute("class_num_set", list);
        request.setAttribute("ent_year_set", entYearSet);

        // JSPへフォワード
        request.getRequestDispatcher("student_create.jsp").forward(request, response);

    }
}
