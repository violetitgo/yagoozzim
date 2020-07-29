package com.semi.yagoozzim.index.model.service;

import java.util.Map;

public interface IndexService {
	
	public Map<String,Object>  selectData(String sysdate, String tomorrow, String aftertomorrow);

}
