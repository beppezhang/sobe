package com.kpluswebup.web.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.CmsWechatMenuDAO;
import com.kpluswebup.web.content.service.CmsWechatMenuService;
import com.kpluswebup.web.domain.CmsWechatMenuDTO;
import com.kpluswebup.web.vo.CmsWechatMenuVO;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class CmsWechatMenuServiceImpl implements CmsWechatMenuService {

    @Autowired
    private CmsWechatMenuDAO cmsWechatMenuDAO;
    

    @Override
    public List<CmsWechatMenuVO> findWechatMenuByPagination(CmsWechatMenuDTO cmsWechatMenuDTO) {
        if (StringUtil.isEmpty(cmsWechatMenuDTO.getInterfaceConfigID())) {
            cmsWechatMenuDTO.setInterfaceConfigID(null);
        }
        Long count = cmsWechatMenuDAO.findWechatMenuCount(cmsWechatMenuDTO);
        cmsWechatMenuDTO.doPage(count, cmsWechatMenuDTO.getPageNo(), cmsWechatMenuDTO.getPageSize());
        List<CmsWechatMenuVO> list = cmsWechatMenuDAO.findWechatMenuByPagination(cmsWechatMenuDTO);
        if (list != null && list.size() > 0) {
            for (CmsWechatMenuVO cmsWechatMenuVO : list) {
                List<CmsWechatMenuVO> childList = cmsWechatMenuDAO.findCmsWechatMenuChildByParentID(cmsWechatMenuVO.getMainID());
                cmsWechatMenuVO.setCmsWechatMenuChild(childList);
            }
        }
        return list;
    }

    @Override
    public Boolean deleteWechatMenuByMainID(String mainIDs) {
        try {
            String ids[] = mainIDs.split(",");
            for (String mainID : ids) {
                cmsWechatMenuDAO.deleteWechatMenuByMainID(mainID);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addWechatMenu(CmsWechatMenuDTO cmsWechatMenuDTO) {
        cmsWechatMenuDAO.insertWechatMenu(cmsWechatMenuDTO);
    }

    @Override
    public CmsWechatMenuVO findWechatMenuByMainID(String mainID) {
        return cmsWechatMenuDAO.findWechatMenuByMainID(mainID);
    }

    @Override
    public void updateWechatMenuByMainID(CmsWechatMenuDTO cmsWechatMenuDTO) {
        cmsWechatMenuDAO.updateWechatMenuByMainID(cmsWechatMenuDTO);
    }

    @Override
    public List<CmsWechatMenuVO> findCmsWechatMenuParent() {
        return cmsWechatMenuDAO.findCmsWechatMenuParent();
    }

    @Override
    public List<CmsWechatMenuVO> findCmsWechatMenuChildByParentID(String parentID) {
        return cmsWechatMenuDAO.findCmsWechatMenuChildByParentID(parentID);
    }
    
}
