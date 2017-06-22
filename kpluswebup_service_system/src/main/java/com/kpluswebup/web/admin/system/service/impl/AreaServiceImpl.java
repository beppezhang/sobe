package com.kpluswebup.web.admin.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.AreaDAO;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CityVO;

@Service
public class AreaServiceImpl implements AreaService {
	
	private Logger log = Logger.getLogger("AreaServiceImpl.class");

    @Autowired
    private AreaDAO areaDAO;

    public List<AreaVO> getAllProvince() {
        // TODO 优化加入缓存
        return areaDAO.getAllProvince();
    }

    public List<AreaVO> getAreaByParentID(String parentID) {
        return areaDAO.getAreaByParentID(parentID);
    }
    public List<CityVO> getCityByParentID(String parentID) {
    	return areaDAO.getCityByParentID(parentID);
    }

	@Override
	public List<AreaVO> getCityByMainIDAndDepth(String mainID, String depth) {
		List<String> list = new ArrayList<String>();
		String[] areas = mainID.split("\\|");
		for (int i = 0; i < areas.length; i++) {
			String area = areas[i];
			String areaCode = area.split("%")[0];
			log.info(areaCode);
			list.add(areaCode);
		}
    	return areaDAO.getCityByMainIDAndDepth(list);
	}

}
