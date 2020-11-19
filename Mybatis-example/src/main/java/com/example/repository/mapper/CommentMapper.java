package com.example.repository.mapper;

import java.util.List;
import java.util.Map;

import com.example.model.Comment;

public interface CommentMapper {
	Comment selectCommentByPrimaryKey(long commentNo);
	
	List<Comment> selectCommentByCondition(Map<String, Object> condition);
	
	int insertComment(Comment comment);   
 
	int updateComment(Comment comment);
	
	int deleteComment(long commentNo);

	int deleteAllComments();
}
