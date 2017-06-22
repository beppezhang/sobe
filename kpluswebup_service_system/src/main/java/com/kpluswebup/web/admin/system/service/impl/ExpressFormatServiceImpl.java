package com.kpluswebup.web.admin.system.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.ExpressFormatDAO;
import com.kpluswebup.web.admin.system.service.ExpressFormatService;
import com.kpluswebup.web.domain.ExpressFormatDTO;
import com.kpluswebup.web.vo.ExpressFormatVO;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class ExpressFormatServiceImpl implements ExpressFormatService {

    @Autowired
    private ExpressFormatDAO expressFormatDAO;

    public String addExpressFormat(String name, String expressID, String picURL, String width, String height,
                                 String isDefault) {
        ExpressFormatDTO expressFormatDTO = new ExpressFormatDTO();
        expressFormatDTO.setExpressID(expressID);
        expressFormatDTO.setName(name);
        expressFormatDTO.setPicURL(picURL);
        if (StringUtil.isNotEmpty(width)) {
            expressFormatDTO.setWidth(Double.parseDouble(width));
        } else {
            expressFormatDTO.setWidth(0d);
        }

        if (StringUtil.isNotEmpty(height)) {
            expressFormatDTO.setHeight(Double.parseDouble(height));
        } else {
            expressFormatDTO.setHeight(0d);
        }
        expressFormatDTO.setMainID(UUIDUtil.getUUID());
        expressFormatDAO.addExpressFormat(expressFormatDTO);
        return expressFormatDTO.getMainID();

    }

    public List<ExpressFormatVO> findALLExpressFormat() {
        return expressFormatDAO.findExpressFormat(new ExpressFormatDTO());
    }

    public Boolean deleteExpressFormatByMainID(String mainID) {
        notNull(mainID, "name is null");
        try {
            expressFormatDAO.deleteExpressFormatByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public ExpressFormatVO findExpressFormatByMainID(String mainID) {
        notNull(mainID, "name is null");
        return expressFormatDAO.findExpressFormatByMainID(mainID);
    }

    
    public void editExpressFormat(String mainID, String name, String expressID, String isDefault, String picURL,
                                  String width, String height,String currentOperator) {
        notNull(mainID, "name is null");
        ExpressFormatDTO expressFormatDTO = new ExpressFormatDTO();
        expressFormatDTO.setExpressID(expressID);
        expressFormatDTO.setName(name);
        expressFormatDTO.setPicURL(picURL);
        if (StringUtil.isNotEmpty(width)) {
            expressFormatDTO.setWidth(Double.parseDouble(width));
        } else {
            expressFormatDTO.setWidth(0d);
        }

        if (StringUtil.isNotEmpty(height)) {
            expressFormatDTO.setHeight(Double.parseDouble(height));
        } else {
            expressFormatDTO.setHeight(0d);
        }
        expressFormatDTO.setMainID(mainID);
        expressFormatDTO.setModifier(currentOperator);
        expressFormatDAO.updateExpressFormatByMainID(expressFormatDTO);
        
    }
}
