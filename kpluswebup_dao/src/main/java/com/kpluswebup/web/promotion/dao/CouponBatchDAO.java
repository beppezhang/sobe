package com.kpluswebup.web.promotion.dao;

import java.util.List;

import com.kpluswebup.web.domain.CouponBatchDTO;
import com.kpluswebup.web.domain.CouponDTO;
import com.kpluswebup.web.vo.CouponBatchVO;
import com.kpluswebup.web.vo.CouponPromotionVO;
import com.kpluswebup.web.vo.CouponVO;


public interface CouponBatchDAO {
    
    /**
     * 获取所有优惠劵批次
     * @date 2014年11月11日
     * @author lupeng
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public List<CouponPromotionVO> findAllCouponBatch();
    
    /**
     * 根据mainID查询优惠券批次
     * @date 2014年11月12日
     * @author lupeng
     * @param mainId
     * @return
     * @since JDK 1.6
     * @Description  
     */
    public CouponPromotionVO findCouponBatchByMainID(String mainId);
    
    /**
     * 分页查询批量优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponBatchDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CouponBatchVO> findCouponBatchByPagination(CouponBatchDTO couponBatchDTO);
    
    /**
     * 查询总条数
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponBatchDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCouponBatchCount(CouponBatchDTO couponBatchDTO);
    
    /**
     * 添加批量优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponBatchDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertCouponBatch(CouponBatchDTO couponBatchDTO);
    
    /**
     * 添加批次下优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponDTO
     * @since JDK 1.6
     * @Description
     */
    public void insertCoupon(CouponDTO couponDTO);
    
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
    public Integer deleteCouponBatch(String mainID);
    
    /**
     * 根据批量id删除优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @param batchID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteCoupon(String batchID);
    
    /**
     * 分页查询优惠券
     * @date 2014年11月21日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CouponVO> findCouponByBatchID(CouponDTO couponDTO);
    
    /**
     * 查询总条数
     * @date 2014年11月21日
     * @author wanghehua
     * @param couponDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findCouponCount(CouponDTO couponDTO);
    
    /**
     * 根据批量id查找所有优惠券
     * @date 2014年11月22日
     * @author wanghehua
     * @param batchID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<CouponVO> findCouponByCouponBatchID(String batchID);
    
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
    public Integer updateCouponStatus(CouponDTO couponDTO);
    
    /**
     * 改变批量优惠券下的优惠券状态
     * @date 2014年11月24日
     * @author wanghehua
     * @param couponDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateCouponBatchStatus(CouponDTO couponDTO);
    
    /**
     * 修改批量优惠券状态
     * @date 2014年11月24日
     * @author wanghehua
     * @param couponBatchDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer updateCouponBatchSta(CouponBatchDTO couponBatchDTO);
    
}