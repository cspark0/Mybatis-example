package com.example.repository;

import java.util.*;

import com.example.model.Comment;
import com.example.repository.jdbc.CommentJdbcRepository;

public class CommentJdbcRepositoryTest {
	private static CommentJdbcRepository commentDao = new CommentJdbcRepository();

	public static void main(String[] args) {	
		System.out.println("CommentJdbcRepositoryTest starts...");
		
		try {
			insertComment(20200010L, "user1", "comment #10");
			insertComment(20200020L, "user1", "comment #20");
			insertComment(20200030L, "user2", "comment #30");
			System.out.println();
			
			selectCommentByPrimaryKey(20200010L);
			selectCommentByPrimaryKey(20200020L);
			selectCommentByCondition("user1");
			System.out.println();
	
			updateComment(20200010L, "comment #11");
			selectCommentByCondition("user1");
			System.out.println();
			
			deleteComment(20200010L);
			selectCommentByCondition("user1");
			System.out.println();			
		} finally {
			deleteAllComments();
			selectCommentByCondition("user1");
		}
	}
	
	public static void selectCommentByPrimaryKey(long commentNo) {		
		Comment comment = commentDao.selectCommentByPrimaryKey(commentNo);
		System.out.println("selectCommentByPrimaryKey(" + commentNo + "): ");
		System.out.println(comment);
	}

	public static void selectCommentByCondition(String userId) {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		List<Comment> list = commentDao.selectCommentByCondition(condition);
		System.out.println("selectCommentByCondition(" + userId + "): ");
		System.out.println(list);
	}
	
	public static void insertComment(long commentNo, String userId, String commentContent) {
		Date regDate = Calendar.getInstance().getTime();
		
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setUserId(userId);
		comment.setCommentContent(commentContent);
		comment.setRegDate(regDate);
		int result = commentDao.insertComment(comment);
		System.out.println("insertComment(" + commentNo + ", ...): " + result);
	}

	public static void updateComment(long commentNo, String commentContent) {
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setCommentContent(commentContent);
		int result = commentDao.updateComment(comment);
		System.out.println("updateComment(" + commentNo + ", " + commentContent + "): " + result);
	}

	public static void deleteComment(long commentNo) {
		int result = commentDao.deleteComment(commentNo);
		System.out.println("deleteComment(" + commentNo + "): " + result);
	}		
	
	public static void deleteAllComments() {		
		int result = commentDao.deleteAllComments();
		System.out.println("deleteAllComments(): " + result);
	}	
}
