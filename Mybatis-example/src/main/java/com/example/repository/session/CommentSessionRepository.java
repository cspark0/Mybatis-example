package com.example.repository.session;

import java.io.*;
import java.util.*;

import com.example.model.Comment;
import com.example.model.Reply;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class CommentSessionRepository {
	private final String namespace = "com.example.repository.mapper.CommentMapper";

	private SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
	
	private SqlSessionFactory createSqlSessionFactory() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public Comment selectCommentByPrimaryKey(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(
					namespace + ".selectCommentByPrimaryKey", commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public List<Comment> selectCommentByCondition(Map<String, Object> condition) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList(
					namespace + ".selectCommentByCondition", condition);			
		} finally {
			sqlSession.close();
		}
	}

	public int insertComment(Comment comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.insert(namespace + ".insertComment", comment);
			if (result > 0) {
				sqlSession.commit();
			} 			
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int updateComment(Comment comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.update(namespace + ".updateComment", comment);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int deleteComment(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.delete(namespace + ".deleteComment", commentNo);
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	public int deleteAllComments() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.delete(namespace + ".deleteAllComments");
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
	
	public Comment selectCommentByPrimaryKeyAssociation(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(
					namespace + ".selectCommentByPrimaryKeyAssociation", commentNo);
		} finally {
			sqlSession.close();
		}
	}
	
	public Comment selectCommentByPrimaryKeyAssociation2(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(
					namespace + ".selectCommentByPrimaryKeyAssociation", commentNo);
		} finally {
			sqlSession.close();
		}
	}
	
	public Comment selectCommentByPrimaryKeyCollection(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return (Comment)sqlSession.selectOne(
					namespace + ".selectCommentByPrimaryKeyCollection", commentNo);
		} finally {
			sqlSession.close();
		}
	}

	public int insertReply(Reply reply) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.insert(namespace + ".insertReply", reply);
			if (result > 0) {
				sqlSession.commit();
			} 			
			return result;
		} finally {
			sqlSession.close();
		}
	}

	public int deleteAllReplies() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.delete(namespace + ".deleteAllReplies");
			if (result > 0) {
				sqlSession.commit();
			}
			return result;
		} finally {
			sqlSession.close();
		}
	}
}
