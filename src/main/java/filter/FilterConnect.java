package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin")
public class FilterConnect implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {	}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        String attribute = (String) session.getAttribute("role");
        if(attribute == null){
            res.sendRedirect("/index");
        }

        if ("user".equals(attribute)) {   //checking whether the session exists
            res.sendRedirect(req.getContextPath() + "/user");
        } else if ("admin".equals(attribute)){
            chain.doFilter(request, response);
        }

    }
    public void destroy() {	}
}
