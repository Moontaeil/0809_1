package kr.co.softsoldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.UserBean;

public class CheckLoiginInterceptor implements HandlerInterceptor{
	
	//로그인 여부 판단하는 UserBean
	private UserBean loginUserBean;
	
	public CheckLoiginInterceptor(UserBean loginUserBean) {
		
		this.loginUserBean = loginUserBean;
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(loginUserBean.isUserLogin()==false) {//로그아웃 상태
			String contextPath = request.getContextPath();//루트경로
			response.sendRedirect(contextPath + "/user/not_login");
			return false;
		}
		
		return true;//로그인 상태
	}

	
	
}
