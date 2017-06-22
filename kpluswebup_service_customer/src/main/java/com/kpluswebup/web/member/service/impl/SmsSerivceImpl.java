package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.customer.dao.SMSDAO;
import com.kpluswebup.web.domain.SMSDTO;
import com.kpluswebup.web.member.service.SmsSerivce;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.SmsVO;
import com.kpuswebup.comom.util.SendSms;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class SmsSerivceImpl implements SmsSerivce {

    @Autowired
    private SMSDAO           smsdao;
    @Autowired
    private CustomerDAO      customerDAO;
    @Autowired
    private CustomerGroupDAO customerGroupDAO;

    @Override
    public List<SmsVO> findSmsByPagination(SMSDTO smsdto) {
        Long count = smsdao.findSmsCount(smsdto);
        smsdto.doPage(count, smsdto.getPageNo(), smsdto.getPageSize());
        List<SmsVO> list = smsdao.findSmsByPagination(smsdto);
        return list;
    }

    @Override
    public void smsSend(String[] customerIDs, String[] groupIDs, String title, String content, String currentOperator) {
        if (customerIDs != null) {
            for (String customerID : customerIDs) {
                CustomerVO customerVO = customerDAO.findMemberByMainID(customerID);
                SMSDTO smsDTO = new SMSDTO();
                smsDTO.setMainID(UUIDUtil.getUUID());
                smsDTO.setMobile(customerVO.getUsername());
                smsDTO.setCustomerID(customerID);
                smsDTO.setTitle(title);
                smsDTO.setContent(content);
                smsDTO.setCreator(currentOperator);
                Integer msg = 1;
                if (StringUtil.isNotEmpty(smsDTO.getMobile()) && StringUtil.isNotEmpty(content)) {
                    String[] telphone = { smsDTO.getMobile() };
                    msg = SendSms.sendSMS(telphone, content, 5);
                }
                if (msg == 0) {
                    smsDTO.setStatus(1);
                } else {
                    smsDTO.setStatus(2);
                }
                smsdao.smsSend(smsDTO);
            }
        }
        if (groupIDs != null) {
            for (String groupID : groupIDs) {
                List<CustomerGroupMemberVO> list = customerGroupDAO.findCustomerGroupMemberByGroupID(groupID);
                if (list != null && list.size() > 0) {
                    for (CustomerGroupMemberVO customerGroupMemberVO : list) {
                        CustomerVO customerVO = customerDAO.findMemberByMainID(customerGroupMemberVO.getCustomerID());
                        SMSDTO smsDTO = new SMSDTO();
                        smsDTO.setMainID(UUIDUtil.getUUID());
                        smsDTO.setMobile(customerVO.getUsername());
                        smsDTO.setCustomerID(customerVO.getMainID());
                        smsDTO.setTitle(title);
                        smsDTO.setContent(content);
                        smsDTO.setCreator(currentOperator);
                        Integer msg = 1;
                        if (StringUtil.isNotEmpty(smsDTO.getMobile()) && StringUtil.isNotEmpty(content)) {
                            String[] telphone = { smsDTO.getMobile() };
                            msg = SendSms.sendSMS(telphone, content, 5);
                        }
                        if (msg == 0) {
                            smsDTO.setStatus(1);
                        } else {
                            smsDTO.setStatus(2);
                        }
                        smsdao.smsSend(smsDTO);
                    }
                }
            }
        }
    }

    @Override
    public SmsVO findSmsByMaminID(String mainID) {
        return smsdao.findSmsByMaminID(mainID);
    }

    @Override
    public void updateSmsByMainID(SMSDTO smsdto) {
        smsdao.updateSmsByMainID(smsdto);
    }

}
