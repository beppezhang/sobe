package com.kpluswebup.web.member.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.CustomerDeliveryAddressDAO;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;

@Service
public class CustomerAddressSerivceImpl implements CustomerAddressService {

    private static final Logger        LOGGER = LogManager.getLogger(CustomerAddressSerivceImpl.class);

    @Autowired
    private CustomerDeliveryAddressDAO customerDeliveryAddressDAO;

    @Override
    public List<CustomerDeliveryAddressVO> findAddressByCustomerID(String customerID, Integer type) {
        CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
        customerDeliveryAddressDTO.setCustomerID(customerID);
        customerDeliveryAddressDTO.setType(0);
        if (type == 0) {
            customerDeliveryAddressDTO.setStatus(1);
        }
        List<CustomerDeliveryAddressVO> list = customerDeliveryAddressDAO.findAddressByCustomerID(customerDeliveryAddressDTO);
        return list;
    }

    @Override
    public Boolean deleteAddressByPrimaryKey(Long id) {
        try {
            customerDeliveryAddressDAO.deleteByPrimaryKey(id);
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public CustomerDeliveryAddressVO findDefaultAddressByCustomerID(String customerID, Integer type) {
        CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
        customerDeliveryAddressDTO.setCustomerID(customerID);
        customerDeliveryAddressDTO.setType(type);
        return customerDeliveryAddressDAO.findDefaultAddress(customerDeliveryAddressDTO);
    }

    @Override
    public void editDefaultAddressByID(Long id) {
        customerDeliveryAddressDAO.updateDefaultAddress(id);
    }

    @Override
    public void editIsDefaultAddressByID(Long id) {
        customerDeliveryAddressDAO.updateIsDefaultAddress(id);
    }

    @Override
    public CustomerDeliveryAddressVO findAddressByID(Long id) {
        return customerDeliveryAddressDAO.findAddressByID(id);
    }

    @Override
    public CustomerDeliveryAddressVO findValidAddressByID(Long id) {
        return customerDeliveryAddressDAO.findValidAddressByID(id);
    }

    @Override
    public void addAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO) {
        customerDeliveryAddressDAO.insertAddress(customerDeliveryAddressDTO);
    }

    @Override
    public void editAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO) {
        customerDeliveryAddressDAO.updateAddress(customerDeliveryAddressDTO);
    }

    @Override
    public List<CustomerDeliveryAddressVO> findDeliveryAddressByPagination(CustomerDeliveryAddressDTO customerDeliveryAddressDTO) {
        Long count = customerDeliveryAddressDAO.findDeliveryAddressCount(customerDeliveryAddressDTO);
        customerDeliveryAddressDTO.doPage(count, customerDeliveryAddressDTO.getPageNo(),
                                          customerDeliveryAddressDTO.getPageSize());
        List<CustomerDeliveryAddressVO> list = customerDeliveryAddressDAO.findDeliveryAddressByPagination(customerDeliveryAddressDTO);
        return list;
    }

    @Override
    public void changeDeliveryAddress(CustomerDeliveryAddressDTO customerDeliveryAddressDTO) {
        customerDeliveryAddressDAO.changeDeliveryAddress(customerDeliveryAddressDTO);
    }

    @Override
    public void updateAddressNotDefaultByCustomerMainID(String mainID) {
        customerDeliveryAddressDAO.updateAddressNotDefaultByCustomerMainID(mainID);

    }

    @Override
    public List<CustomerDeliveryAddressVO> findPassAddressByCustomerID(String customerID, int type) {
        CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
        customerDeliveryAddressDTO.setCustomerID(customerID);
        customerDeliveryAddressDTO.setType(type);
        customerDeliveryAddressDTO.setStatus(1);
        List<CustomerDeliveryAddressVO> list = customerDeliveryAddressDAO.findAddressByCustomerID(customerDeliveryAddressDTO);
        return list;
    }

}
