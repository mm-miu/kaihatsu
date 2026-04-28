package scoremanager.main;

import tool.Action;
import jakarta.servlet.http.*;
import bean.Teacher;
import bean.Subject;
import dao.SubjectDao;

public class SubjectDeleteExecuteAction extends Action {

    @Override
    public void execute(
        HttpServletRequest request, HttpServletResponse response
    ) throws Exception {
        HttpSession session=request.getSession(); // セッション
        Teacher teacher=(Teacher)session.getAttribute("user");
        Subject subject=(Subject)session.getAttribute("subject");
        
        SubjectDao Sjdao = new SubjectDao();

        String school_cd= teacher.getSchool().getCd();
        System.out.println(subject.getCd());
        Subject Sj = new Subject();

        Sj.setCd(subject.getCd());
        Sj.setSchool(teacher.getSchool());

       boolean af= Sjdao.delete(Sj);

        if (af==false){
            request.getRequestDispatcher("/kaihatsu/scoremanager/main/SubjectList.action");
            return ;
        }else{
            request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
            return;
        }

    }
}
