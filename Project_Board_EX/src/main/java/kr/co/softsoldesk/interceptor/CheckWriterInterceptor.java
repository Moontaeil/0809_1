package kr.co.softsoldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor{
	
	private UserBean loginUserBean;
	private BoardService boardService;
	
	public CheckWriterInterceptor(UserBean loginUserBean, BoardService boardService) {
		
		this.loginUserBean = loginUserBean;
		this.boardService = boardService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String str1 = request.getParameter("content_idx");//read.jsp에서 파라미터로 보낸 content_idx
		int content_idx = Integer.parseInt(str1);
		
		ContentBean currentContentBean = boardService.getContentInfo(content_idx);//현재 게시글 정보
		
		if(currentContentBean.getContent_writer_idx() != loginUserBean.getUser_idx()) {
		//작성자 회원번호와 로그인한 회원번호가 다르면
			
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/board/not_writer");
			//수정에 대한 요청이 아닌 새로운 요청(board/not_writer)으로 리다이렉트
			
			return false;
		}
		
		return true;
	}

	
}
