package com.kpluswebup.web.customer.dao;

import java.util.List;

import com.kpluswebup.web.domain.EmailDTO;
import com.kpluswebup.web.vo.EmailVO;

public interface EmailDAO {

    public Integer deleteByPrimaryKey(String mainid);

    public Integer insert(EmailDTO record);

    public Integer insertSelective(EmailDTO record);

    EmailDTO selectByPrimaryKey(String mainid);

    public Integer updateByPrimaryKeySelective(EmailDTO record);

    public Integer updateByPrimaryKey(EmailDTO record);

    /**
     * 分页查询邮件
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param emailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<EmailVO> findEmailByPagination(EmailDTO emailDTO);

    /**
     * 查询总条数
     * 
     * @date 2014年11月15日
     * @author wanghehua
     * @param emailDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findEmailCount(EmailDTO emailDTO);

    /**
     * 根据id查找邮件
     * 
     * @date 2014年11月17日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public EmailVO findEmailByMainID(String mainID);

    /**
     * 修改邮件发送状态
     * 
     * @date 2014年11月17日
     * @author wanghehua
     * @param status
     * @since JDK 1.6
     * @Description
     */
    public void updateEmailStatus(EmailDTO emailDTO);

    /**
     * 发送邮件
     * 
     * @date 2014年12月29日
     * @author wanghehua
     * @param emailDTO
     * @since JDK 1.6
     * @Description
     */
    public void emailSend(EmailDTO emailDTO);
}
