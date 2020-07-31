package com.semi.yagoozzim.index.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.semi.yagoozzim.index.model.dao.IndexDao;
import com.semi.yagoozzim.index.model.vo.PlayData;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	IndexDao id;

	@Override
	public Map<String, Object> selectData(String sysdate, String tomorrow, String aftertomorrow) {
		Map<String, Object> res = new HashMap<>();
		List<PlayData> plist = id.selectData(sysdate, tomorrow, aftertomorrow);
		res.put("plist", plist);

		return res;
	}

	@Override
	public int insertData(List<Map<String, String>> datalist) {
		int res =  id.insertData(datalist);
		return res;
	}

}
