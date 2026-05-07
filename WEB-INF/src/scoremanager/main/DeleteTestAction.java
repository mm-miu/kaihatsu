package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
import dao.TestDao;

public class DeleteTestAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        String studentNo = req.getParameter("student_no");
        String subjectCd = req.getParameter("subject_cd");
        String schoolCd = req.getParameter("school_cd");
        int no = Integer.parseInt(req.getParameter("no"));

        TestDao dao = null;
        boolean deleted = false;//boolean deleted = dao.delete(studentNo, subjectCd, schoolCd, no);あってるかもしれないから残しとく
        String message;

        try {
            dao = new TestDao();//DB操作用のクラスをインスタンス化
            deleted = dao.delete(studentNo, subjectCd, schoolCd, no);//削除処理を実行
            message = deleted ? "削除しました。" : "対象データが見つかりませんでした。";
        } catch (Exception e) {
            message = "削除処理中にエラーが発生しました";
            e.printStackTrace();
        }
        /*
        message = deleted ? "削除しました。" : "削除できませんでした。";
        この部分は三項演算子と言って
        意味はこう：(boolean型に対するtrueとfalseの判別)
        if (deleted) {
            message = "削除できました。";
        } else {
            message = "削除できませんでした(対象が見つかりません)";
        }
        */


        // JSP に削除結果と学生番号を渡してフォワード
        req.setAttribute("deleted", deleted);
        req.setAttribute("message", message);
        req.setAttribute("studentNo", studentNo);

        req.getRequestDispatcher("test_delete.jsp").forward(req, res);
    }
}
