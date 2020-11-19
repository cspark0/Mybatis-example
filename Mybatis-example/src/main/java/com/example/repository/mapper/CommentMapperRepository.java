package com.example.repository.mapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.model.Comment;

public class CommentMapperRepository {
	private SqlSessionFactory sqlSessionFactory;
	
	public CommentMapperRepository() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	public Comment selectCommentByPrimaryKey(long commentNo) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CommentMapper.class).selectCommentByPrimaryKey(commentNo);			
		} finally {
			sqlSession.close();
		}
	}

	public List<Comment> selectCommentByCondition(Map<String, Object> condition) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.getMapper(CommentMapper.class).selectCommentByCondition(condition);			
		} finally {
			sqlSession.close();
		}
	}
	
	public int insertComment(Comment comment) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			int result = sqlSession.getMapper(CommentMapper.class).insertComment(comment);
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
			int result = sqlSession.getMapper(CommentMapper.class).updateComment(comment);
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
			int result = sqlSession.getMapper(CommentMapper.class).deleteComment(commentNo);
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
			int result = sqlSession.getMapper(CommentMapper.class).deleteAllComments();
			if (result > 0) {
				sqlSession.commit();
			} 
			return result;		
		} finally {
			sqlSession.close();
		}
	}
}