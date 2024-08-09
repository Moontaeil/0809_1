package kr.co.softsoldesk.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.mapper.BoardMapper;

@Repository
public class BoardDAO {

	@Autowired
	private BoardMapper boardMapper;
	
	//게시글 저장
	public void addContentInfo(ContentBean writeContentBean) {
		
		boardMapper.addContentInfo(writeContentBean);
		
	}
	
	//게시판 이름 가져오기
	public String getBoardInfoName(int board_info_idx) {
		
		return boardMapper.getBoardInfoName(board_info_idx);
	}
	
	//특정 게시판의 게시글 제목, 작성자 이름, 작성날짜 가져오기
	public List<ContentBean> getContentList(int board_info_idx){
		
		return boardMapper.getContentList(board_info_idx);
	}
	
	//특정 게시글 가져오기
	public ContentBean getContentInfo(int content_idx) {
		
		return boardMapper.getContentInfo(content_idx);
	}
	
	//게시글 업데이트
	public void modifyContentInfo(ContentBean modifyContentBean) {
		
		boardMapper.modifyContentInfo(modifyContentBean);
	}
	
	//게시글 삭제
	public void deleteContentInfo(int content_idx) {
		
		boardMapper.deleteContentInfo(content_idx);
	}
	
}
