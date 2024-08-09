package kr.co.softsoldesk.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.softsoldesk.beans.BoardInfoBean;
import kr.co.softsoldesk.beans.ContentBean;
import kr.co.softsoldesk.beans.UserBean;
import kr.co.softsoldesk.service.BoardService;
import kr.co.softsoldesk.service.UserService;

	
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Resource(name = "loginUserBean")
	UserBean loginUserBean;
	
	@Autowired
	BoardService boardService;

	
	@GetMapping("/main")
	private String main(@RequestParam("board_info_idx")int board_info_idx, Model model) {
	//게시판 index 번호 가져오기
		
		String boardInfoName = boardService.getBoardInfoName(board_info_idx);//게시판 이름
		List<ContentBean> contentList = boardService.getContentList(board_info_idx);//게시판 게시글 들
		
		model.addAttribute("board_info_idx", board_info_idx);
		model.addAttribute("board_info_name", boardInfoName);
		model.addAttribute("contentList", contentList);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	private String read(@RequestParam("content_idx") int content_idx, 
						@RequestParam("board_info_idx") int board_info_idx, Model model) {
		
		ContentBean readContentBean = boardService.getContentInfo(content_idx);
		model.addAttribute("content", readContentBean);
		
		model.addAttribute("board_info_idx", board_info_idx);
		
		model.addAttribute("content_idx", content_idx);
		
		model.addAttribute("loginUserBean", loginUserBean);
		
		return "board/read";
	}
	
	@GetMapping("/write")
	private String write(@ModelAttribute("writeContentBean") ContentBean writeContentBean,
						 @RequestParam("board_info_idx") int board_info_idx, Model model) {
		
		writeContentBean.setContent_board_idx(board_info_idx);

		return "board/write";
	}
	@PostMapping("/write_pro")
	private String write_pro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
							 BindingResult result) {
		
		System.out.println(writeContentBean.getContent_board_idx());
		if(result.hasErrors()) {
			
			return "board/write";
		}
		
		boardService.addContentInfo(writeContentBean);
		
		return "board/write_success";
	}
	
	@GetMapping("/modify")
	private String modify(@RequestParam("content_idx") int content_idx, Model model) {
		
		ContentBean modifyContentBean = boardService.getContentInfo(content_idx);

		model.addAttribute("modifyContentBean", modifyContentBean);

		return "board/modify";
	}
	
	@PostMapping("/modify_pro")
	private String modify_pro(@Valid @ModelAttribute ("modifyContentBean") ContentBean modifyContentBean, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "board/modify";
		}
		
		System.out.println(modifyContentBean.getContent_idx());
		System.out.println(modifyContentBean.getContent_subject());
		boardService.modifyContentBean(modifyContentBean);
		
		return "board/modify_success";
	}
	
	@GetMapping("/not_writer")
	private String not_writer() {
		
		return "board/not_writer";
	}
	
	@GetMapping("/delete")
	private String delete(@RequestParam("content_idx") int content_idx) {

		boardService.deleteContentInfo(content_idx);
		
		return "board/delete";
	}
}
