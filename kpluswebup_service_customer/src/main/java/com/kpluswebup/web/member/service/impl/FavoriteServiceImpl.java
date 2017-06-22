package com.kpluswebup.web.member.service.impl;

import static org.springframework.util.Assert.notNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.customer.dao.FavoriteDAO;
import com.kpluswebup.web.domain.FavoriteDTO;
import com.kpluswebup.web.member.service.FavoriteService;
import com.kpluswebup.web.vo.FavoriteVO;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteDAO favoriteDAO;
    
    public List<FavoriteVO> findFavoriteByPagination(FavoriteDTO favoriteDTO) {
        Long count = favoriteDAO.findFavoriteCount(favoriteDTO);
        favoriteDTO.doPage(count,favoriteDTO.getPageNo(),favoriteDTO.getPageSize());
        List<FavoriteVO> list = favoriteDAO.findFavoriteByPagination(favoriteDTO);
        return list;
    }

    @Override
    public Long findCountByitemIdOrProductId(FavoriteDTO favoriteDTO) {
        return favoriteDAO.findCountByitemIdOrProductId(favoriteDTO);
    }

    @Override
    public Long findFavoriteCount(FavoriteDTO favoriteDTO) {
        return favoriteDAO.findFavoriteCount(favoriteDTO);
    }

    @Override
    public void insertFavorite(FavoriteDTO favoriteDTO) {
        favoriteDAO.insertFavorite(favoriteDTO);
    }

    @Override
    public boolean isFavorite(String customerID, String productID) {
        notNull(customerID, "customerID is null");
        notNull(productID, "productID is null");
        FavoriteDTO dto = new FavoriteDTO();
        dto.setCustomerID(customerID);
        dto.setProductID(productID);
        FavoriteVO favorite = favoriteDAO.findFavoriteByCustomer(dto);
        if(favorite != null){
            return true;
        }
        return false;
    }

    @Override
    public FavoriteVO findFavoriteByCustomerItem(FavoriteDTO favoriteDTO) {
        return favoriteDAO.findFavoriteByCustomerItem(favoriteDTO);
    }

    @Override
    public List<FavoriteVO> findFavoritesByCustomer(String customerID) {
        return favoriteDAO.findFavoritesByCustomer(customerID);
    }

    @Override
    public void updateFavoriteByID(String favoriteIds) {
        String [] ids=favoriteIds.split(",");
        for(String id:ids){
            favoriteDAO.updateFavoriteByID(Long.valueOf(id));
        }
    }

}
