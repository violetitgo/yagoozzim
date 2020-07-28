package com.semi.yagoozzim.index.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class IndexDao {
	
	@Autowired
	SqlSession sqlSession;
	
	

}
