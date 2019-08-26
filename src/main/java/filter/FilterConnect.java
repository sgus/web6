package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin")
public class FilterConnect implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {	}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Object role = request.getAttribute("role");
        System.out.println(role);
    }

    public void destroy() {	}
}
