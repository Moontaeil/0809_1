package kr.co.softsoldesk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.softsoldesk.beans.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		UserBean userBean = (UserBean)target;
		
		//객체명 확인
		String beanName = errors.getObjectName();
		//System.out.println(beanName);
		
		if(beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {
		
			if(userBean.getUser_pw().equals(userBean.getUser_pw2())==false) {//비밀번호, 비밀번호 확인 불일치
				
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			if(beanName.equals("joinUserBean")) {
				if(userBean.isUserIdExist()==false) {
					errors.rejectValue("user_id", "DontcheckUserIdExist");
				}
			}
		}
	}

}
