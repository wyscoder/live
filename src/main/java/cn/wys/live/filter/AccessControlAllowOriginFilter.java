package cn.wys.live.filter;

import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wys
 * @date 2019/11/2
 */

public class AccessControlAllowOriginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(req, response);
    }
    @Override
    public void init(FilterConfig filterConfig) {

    }
    @Override
    public void destroy() {

    }

}
