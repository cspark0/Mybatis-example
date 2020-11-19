package com.example.repository;

import java.util.*;

import com.example.model.Comment;
import com.example.model.Reply;
import com.example.repository.session.CommentSessionRepository;

public class CommentSessionRepositoryTest2 {
	private static CommentSessionRepository commentDao = new CommentSessionRepository();
	
	public static void main(String[] args) {
		System.out.println("CommentSessionRepositoryTest2 starts...");
		
		try {
			insertComment(20200001L, "user1", "comment #1");
			
			insertReply(20200101L, 20200001L, "user2", "1st reply");
			insertReply(20200102L, 20200001L, "user3", "2nd reply");
			insertReply(20200103L, 20200001L, "user2", "3rd reply");
			System.out.println();
			
			selectCommentByPrimaryKey(20200001L);
			System.out.println();
			
			selectCommentByPrimaryKeyAssociation(20200001L);
			System.out.println();
			
			selectCommentByPrimaryKeyAssociation2(20200001L);
			System.out.println();
			
			selectCommentByPrimaryKeyCollection(20200001L);
			System.out.println();
		} finally {
			deleteAllReplies();
			deleteAllComments();
		}
	}

	public static void selectCommentByPrimaryKey(long commentNo) {		
		Comment comment = commentDao.selectCommentByPrimaryKey(commentNo);		
		
		System.out.println("selectCommentByPrimaryKey(" + commentNo + "): ");
		System.out.println(comment);
	}
	
	public static void selectCommentByPrimaryKeyAssociation(long commentNo) {		
		Comment comment = commentDao.selectCommentByPrimaryKeyAssociation(commentNo);	
		
		System.out.println("selectCommentByPrimaryKeyAssociation(" + commentNo + "): ");
		System.out.println(comment);
		System.out.println("user's name: " + comment.getUser().getUserName());
		System.out.println("user's phone: " + comment.getUser().getPhone());
	}

	public static void selectCommentByPrimaryKeyAssociation2(long commentNo) {		
		Comment comment = commentDao.selectCommentByPrimaryKeyAssociation2(commentNo);		
		
		System.out.println("selectCommentByPrimaryKeyAssociation2(" + commentNo + "): ");
		System.out.println(comment);
		System.out.println("user's name: " + comment.getUser().getUserName());
		System.out.println("user's phone: " + comment.getUser().getPhone());
	}
	
	public static void selectCommentByPrimaryKeyCollection(long commentNo) {
		Comment comment = commentDao.selectCommentByPrimaryKeyCollection(commentNo);	
		
		System.out.println("selectCommentByPrimaryKeyCollection(" + commentNo + "): ");
		System.out.println(comment);
		List<Reply> replies = comment.getReplies();
		System.out.println("- number of replies: " + replies.size());
		System.out.print("- reply IDs: ");
		for (int i = 0; i < replies.size(); i++) {
			System.out.print(replies.get(i).getReplyId() + ", ");
		}
		System.out.println();
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
	
	public static void insertReply(long replyId, long commentNo, String userId, String replyContent) {
		Reply reply = new Reply(replyId, commentNo, userId, replyContent, 
								Calendar.getInstance().getTime());
		int result = commentDao.insertReply(reply);		
		System.out.println("insertReply(" + replyId + ", " + commentNo + ",...): " + result);
	}
	
	public static void deleteAllComments() {		
		int result = commentDao.deleteAllComments();
		System.out.println("deleteAllComments(): " + result);
	}	
	
	public static void deleteAllReplies() {		
		int result = commentDao.deleteAllReplies();
		System.out.println("deleteAllReplys(): " + result);
	}	
}
