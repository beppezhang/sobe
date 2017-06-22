package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.admin.system.dao.InterfaceConfigDAO;
import com.kpluswebup.web.customer.dao.CustomerDAO;
import com.kpluswebup.web.customer.dao.CustomerGroupDAO;
import com.kpluswebup.web.customer.dao.EmailDAO;
import com.kpluswebup.web.domain.EmailDTO;
import com.kpluswebup.web.domain.InterfaceConfigParameterDTO;
import com.kpluswebup.web.member.service.EmailSerivce;
import com.kpluswebup.web.vo.CustomerGroupMemberVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpluswebup.web.vo.EmailVO;
import com.kpuswebup.comom.util.UUIDUtil;

@Service
public class EmailSerivceImpl implements EmailSerivce {

    @Autowired
    private EmailDAO           emailDAO;
    @Autowired
    private InterfaceConfigDAO interfaceConfigDAO;
    @Autowired
    private CustomerDAO        customerDAO;
    @Autowired
    private CustomerGroupDAO   customerGroupDAO;

    @Override
    public List<EmailVO> findEmailByPagination(EmailDTO emailDTO) {
        Long count = emailDAO.findEmailCount(emailDTO);
        emailDTO.doPage(count, emailDTO.getPageNo(), emailDTO.getPageSize());
        List<EmailVO> list = emailDAO.findEmailByPagination(emailDTO);
        return list;
    }

    @Override
    public Boolean findEmailByMainID(String mainID) {
        Boolean isSuccess = true;
        try {
            EmailVO emailVO = emailDAO.findEmailByMainID(mainID);
            InterfaceConfigParameterDTO interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("SendName");
            String sendName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("MailName");
            String mailName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("MailPassword");
            String mailPass = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("MailHost");
            String mailHost = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("LoginName");
            String loginName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            String[] mailInfo = new String[] { loginName, mailPass, mailName, mailHost, sendName };
            isSuccess = SendMail.sendEmail(emailVO.getEmail(), emailVO.getTitle(), emailVO.getContent(), mailInfo);
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        }
        if (isSuccess) {
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setStatus(1);
            emailDTO.setMainID(mainID);
            emailDAO.updateEmailStatus(emailDTO);
        }
        return isSuccess;
    }

    @Override
    public void emailSend(String[] customerIDs, String[] groupIDs, String title, String content, String currentOperator) {
        Boolean isSuccess = true;
        InterfaceConfigParameterDTO interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
        interfaceConfigParameterDTO.setInterfaceID("Email");
        interfaceConfigParameterDTO.setParameterID("SendName");
        String sendName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
        interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
        interfaceConfigParameterDTO.setInterfaceID("Email");
        interfaceConfigParameterDTO.setParameterID("MailName");
        String mailName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
        interfaceConfigParameterDTO.setInterfaceID("Email");
        interfaceConfigParameterDTO.setParameterID("MailPassword");
        String mailPass = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
        interfaceConfigParameterDTO.setInterfaceID("Email");
        interfaceConfigParameterDTO.setParameterID("MailHost");
        String mailHost = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
        interfaceConfigParameterDTO.setInterfaceID("Email");
        interfaceConfigParameterDTO.setParameterID("LoginName");
        String loginName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
        String[] mailInfo = new String[] { loginName, mailPass, mailName, mailHost, sendName };
        if (customerIDs != null) {
            for (String customerID : customerIDs) {
                CustomerVO customerVO = customerDAO.findCustomerByMainID(customerID);
                try {
                    isSuccess = SendMail.sendEmail(customerVO.getEmail(), title, content, mailInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                    isSuccess = false;
                }
                EmailDTO emailDTO = new EmailDTO();
                emailDTO.setMainID(UUIDUtil.getUUID());
                emailDTO.setEmail(customerVO.getEmail());
                emailDTO.setCustomerID(customerID);
                emailDTO.setTitle(title);
                emailDTO.setContent(content);
                emailDTO.setCreator(currentOperator);
                if (isSuccess) {
                    emailDTO.setStatus(1);
                } else {
                    emailDTO.setStatus(2);
                }
                emailDAO.emailSend(emailDTO);
            }
        }
        if (groupIDs != null) {
            for (String groupID : groupIDs) {
                List<CustomerGroupMemberVO> list = customerGroupDAO.findCustomerGroupMemberByGroupID(groupID);
                if (list != null && list.size() > 0) {
                    for (CustomerGroupMemberVO customerGroupMemberVO : list) {
                        CustomerVO customerVO = customerDAO.findCustomerByMainID(customerGroupMemberVO.getCustomerID());
                        try {
                            isSuccess = SendMail.sendEmail(customerVO.getEmail(), title, content, mailInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                            isSuccess = false;
                        }
                        EmailDTO emailDTO = new EmailDTO();
                        emailDTO.setMainID(UUIDUtil.getUUID());
                        emailDTO.setEmail(customerVO.getEmail());
                        emailDTO.setCustomerID(customerVO.getMainID());
                        emailDTO.setTitle(title);
                        emailDTO.setContent(content);
                        emailDTO.setCreator(currentOperator);
                        if (isSuccess) {
                            emailDTO.setStatus(1);
                        } else {
                            emailDTO.setStatus(2);
                        }
                        emailDAO.emailSend(emailDTO);
                    }
                }
            }
        }
    }

    @Override
    public void sendEmail(String mainID,String email, String title, String content) {
        Boolean isSuccess = true;
        try {
            InterfaceConfigParameterDTO interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("SendName");
            String sendName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO = new InterfaceConfigParameterDTO();
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("MailName");
            String mailName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("MailPassword");
            String mailPass = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("MailHost");
            String mailHost = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            interfaceConfigParameterDTO.setInterfaceID("Email");
            interfaceConfigParameterDTO.setParameterID("LoginName");
            String loginName = interfaceConfigDAO.findInterfaceParameterValue(interfaceConfigParameterDTO);
            String[] mailInfo = new String[] { loginName, mailPass, mailName, mailHost, sendName };
            isSuccess = SendMail.sendEmail(email, title, content, mailInfo);
            EmailDTO emailDTO = new EmailDTO();
            emailDTO.setMainID(UUIDUtil.getUUID());
            emailDTO.setEmail(email);
            emailDTO.setCustomerID(mainID);
            emailDTO.setTitle(title);
            emailDTO.setContent(content);
            emailDTO.setCreator(mainID);
            if (isSuccess) {
                emailDTO.setStatus(1);
            } else {
                emailDTO.setStatus(2);
            }
        } catch (Exception e) {
            isSuccess = false;
        }
    }
    
}
