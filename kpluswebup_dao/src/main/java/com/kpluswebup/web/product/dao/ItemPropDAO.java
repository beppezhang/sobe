package com.kpluswebup.web.product.dao;

import java.util.List;

import com.kpluswebup.web.domain.ItemPropDTO;
import com.kpluswebup.web.domain.ItemPropValueDTO;
import com.kpluswebup.web.vo.ItemPropVO;
import com.kpluswebup.web.vo.ItemPropValueVO;

public interface ItemPropDAO {

    /**
     * 删除一条销售规格
     * 
     * @date 2014年10月31日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteItemPropByMainID(String mainID);

    /**
     * 插入一条销售规格
     * 
     * @date 2014年10月31日
     * @author zhuhp
     * @param itemPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer insertItemProp(ItemPropDTO itemPropDTO);

    /**
     * 修改一条销售规格记录
     * 
     * @date 2014年10月31日
     * @author zhuhp
     * @param itemPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateItemProp(ItemPropDTO itemPropDTO);

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param itemPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemPropVO> findItemPropByPagination(ItemPropDTO itemPropDTO);

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param itemPropDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findItemPropCount(ItemPropDTO itemPropDTO);

    /**
     * @date 2014年11月1日
     * @author zhuhp
     * @param itemPropValueDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemPropValueVO> findAllItemPropValueByItemPropMianID(String itemPropID);

    /**
     * @date 2014年11月1日
     * @author zhuhp
     * @param itemPropValueDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertItemPropValue(ItemPropValueDTO itemPropValueDTO);

    /**
     * @date 2014年11月1日
     * @author zhuhp
     * @param mainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteItemtPropValeByMainID(String mainID);

    /**
     * @date 2014年11月1日
     * @author zhuhp
     * @param itemPropMainID
     * @since JDK 1.6
     * @Description
     */
    public void deleteItemtPropValeByItemPropMainID(String itemPropMainID);

}
