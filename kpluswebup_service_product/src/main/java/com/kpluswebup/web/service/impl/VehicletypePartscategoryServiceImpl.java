package com.kpluswebup.web.service.impl;

import java.util.List;

import com.kpluswebup.web.partscategory.dao.VehicletypePartscategoryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.service.VehicletypePartscategoryService;
import com.kpluswebup.web.vo.VehicletypePartscategoryEntity;

@Service
public class VehicletypePartscategoryServiceImpl implements
		VehicletypePartscategoryService {

	@Autowired
	private VehicletypePartscategoryDAO dao;
	
	@Override
	public List<VehicletypePartscategoryEntity> findByPartscategorysByVehicletype(
			VehicletypePartscategoryEntity entity) {
		
		return dao.findByPartscategorysByVehicletype(entity); 
	}

}
