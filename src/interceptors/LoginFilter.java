package interceptors;
import javax.servlet.Filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
public class LoginFilter implements Filter {
	
	public void init(FilterConfig config)
          throws ServletException{
		
		
	}
	public void doFilter(ServletRequest request,
              ServletResponse response,
              FilterChain chain)
              throws IOException,
                     ServletException{
		String Admin_name=(String) ((HttpServletRequest) request).getSession().getAttribute("");
		
		
			chain.doFilter(request,response) ;
	
			((HttpServletResponse) response).sendRedirect("/book/errors/errors2.jsp");
		
	
	}
	public void destroy(){
	}
}
