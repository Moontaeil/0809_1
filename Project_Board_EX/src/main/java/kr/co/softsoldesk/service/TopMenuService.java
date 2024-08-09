package kr.co.softsoldesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.softsoldesk.DAO.TopMenuDAO;
import kr.co.softsoldesk.beans.BoardInfoBean;

@Service
public class TopMenuService {
	
	@Autowired
	private TopMenuDAO topMenuDAO;
	
	public List<BoardInfoBean> getTopMenuList(){
		
		List<BoardInfoBean>topMenuList = topMenuDAO.getTopMenuList();
		
		return topMenuList;
	}

}
