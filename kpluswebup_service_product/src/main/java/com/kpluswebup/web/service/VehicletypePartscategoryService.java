package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.vo.VehicletypePartscategoryEntity;

public interface VehicletypePartscategoryService {
	public List<VehicletypePartscategoryEntity> findByPartscategorysByVehicletype(VehicletypePartscategoryEntity entity);
}
