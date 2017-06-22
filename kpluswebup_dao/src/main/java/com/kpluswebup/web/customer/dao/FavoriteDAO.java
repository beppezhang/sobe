package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.FavoriteDTO;
import com.kpluswebup.web.vo.FavoriteVO;

public interface FavoriteDAO {

    public Integer deleteByPrimaryKey(Long id);

    public Integer insert(FavoriteDTO record);

    public Integer insertSelective(FavoriteDTO record);

    FavoriteDTO selectByPrimaryKey(Long id);

    public Integer updateByPrimaryKeySelective(FavoriteDTO record);

    public Integer updateByPrimaryKey(FavoriteDTO record);

    /**
     * 我的收藏总条数
     * 
     * @date 2014年12月22日
     * @author yuanyuan
     * @param favoriteDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findFavoriteCount(FavoriteDTO favoriteDTO);

    /**
     * 我的收藏
     * 
     * @date 2014年12月22日
     * @author yuanyuan
     * @param favoriteDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<FavoriteVO> findFavoriteByPagination(FavoriteDTO favoriteDTO);

    /**
     * 根据商品id或者产品id查询收藏条数
     * 
     * @date 2014年12月30日
     * @author liudanqi
     * @param favoriteDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCountByitemIdOrProductId(FavoriteDTO favoriteDTO);

    /**
     * 添加收藏
     * 
     * @date 2014年12月30日
     * @author liudanqi
     * @param favoriteDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertFavorite(FavoriteDTO favoriteDTO);
    
    /**
     * 查询指定用户的收藏
     * @date 2015年1月4日
     * @author liudanqi
     * @param favoriteDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public FavoriteVO findFavoriteByCustomer(FavoriteDTO favoriteDTO);


    /**
     * 根据用户、商品查询是否收藏过此商品
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param favoriteDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public FavoriteVO findFavoriteByCustomerItem(FavoriteDTO favoriteDTO);
    
    /**
     * 查询我的收藏
     * @date 2015年1月5日
     * @author liudanqi
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<FavoriteVO> findFavoritesByCustomer(String customerID);
    
    /**
     * 取消收藏
     * @date 2015年1月9日
     * @author wanghehua
     * @param id
     * @since JDK 1.6
     * @Description
     */
    public void updateFavoriteByID(Long id);
}

