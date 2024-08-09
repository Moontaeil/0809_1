package kr.co.softsoldesk.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.mapper.UserMapper;

@Repository
public class UserDAO {

	@Autowired
	private UserMapper userMapper;
	
	//ID 중복확인
	public String checkUserIdExist(String user_id) {
		
		return userMapper.checkUserIdExist(user_id);
		
	}
	//회원가입
	public void addUser(UserBean joinUserBean) {
		
		userMapper.addUser(joinUserBean);
		
	}
	//로그인
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		
		
		return userMapper.getLoginUserInfo(tempLoginUserBean);
	}
	//수정할 회원 정보 가져오기
	public UserBean getModifyUserInfo(int user_idx) {
		
		return userMapper.getModifyUserInfo(user_idx);
	}
	//회원수정-DB
	public void modifyUserInfo(UserBean modifyUserBean) {
		
		System.out.println(modifyUserBean.getUser_idx());
		System.out.println(modifyUserBean.getUser_name());
		System.out.println(modifyUserBean.getUser_pw());
		
		userMapper.modifyUserInfo(modifyUserBean);
		
	}
	
	
}
