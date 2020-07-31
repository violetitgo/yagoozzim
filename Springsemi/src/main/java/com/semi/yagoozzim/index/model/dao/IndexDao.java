package com.semi.yagoozzim.index.model.dao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.semi.yagoozzim.index.model.vo.PlayData;


@Repository
public class IndexDao {
	
	@Autowired
	SqlSession sqlSession;
	


	public List<PlayData> selectData(String sysdate, String tomorrow, String aftertomorrow){
	
		Map<String, Object> data = new HashMap<>();
		data.put("sysdate",sysdate);
		data.put("tomorrow",tomorrow);
		data.put("aftertomorrow",aftertomorrow);
		
		
		return sqlSession.selectList("PlayData.selectData",data);
	}
	
	public int insertData(List<Map<String, String>> datalist) {
		return 0;
	}
	

}
