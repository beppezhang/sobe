package com.kpluswebup.web.content.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.CmsWechatReplySetDAO;
import com.kpluswebup.web.content.service.CmsWechatReplySetService;
import com.kpluswebup.web.domain.CmsWechatReplySetDTO;
import com.kpluswebup.web.vo.CmsWechatReplySetVO;
import com.kpuswebup.comom.util.StringUtil;

@Service
public class CmsWechatReplySetServiceImpl implements CmsWechatReplySetService {

    @Autowired
    private CmsWechatReplySetDAO cmsWechatReplySetDAO;

    @Override
    public List<CmsWechatReplySetVO> findWechatReplyByPagination(CmsWechatReplySetDTO cmsWechatReplySetDTO) {
        if (StringUtil.isEmpty(cmsWechatReplySetDTO.getInterfaceConfigID())) {
            cmsWechatReplySetDTO.setInterfaceConfigID(null);
        }
        Long count = cmsWechatReplySetDAO.findWechatReplyCount(cmsWechatReplySetDTO);
        cmsWechatReplySetDTO.doPage(count, cmsWechatReplySetDTO.getPageNo(), cmsWechatReplySetDTO.getPageSize());
        List<CmsWechatReplySetVO> list = cmsWechatReplySetDAO.findWechatReplyByPagination(cmsWechatReplySetDTO);
        return list;
    }

    @Override
    public Boolean deleteWechatReplyByMainID(String mainIDs) {
        try {
            String[] ids = mainIDs.split(",");
            for (String mainID : ids) {
                cmsWechatReplySetDAO.deleteWechatReplyByMainID(mainID);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addWechatReply(CmsWechatReplySetDTO cmsWechatReplySetDTO) {
        cmsWechatReplySetDAO.insertWechatReply(cmsWechatReplySetDTO);
    }

    @Override
    public CmsWechatReplySetVO findWechatReplyByMainID(String mainID) {
        return cmsWechatReplySetDAO.findWechatReplyByMainID(mainID);
    }

    @Override
    public void updateWechatReplyByMainID(CmsWechatReplySetDTO cmsWechatReplySetDTO) {
        cmsWechatReplySetDAO.updateWechatReplyByMainID(cmsWechatReplySetDTO);
    }

    @Override
    public CmsWechatReplySetVO findWechatByKeyword(String keyword) {
        return cmsWechatReplySetDAO.findWechatByKeyword(keyword);
    }


}
