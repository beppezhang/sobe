package com.kpluswebup.web.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.ItemDTO;
import com.kpluswebup.web.domain.ProductDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpluswebup.web.vo.SupplierItemVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.Constant;
import com.kpuswebup.comom.util.StringUtil;

public interface SupplierItemService {
	/**
	 * 
	 * @date 2015年5月14日
	 * @author moo
	 * @param supplierItemDTO
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 添加供应商商品
	 */
	public int addOrEditSupplierItem(SupplierItemDTO supplierItemDTO);
	
	/**
	 * 
	 * @date 2015年5月15日
	 * @author moo
	 * @param supplierItemDTO
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description 
	 */
	public int deleteSupplierItem(SupplierItemDTO supplierItemDTO);
	
	 public Integer updateSupplierItemStatus(String mainID, String status) ;
	
	/**
	 * 
	 * @date 2015年5月18日
	 * @author moo
	 * @param mainID
	 * @param type -1全部 0未审核1审核通过2审核失败
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description
	 */
	public int findItemBySupplierAdnType(String mainID, int type);
	
	
	/**
	 * 供应商 商品列表
	 * @param supplierItemDTO
	 * @return
	 */
	public List<SupplierItemVO> findSupplierItem(SupplierItemDTO supplierItemDTO);

	/**
	 * 
	 * @date 2015年6月2日
	 * @author moo
	 * @param supplierID
	 * @return
	 * @return List<ItemVO>
	 * @since JDK 1.6
	 * @Description 销量前三商品
	 */
	public List<ItemVO> findTopThreeSaleItem(String supplierID);

	/**
	 * 
	 * @date 2015年6月8日
	 * @author moo
	 * @param mainID
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSupplierItemStatusWhichStockLess20(String mainID);

	/**
	 * 
	 * @date 2015年6月17日
	 * @author moo
	 * @param ids
	 * @return
	 * @return int
	 * @since JDK 1.6
	 * @Description
	 */
	public int batchConfirmSupplierItem(String ids);
	
	/**
	 * 删除供应商商品
	 * @date 2015年6月18日
	 * @author wanghehua
	 * @param mainID
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteSupplierItemBymainID(String mainID);
	
	public SupplierItemVO findSupplierItemByItemIDAndSupplierID(SupplierItemDTO supplierItemDTO);
	
	/**
     * 导出供应商商品
     * 
     * @date 2014年11月26日
     * @author wanghehua
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description
     */
    public void exportSupplierItem(HttpServletResponse response, SupplierItemDTO supplierItemDTO);

    /**************************Tparts******************/
}
