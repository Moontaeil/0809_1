package kr.co.softsoldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.softsoldesk.beans.ContentBean;

public interface BoardMapper {

	@Insert("insert into content_table(content_idx, content_subject, content_text, content_file,"
			+ "content_writer_idx, content_board_idx, content_date)"
			+ "values(content_seq.nextval, #{content_subject}, #{content_text}, #{content_file, jdbcType=VARCHAR},"
			+ "#{content_writer_idx}, #{content_board_idx}, sysdate)")
	void addContentInfo(ContentBean writeContentBean);
	//jdbcType=VARCHAR: null값 허용(null을 문자로 인식)

	@Select("select board_info_name from board_info_table where board_info_idx = #{board_info_idx}")
	String getBoardInfoName(int board_info_idx);
	
	@Select("select c.content_idx, c.content_subject, to_char(c.content_date, 'YYYY-MM-DD') as content_date, "
			+ "u.user_name as content_writer_name "
			+ "from content_table c, user_table u "
			+ "where c.content_writer_idx = u.user_idx "
			+ "and c.content_board_idx = #{board_info_idx} "
			+ "order by content_idx desc")
	List<ContentBean> getContentList(int board_info_idx);
	
	@Select("select c.content_idx, c.content_writer_idx, u.user_name as content_writer_name, c.content_subject, c.content_text, to_char(c.content_date, 'YYYY-MM-DD') as content_date, "
			+ "c.content_file "
			+ "from content_table c, user_table u "
			+ "where u.user_idx = c.content_writer_idx "
			+ "and content_idx = #{content_idx}")
	ContentBean getContentInfo(int content_idx);
	
	
	@Update("update content_table set content_subject = #{content_subject}, "
			+ "content_text = #{content_text}, "
			+ "content_file = #{content_file, jdbcType=VARCHAR} "
			+ "where content_idx = #{content_idx}")
	void modifyContentInfo(ContentBean modifyContentBean);
	
	@Delete("delete from content_table where content_idx = #{content_idx}")
	void deleteContentInfo(int content_idx);

}
