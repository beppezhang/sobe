package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.HistroyDTO;
import com.kpluswebup.web.vo.HistroyVO;

public interface HistroyService {

    /**
     * 我的足迹
     * 
     * @date 2014年12月22日
     * @author yuanyuan
     * @param HistroyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<HistroyVO> findHistroyByPagination(HistroyDTO histroyDTO);

    /**
     * 根据商品id或者产品id查询足迹条数
     * 
     * @date 2014年12月30日
     * @author liudanqi
     * @param HistroyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCountByitemIdOrProductId(HistroyDTO histroyDTO);

    /**
     * 根据条件查询我的足迹
     * 
     * @date 2014年12月22日
     * @author liudanqi
     * @param HistroyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findHistroyCount(HistroyDTO histroyDTO);

    /**
     * 添加足迹
     * 
     * @date 2014年12月30日
     * @author liudanqi
     * @param HistroyDTO
     * @since JDK 1.6
     * @Description
     */

    public void insertHistroy(HistroyDTO histroyDTO);

    /**
     * 判断是否足迹
     * 
     * @date 2015年1月4日
     * @author liudanqi
     * @param customerID
     * @param productID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public boolean isHistroy(String customerID, String productID);

    /**
     * 根据用户、商品查询是否足迹过此商品
     * 
     * @date 2015年1月4日
     * @author wanghehua
     * @param HistroyDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public HistroyVO findHistroyByCustomerItem(HistroyDTO histroyDTO);

    /**
     * 查询我的足迹
     * 
     * @date 2015年1月5日
     * @author liudanqi
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<HistroyVO> findHistroysByCustomer(String customerID);

    /**
     * 取消足迹
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param id
     * @since JDK 1.6
     * @Description
     */
    public void updateHistroyByID(String histroyIds);
}
