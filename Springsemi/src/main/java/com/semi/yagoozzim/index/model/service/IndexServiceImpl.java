package com.semi.yagoozzim.index.model.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.semi.yagoozzim.index.model.dao.IndexDao;

public class IndexServiceImpl implements IndexService {
	
	@Autowired
	IndexDao indexDao;
	

}
