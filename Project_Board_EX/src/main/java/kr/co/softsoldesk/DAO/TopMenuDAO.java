package kr.co.softsoldesk.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.mapper.TopMenuMapper;

@Repository
public class TopMenuDAO {

	@Autowired
	private TopMenuMapper topMenuMapper;
	
	public List<BoardInfoBean> getTopMenuList(){
		
		List<BoardInfoBean> topMenuList = topMenuMapper.getTopMenuList();
		
		return topMenuList;
	}
}
