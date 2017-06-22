package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.ItemPropDTO;
import com.kpluswebup.web.vo.ItemPropVO;
import com.kpluswebup.web.vo.ItemPropValueVO;

public interface ItemPropService {

    /**
     * 分页查询规格
     * 
     * @date 2014年10月31日
     * @author zhuhp
     * @param pageNo
     * @param pageSize
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemPropVO> findItemPropByPagination(ItemPropDTO itemPropDTO);

    /**
     * @date 2014年10月31日
     * @author zhuhp
     * @param itemPropDTO
     * @since JDK 1.6
     * @Description
     */
    public void addItemProp(ItemPropDTO itemPropDTO, String itemPropValue);

    /**
     * 删除一个属性值
     * 
     * @date 2014年11月1日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteItemtPropVale(String mainID);

    /**
     * @date 2014年11月1日
     * @author zhuhp
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteItemPropByMainID(String mainID);

    /**
     * @date 2014年12月5日
     * @author zhuhp
     * @param propValue
     * @param itemPropMainID
     * @since JDK 1.6
     * @Description
     */
    public Boolean insertItemPropValue(String propValue, String itemPropMainID,String currentOperator);
    
    /**
     * 
     * @date 2015年5月27日
     * @author wanghehua
     * @param itemPropID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<ItemPropValueVO> findAllItemPropValueByItemPropMianID(String itemPropID);
}
