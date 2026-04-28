package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.School;
import bean.Subject;
import bean.Test;
import bean.Student;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import dao.StudentDao;
import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");
        School school = null;
        school=teacher.getSchool();

        List<Test> tests=new ArrayList<>();// 成績
        TestDao tDao=new TestDao();// 成績DAO
        StudentDao stDao=new StudentDao();// 学生DAO
        SubjectDao suDao=new SubjectDao();// 科目DAO
        Map<String, String> errors=new HashMap<>();// エラーメッセージ

        // リクエストパラメーターの取得
        String[] registList=request.getParameterValues("regist");// 学生番号のリスト
        String count=request.getParameter("count");// 回数
        String subjectCd=request.getParameter("subject");// 科目CD
        Subject subject=suDao.get(subjectCd, school);

                // jspに送る値を作成
                // 入学年度一覧を作成
                LocalDate todaysDate=LocalDate.now();// LocalDateインスタンスを取得
                int year=todaysDate.getYear();// 現在の年を取得
                List<Integer> entYearSet=new ArrayList<>();
                for (int i=year-10; i<year+1; i++) {
                    entYearSet.add(i);
                }

                //クラス一覧を作成
                ClassNumDao cnDao = new ClassNumDao();
                List<String> classList = cnDao.filter(school);

                //科目一覧を作成
                List<Subject> subjectList = suDao.filter(school);

                // テスト回数のリストを作成
                List<Integer> countSet=new ArrayList<>();
                for (int i = 1; i <= 2; i++) {
                    countSet.add(i);
                }
                // JSP に渡す属性
                request.setAttribute("ent_year_set", entYearSet);
                request.setAttribute("class_num_set", classList);
                request.setAttribute("subject_set", subjectList);
                request.setAttribute("count_set", countSet);


        // 学生ごとの点数を取り出す
        for (String studentNo : registList) {
            Student student=stDao.get(studentNo);
            String pointStr=request.getParameter("point_"+studentNo);
            int point=Integer.parseInt(pointStr);

            // 範囲外ならエラーを送信する
            if (point<0 || point>100) {
                errors.put(studentNo, "0~100の範囲で入力してください");
                continue;
            
            }

            // 既存の成績データを取得しpointを上書き
            Test test=tDao.get(student, subject, school, Integer.parseInt(count));
            test.setPoint(point);
            tests.add(test);
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("test_regist.jsp").forward(request, response);
            return;
        }

        // 一括更新
        if (tests.size()>0) {
            tDao.update(tests);
        }

        // JSPへフォワード
        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);

    }
}
