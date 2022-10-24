package core.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(value = {
		"/collection/toBeFilteredBack/*",
		"/tag/toBeFilteredBack/*",
		"/contact/toBeFilteredBack/*", 
//		"/exhibition/toBeFilteredBack/*", 
		"/member/toBeFilteredBack/*", 
//		"/order/toBeFilteredBack/*", 
//		"/prod/toBeFilteredBack/*",
})
public class FilterByEBack extends HttpFilter implements Filter {

//	public FilterByEBack() {
//		super();
//		// TODO Auto-generated constructor stub
//	}


	private static final long serialVersionUID = 1L;

	// server啟動時會自動調用此init方法，啟動filter1次
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("filterByEBack啟動");
	}

	// 瀏覽器向server發送請求n次
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		//	chain.doFilter(req, resp);

		// 強制將轉型成HTTP物件
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		// 獲取瀏覽器請求資源的路徑
		String uri = request.getRequestURI();
		//	System.out.println(uri);
		
		//判斷是否為登入相關程式碼（要確認路徑名稱）
		if (uri.contains("/member/manlogin")
			|| uri.contains("/member/register")
			|| uri.contains("/member/forgetpass")
			|| uri.contains("/member/workCss/") 
			|| uri.contains("/member/workJs/")
			|| uri.contains("/member/workImage/")
		) {
			// System.out.println(456);
			// 是就放行，讓瀏覽器可以訪問到servlet或JSP
			chain.doFilter(req, resp);
		} else {
			// 不是，就要檢查是否已經登入
			Object user = request.getSession().getAttribute("member");
			if (user != null) {
				
				// 放行
				chain.doFilter(req, resp);
			} else {
				
				// 存入目前想去的頁面
				request.getSession().setAttribute("url", request.getRequestURI());
				// 轉發到登入頁面（要確認實際登入程式的檔案名稱）
				response.sendRedirect(request.getContextPath()+"/member/manegerboot.html");
			}
		}
	}
	
	public void destroy() {
	}
}
