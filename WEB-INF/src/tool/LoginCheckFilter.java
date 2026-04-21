package tool;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

@WebFilter(urlPatterns = {"/scoremanager/main/*"})
public class LoginCheckFilter implements Filter {

    public void doFilter(
        ServletRequest request, ServletResponse response, 
        FilterChain chain
    ) throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String path = req.getServletPath();

        if (path.equals("/scoremanager/main/Logout.action") ||
            path.equals("/scoremanager/main/logout.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/scoremanager/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
    }
}
