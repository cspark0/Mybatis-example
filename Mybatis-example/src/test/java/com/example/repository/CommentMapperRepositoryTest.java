package com.example.repository;

import java.util.*;

import com.example.model.Comment;
import com.example.repository.mapper.CommentMapperRepository;

public class CommentMapperRepositoryTest {
	private static CommentMapperRepository commentDao = new CommentMapperRepository();

	public static void main(String[] args) {
		System.out.println("CommentMapperRepository starts...");
		
		try {
			insertComment(20200004L, "user1", "comment #4");
			insertComment(20200005L, "user1", "comment #5");
			insertComment(20200006L, "user2", "comment #6");
			System.out.println();
			
			selectCommentByPrimaryKey(20200004L);
			selectCommentByPrimaryKey(20200005L);
			selectCommentByCondition("user1");
			System.out.println();
	
			updateComment(20200004L, "comment #0");
			selectCommentByCondition("user1");
			System.out.println();
			
			deleteComment(20200004L);
			selectCommentByCondition("user1");
			System.out.println();			
		} finally {
			deleteAllComments();
			selectCommentByCondition("user1");
		}
	}
	
	public static void selectCommentByPrimaryKey(Long commentNo) {		
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
	
	public static void insertComment(Long commentNo, String userId, String commentContent) {
		Date regDate = Calendar.getInstance().getTime();		
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setUserId(userId);
		comment.setCommentContent(commentContent);
		comment.setRegDate(regDate);
		
		int result = commentDao.insertComment(comment);
		System.out.println("insertComment(" + commentNo + ", ...): " + result);
	}

	public static void updateComment(Long commentNo, String commentContent) {
		Comment comment = new Comment();
		comment.setCommentNo(commentNo);
		comment.setCommentContent(commentContent);

		int result = commentDao.updateComment(comment);
		System.out.println("updateComment(" + commentNo + ", " + commentContent + "): " + result);
	}

	public static void deleteComment(Long commentNo) {
		int result = commentDao.deleteComment(commentNo);
		System.out.println("deleteComment(" + commentNo + "): " + result);
	}		
	
	public static void deleteAllComments() {		
		int result = commentDao.deleteAllComments();
		System.out.println("deleteAllComments(): " + result);
	}	
}
