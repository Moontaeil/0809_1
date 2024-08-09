package kr.co.softsoldesk.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.DAO.UserDAO;
import kr.co.softsoldesk.beans.UserBean;

@Service
public class UserService {

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@Autowired
	UserDAO userDAO;
	
	//중복확인
	public boolean checkUserIdExist(String user_id) {
		
		String checkId = userDAO.checkUserIdExist(user_id);
		
		if(checkId==null) {
			return true;
		}else {
			return false;
		}
		
	}
	//회원가입
	public void addUser(UserBean joinUserBean) {
		
		userDAO.addUser(joinUserBean);
	}
	
	//로그인
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		
		UserBean tempLoginUserBean2 = userDAO.getLoginUserInfo(tempLoginUserBean);
		
		//System.out.println(tempLoginUserBean2.getUser_idx() + tempLoginUserBean.getUser_name());
		
		if(tempLoginUserBean2!=null) {
			
			loginUserBean.setUser_idx(tempLoginUserBean2.getUser_idx());
			loginUserBean.setUser_name(tempLoginUserBean2.getUser_name());
			loginUserBean.setUserLogin(true);
		}
	}
	
	public void getModifyUserInfo(UserBean modifyUserBean) {
		
		UserBean tempModifyUserBean = userDAO.getModifyUserInfo(loginUserBean.getUser_idx());
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		
		modifyUserBean.setUser_idx(loginUserBean.getUser_idx());
		userDAO.modifyUserInfo(modifyUserBean);
	}
	
}
