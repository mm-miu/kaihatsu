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

public class StudentCreateExecuteAction extends Action {

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
        Student student=null;// 学生
        LocalDate todaysDate=LocalDate.now();// LocalDateインスタンスを取得
        int year=todaysDate.getYear();// 現在の年を取得
        StudentDao sDao=new StudentDao();// 学生DAO
        ClassNumDao cNumDao=new ClassNumDao();
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        entYearStr=request.getParameter("ent_year");
        no=request.getParameter("no");
        name=request.getParameter("name");
        classNum=request.getParameter("class_num");

        // ビジネスロジック
        if (entYearStr==null || entYearStr.equals("0")) {
            errors.put("ent_year", "入学年度を選択してください");
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            List<String> list=cNumDao.filter(teacher.getSchool());
            request.setAttribute("class_num_set", list);
            List<Integer> entYearSet=new ArrayList<>();
            for (int i=year-10; i<year+1; i++) {
                entYearSet.add(i);
            }
            request.setAttribute("ent_year_set", entYearSet);
            request.getRequestDispatcher("student_create.jsp")
                .forward(request, response);
            return;
        }
        // 数値に変換
        entYear=Integer.parseInt(entYearStr);
        // 学校コード
        String schoolCd = teacher.getSchool().getCd();

        if (sDao.existsStudentNo(schoolCd, no)) {
            errors.put("no", "学生番号が重複しています");
            request.setAttribute("errors", errors);
            request.setAttribute("no", no);
            request.setAttribute("ent_year", entYearStr);
            request.setAttribute("name", name);
            request.setAttribute("class_num", classNum);
            List<String> list=cNumDao.filter(teacher.getSchool());
            request.setAttribute("class_num_set", list);
            List<Integer> entYearSet=new ArrayList<>();
            for (int i=year-10; i<year+1; i++) {
                entYearSet.add(i);
            }
            request.setAttribute("ent_year_set", entYearSet);
            request.getRequestDispatcher("student_create.jsp")
                .forward(request, response);
            return;
        }

        student=new Student();
        student.setEntYear(entYear);
        student.setNo(no);
        student.setName(name);
        student.setClassNum(classNum);
        student.setAttend(true);
        student.setSchool(teacher.getSchool());

        boolean result=sDao.save(student);

        // JSPへフォワード
        request.getRequestDispatcher("student_create_done.jsp").forward(request, response);

    }
}
