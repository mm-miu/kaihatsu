package tool;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(
        HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        try {
            // パスを取得
            String path=request.getServletPath().substring(1);
            // ファイル名を取得しクラス名に変換
            String name=path.replace(".a", "A").replace('/', '.');
            
            // アクションクラスのインスタンスを返却
            Action action=(Action)Class.forName(name).
                getDeclaredConstructor().newInstance();

            // 遷移先URLを取得
            action.execute(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            // エラーメッセージへリダイレクト
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        doGet(request, response);
    }
}
