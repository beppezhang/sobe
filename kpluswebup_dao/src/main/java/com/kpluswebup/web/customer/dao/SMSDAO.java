package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.SMSDTO;
import com.kpluswebup.web.vo.SmsVO;

public interface SMSDAO {

    /*
     * public Integer deleteByPrimaryKey(String mainid); public Integer insert(SMSDTO record); public Integer
     * insertSelective(SMSDTO record); SMSDTO selectByPrimaryKey(String mainid); public Integer
     * updateByPrimaryKeySelective(SMSDTO record);
     */

    public Integer updateByPrimaryKey(SMSDTO record);

    /**
     * 分页查询短信
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param smsDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SmsVO> findSmsByPagination(SMSDTO smsdto);

    /**
     * 查询总条数
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param smsDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findSmsCount(SMSDTO smsdto);

    /**
     * 发送短信
     * 
     * @date 2014年12月29日
     * @author wanghehua
     * @param smsdto
     * @since JDK 1.6
     * @Description
     */
    public void smsSend(SMSDTO smsdto);

    /**
     * 根据id查询短信
     * 
     * @date 2015年1月8日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SmsVO findSmsByMaminID(String mainID);

    /**
     * 修改短信发送状态
     * 
     * @date 2015年1月9日
     * @author wanghehua
     * @param smsdto
     * @since JDK 1.6
     * @Description
     */
    public void updateSmsByMainID(SMSDTO smsdto);
}
