package com.kpluswebup.web.member.service;

import java.util.List;

import com.kpluswebup.web.domain.SMSDTO;
import com.kpluswebup.web.vo.SmsVO;

public interface SmsSerivce {

    /**
     * 分页查找短信
     * 
     * @date 2014年11月14日
     * @author wanghehua
     * @param smsdto
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SmsVO> findSmsByPagination(SMSDTO smsdto);

    /**
     * 发送短信
     * 
     * @date 2014年12月29日
     * @author wanghehua
     * @param customerIDs
     * @param groupIDs
     * @param title
     * @param content
     * @param currentOperator
     * @since JDK 1.6
     * @Description
     */
    public void smsSend(String[] customerIDs, String[] groupIDs, String title, String content, String currentOperator);

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
