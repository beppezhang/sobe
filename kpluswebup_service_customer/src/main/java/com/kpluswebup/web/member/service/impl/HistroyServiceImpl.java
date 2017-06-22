package com.kpluswebup.web.member.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.HistroyDAO;
import com.kpluswebup.web.domain.HistroyDTO;
import com.kpluswebup.web.member.service.HistroyService;
import com.kpluswebup.web.vo.HistroyVO;

@Service
public class HistroyServiceImpl implements HistroyService {

    @Autowired
    private HistroyDAO histroyDAO;
    
    public List<HistroyVO> findHistroyByPagination(HistroyDTO histroyDTO) {
        Long count = histroyDAO.findHistroyCount(histroyDTO);
        histroyDTO.doPage(count,histroyDTO.getPageNo(),histroyDTO.getPageSize());
        List<HistroyVO> list = histroyDAO.findHistroyByPagination(histroyDTO);
        return list;
    }

    @Override
    public Long findCountByitemIdOrProductId(HistroyDTO histroyDTO) {
        return histroyDAO.findCountByitemIdOrProductId(histroyDTO);
    }

    @Override
    public Long findHistroyCount(HistroyDTO histroyDTO) {
        return histroyDAO.findHistroyCount(histroyDTO);
    }

    @Override
    public void insertHistroy(HistroyDTO histroyDTO) {
    	histroyDAO.insertHistroy(histroyDTO);
    }

    @Override
    public boolean isHistroy(String customerID, String productID) {
        notNull(customerID, "customerID is null");
        notNull(productID, "productID is null");
        HistroyDTO dto = new HistroyDTO();
        dto.setCustomerID(customerID);
        dto.setProductID(productID);
        HistroyVO histroy = histroyDAO.findHistroyByCustomer(dto);
        if(histroy != null){
            return true;
        }
        return false;
    }

	@Override
	public HistroyVO findHistroyByCustomerItem(HistroyDTO histroyDTO) {
		return histroyDAO.findHistroyByCustomerItem(histroyDTO);
	}

	@Override
	public List<HistroyVO> findHistroysByCustomer(String customerID) {
		return histroyDAO.findHistroysByCustomer(customerID);
	}

	@Override
	public void updateHistroyByID(String histroyIds) {
		String [] ids=histroyIds.split(",");
		for(String id:ids){
			histroyDAO.updateHistroyByID(Long.valueOf(id));
		}
		
	}

}
