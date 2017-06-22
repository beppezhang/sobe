package com.kpluswebup.web.admin.system.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.ExpressDAO;
import com.kpluswebup.web.admin.system.service.ExpressService;
import com.kpluswebup.web.domain.ExpressDTO;
import com.kpluswebup.web.vo.ExpressVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class ExpressServiceImpl implements ExpressService {

    @Autowired
    private ExpressDAO expressDAO;

    public Integer addExpress(ExpressDTO expressDTO) {

        return expressDAO.addExpress(expressDTO);
    }

    public Long updateExpressByMainID(ExpressDTO expressDTO) {

        return expressDAO.updateExpressByMainID(expressDTO);
    }

    public Boolean deleteExpressByMainID(String mainID) {
        notNull(mainID, "name is null");
        try {
            expressDAO.deleteExpressByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ExpressVO> findALlExpress() {

        return expressDAO.findALlExpress();
    }

    public void addExpress(String name, String contactPerson, String mobile, String code, String isDefault,
                           String description, String currentOperator) {
        notNull(name, "name is null");
        notNull(contactPerson, "contactPerson is null");
        notNull(mobile, "mobile is null");
        notNull(code, "code is null");
        notNull(isDefault, "isDefault is null");
        notNull(description, "description is null");
        ExpressDTO expressDTO = new ExpressDTO();
        expressDTO.setCode(code);
        expressDTO.setDef(Integer.parseInt(isDefault));
        expressDTO.setMobile(mobile);
        expressDTO.setName(name);
        expressDTO.setDescription(description);
        expressDTO.setContactPerson(contactPerson);
        expressDTO.setMainID(UUIDUtil.getUUID());
        expressDTO.setCreator(currentOperator);
        if (isDefault.equals("1")) {
            ExpressVO expressVO = expressDAO.findExpressIsDefault();
            if (expressVO != null) {
                expressDAO.updateExpressDefault(expressVO.getMainID());
            }
        }
        expressDAO.addExpress(expressDTO);
    }

    public ExpressVO findExpressByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return expressDAO.findExpressByMainID(mainID);
    }
    
    
    @Override
    public void editExpress(String mainID, String name, String contactPerson, String mobile, String code,
                            String isDefault, String description, String currentOperator) {
        notNull(name, "name is null");
        notNull(contactPerson, "contactPerson is null");
        notNull(mobile, "mobile is null");
        //notNull(code, "code is null");
        notNull(isDefault, "isDefault is null");
        notNull(description, "description is null");
        ExpressDTO expressDTO = new ExpressDTO();
        expressDTO.setCode(code);
        expressDTO.setDef(Integer.parseInt(isDefault));
        expressDTO.setMobile(mobile);
        expressDTO.setName(name);
        expressDTO.setDescription(description);
        expressDTO.setContactPerson(contactPerson);
        expressDTO.setMainID(mainID);
        expressDTO.setModifier(currentOperator);
        if (isDefault.equals("1")) {
            ExpressVO expressVO = expressDAO.findExpressIsDefault();
            if (expressVO != null) {
                expressDAO.updateExpressDefault(expressVO.getMainID());
            }
        }
        expressDAO.updateExpressByMainID(expressDTO);
    }

	@Override
	public ExpressVO findExpressByName(String name) {
		 notNull(name, "name is null");
	        return expressDAO.findExpressByName(name);
	}

	@Override
	public void addExpress(String name, String contactPerson, String mobile,
			String code, String isDefault, String description,
			String currentOperator, SupplierVO supplierVO) {
        notNull(name, "name is null");
        notNull(contactPerson, "contactPerson is null");
        notNull(mobile, "mobile is null");
        //notNull(code, "code is null");
        notNull(isDefault, "isDefault is null");
        notNull(description, "description is null");
        ExpressDTO expressDTO = new ExpressDTO();
        expressDTO.setCode(code);
        expressDTO.setDef(Integer.parseInt(isDefault));
        expressDTO.setMobile(mobile);
        expressDTO.setName(name);
        expressDTO.setDescription(description);
        expressDTO.setContactPerson(contactPerson);
        expressDTO.setMainID(UUIDUtil.getUUID());
        expressDTO.setCreator(currentOperator);
        expressDTO.setSupplierID(supplierVO.getMainID());
        if (isDefault.equals("1")) {
            ExpressVO expressVO = expressDAO.findExpressIsDefault();
            if (expressVO != null) {
                expressDAO.updateExpressDefault(expressVO.getMainID());
            }
        }
        expressDAO.addExpress(expressDTO);
	}

	@Override
	public List<ExpressVO> findALlExpressBySupplierID(String supplierID) {
        return expressDAO.findALlExpressBySupplierID(supplierID);
	}
	
	@Override
	public List<ExpressVO> findExpressPageBySupplierID(ExpressDTO expressDTO) {
        long count = expressDAO.findExpressCountBySupplierID(expressDTO);
        expressDTO.doPage(count, expressDTO.getPageNo(), expressDTO.getPageSize());
        return expressDAO.findExpressPageBySupplierID(expressDTO);
	}

	@Override
	public ExpressVO findExpressByName(ExpressDTO expressDTO) {
		return expressDAO.findExpress(expressDTO);
	}

}
