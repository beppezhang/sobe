package com.kpluswebup.web.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kpluswebup.web.domain.CouponBatchDTO;
import com.kpluswebup.web.domain.CouponDTO;
import com.kpluswebup.web.vo.CouponBatchVO;
import com.kpluswebup.web.vo.CouponPromotionVO;
import com.kpluswebup.web.vo.CouponVO;

public interface CouponBatchService {

    public List<CouponPromotionVO> findAllCouponBatch();
    
    /**
     * 分页查询批量代金券
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponBatchDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CouponBatchVO> findCouponBatchByPagination(CouponBatchDTO couponBatchDTO);
    
    /**
     * 添加批量优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponBatchDTO
     * @since JDK 1.6
     * @Description
     */
    public void addCouponBatch(CouponBatchDTO couponBatchDTO);
    
    /**
     * 根据id查找批量优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public CouponBatchVO findCouponBatchByMainId(String mainID);
    
    /**
     * 修改批量优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponBatchDTO
     * @since JDK 1.6
     * @Description
     */
    public void updateCouponBatch(CouponBatchDTO couponBatchDTO);
    
    /**
     * 删除批量优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean deleteCouponBatch(String mainIds);
    
    /**
     * 分页查询优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CouponVO> findCouponByBatchID(CouponDTO couponDTO);
    
    /**
     * 优惠券导出excel
     * @date 2014年11月22日
     * @author wanghehua
     * @param response
     * @param batchID
     * @since JDK 1.6
     * @Description
     */
    public void exportCoupon(HttpServletResponse response,String batchID);
    
    /**
     * 改变优惠券状态
     * @date 2014年11月22日
     * @author wanghehua
     * @param mainID
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean changeCouponStatus(String mainID,Integer status);
    
    /**
     * 改变批量优惠券下的优惠券状态
     * @date 2014年11月24日
     * @author wanghehua
     * @param mainID
     * @param status
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Boolean changeCouponBatchStatus(String mainID,Integer status);
    
}
