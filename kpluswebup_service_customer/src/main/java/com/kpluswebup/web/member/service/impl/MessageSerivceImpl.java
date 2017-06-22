package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.customer.dao.MessageDAO;
import com.kpluswebup.web.domain.MessageDTO;
import com.kpluswebup.web.member.service.MessageSerivce;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.MessageVO;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class MessageSerivceImpl implements MessageSerivce {

    @Autowired
    private MessageDAO       messageDAO;
    @Autowired
    private CustomerDAO      customerDAO;
    @Autowired
    private CustomerGroupDAO customerGroupDAO;

    @Override
    public List<MessageVO> findMessageByPagination(MessageDTO messageDTO) {
        Long count = messageDAO.findMessageCount(messageDTO);
        messageDTO.doPage(count, messageDTO.getPageNo(), messageDTO.getPageSize());
        List<MessageVO> list = messageDAO.findMessageByPagination(messageDTO);
        return list;
    }

    @Override
    public void messageSend(String[] customerIDs, String[] groupIDs, String title, String content,
                            String currentOperator) {
        if (customerIDs != null) {
            for (String customerID : customerIDs) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setMainID(UUIDUtil.getUUID());
                messageDTO.setSendee(customerID);
                messageDTO.setSender(currentOperator);
                messageDTO.setTitle(title);
                messageDTO.setContent(content);
                messageDTO.setCreator(currentOperator);
                messageDTO.setIsReaded(0);
                messageDTO.setCreator(currentOperator);
                messageDAO.messageSend(messageDTO);
            }
        }
        if (groupIDs != null) {
            for (String groupID : groupIDs) {
                List<CustomerGroupMemberVO> list = customerGroupDAO.findCustomerGroupMemberByGroupID(groupID);
                if (list != null && list.size() > 0) {
                    for (CustomerGroupMemberVO customerGroupMemberVO : list) {
                        CustomerVO customerVO = customerDAO.findCustomerByMainID(customerGroupMemberVO.getCustomerID());
                        MessageDTO messageDTO = new MessageDTO();
                        messageDTO.setMainID(UUIDUtil.getUUID());
                        messageDTO.setSendee(customerVO.getMainID());
                        messageDTO.setSender(currentOperator);
                        messageDTO.setTitle(title);
                        messageDTO.setContent(content);
                        messageDTO.setCreator(currentOperator);
                        messageDTO.setIsReaded(0);
                        messageDTO.setCreator(currentOperator);
                        messageDAO.messageSend(messageDTO);
                    }
                }
            }
        }
    }

    @Override
    public void updateMessageStatus(MessageDTO messageDTO) {
        messageDAO.updateMeaaageByMainID(messageDTO);
    }

}
