package com.kpluswebup.web.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.SystemCodeDAO;
import com.kpluswebup.web.domain.BrandDTO;
import com.kpluswebup.web.product.dao.BrandDAO;
import com.kpluswebup.web.service.BrandService;
import com.kpluswebup.web.vo.BrandVO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.GenerationNum;

// TODO加入缓存
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDAO brandDAO;
    @Autowired
    private SystemCodeDAO systemCodeDAO;

    public List<BrandVO> findBrandList(BrandDTO brandDTO) {
        Long count = brandDAO.findBrandCount(brandDTO);
        brandDTO.doPage(count, brandDTO.getPageNo(), brandDTO.getPageSize());
        return brandDAO.findBrandByPagination(brandDTO);
    }

    public Boolean deleteBrandByMainID(String mainID) {
        try {
            brandDAO.deleteBrandByMainID(mainID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addBrand(BrandDTO brandDTO) {
        notNull(brandDTO, "brandDTO is null");
        CodeConfigVO codeConfigVO = systemCodeDAO.findCodeConfigByID(Constant.BRANDID);
        if (codeConfigVO != null) {
            String mainID = codeConfigVO.getCodeEx() + GenerationNum.getRandomNumber(9);
            brandDTO.setMainID(mainID);
        }
        brandDAO.insertBrand(brandDTO);
    }

    public BrandVO findBrandByMainID(String mainID) {
        notNull(mainID, "mainID is null");
        return brandDAO.findBrandByMainID(mainID);
    }

    public void editBrand(BrandDTO brandDTO) {
        notNull(brandDTO, "brandDTO is null");
        notNull(brandDTO.getMainID(), "MainID is null");
        brandDAO.updateBrandByMainID(brandDTO);

    }

    public List<BrandVO> findAllBrandList() {

        return brandDAO.findAllBrandList();
    }
    
    public List<BrandVO> findAllBrandListBycatID(String catID) {
        
        return brandDAO.findAllBrandListBycatID(catID);
    }
    public List<BrandVO> findAllBrandListByName(String bname) {
        
        return brandDAO.findAllBrandListByName(bname);
    }
    
    //*********************************************************sxc
    @Override
    public List<Map> findBrandParts() {
    	return brandDAO.findBrandParts();
    }
}
