package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.FocusDAO;
import com.kpluswebup.web.member.service.FocusService;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.FocusEntity;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class FocusServiceImpl implements FocusService {
	@Autowired
	private FocusDAO focusDAO;
	
	public FocusServiceImpl() {
		super();
	}	
	
	public FocusServiceImpl(FocusDAO focusDAO) {
		super();
		this.focusDAO = focusDAO;
	}

	@Override
	public List<FocusEntity> findByFocusEntity(FocusEntity focusEntity) {
		List<FocusEntity> focusList = focusDAO.findFocusByCustomerID(focusEntity);
		return focusList;
	}

	@Override
	public FocusEntity save(FocusEntity focusEntity) {
		Integer isInserted = focusDAO.insert(focusEntity);
		if (isInserted == 1){
			return focusEntity;
		}else{
			return null;
		}
	}

//	@Override
//	public FocusEntity save(FocusEntity focusEntity, CustomerVO customer) {
//		if(customer != null)
//		{
//			focusEntity.setCustomerID(customer.getMainID());
//			return save(focusEntity);
//		}
//		return null;
//	}

	@Override
	public FocusEntity save(String myFocusInfo, String referenceID,
			CustomerVO customer, Integer focusType) {
		//关注信息不为空则插入新的关注信息
		if (myFocusInfo != null && myFocusInfo != "" && customer != null){
			FocusEntity focusEntity = new FocusEntity();

			focusEntity.setCustomerID(customer.getMainID());
			focusEntity.setMyFocusInfo(myFocusInfo);
			focusEntity.setReferenceID(referenceID);
			focusEntity.setFocusType(focusType);

			//先根据上面四条信息查出是否已存在相关关注信息,若不存在则insert,否则update
			FocusEntity existFocus = focusDAO.findExistFocus(focusEntity);
			if (existFocus != null) {
				Integer result = focusDAO.update(focusEntity);
				
				//成功update则将查出来的（包含mainID）existFocus返回，否则返回空
				if (result == 1){
					return existFocus;
				} else {
					return null;
				}
			}

			String mainID = UUIDUtil.getOrigUUID();
			focusEntity.setMainID(mainID);
			focusEntity.setIsDelete(0);

			return save(focusEntity);
		}else {
			return null;
		}
	}

}
