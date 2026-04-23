package scoremanager.main;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import tool.Action;
import dao.ClassNumDao;
import dao.SubjectDao;
import bean.Subject;
import bean.Teacher;
import bean.School;
import bean.ClassNum;

import java.util.List;

public class TestRegistAction extends Action{

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //ログイン中のユーザーを取得
        Teacher teacher = (Teacher)request.getSession().getAttribute("user");
        School school = teacher.getSchool();

        //クラス一覧を取得
        ClassNumDao cDao = new ClassNumDao();
        List<String> classList = cDao.filter(school);

        //科目一覧取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectList = sDao.filter(school);

        //JSPに渡す
        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);

    }
    
}
