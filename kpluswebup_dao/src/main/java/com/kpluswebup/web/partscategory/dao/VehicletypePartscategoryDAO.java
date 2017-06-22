package com.kpluswebup.web.partscategory.dao;

import java.util.List;

import com.kpluswebup.web.vo.VehicletypePartscategoryEntity;

public interface VehicletypePartscategoryDAO {
	
	public List<VehicletypePartscategoryEntity> findByPartscategorysByVehicletype(VehicletypePartscategoryEntity entity);
}
