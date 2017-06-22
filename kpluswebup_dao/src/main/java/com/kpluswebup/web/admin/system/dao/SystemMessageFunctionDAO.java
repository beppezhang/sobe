package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.MessageConfigDTO;
import com.kpluswebup.web.domain.MessageTemplateDTO;
import com.kpluswebup.web.domain.SystemMessageFunctionDTO;
import com.kpluswebup.web.vo.MessageConfigVO;
import com.kpluswebup.web.vo.MessageTemplateVO;
import com.kpluswebup.web.vo.SystemMessageFunctionVO;

public interface SystemMessageFunctionDAO {

	/**
	 * 查询消息模板
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<SystemMessageFunctionVO> findSystemMessageFunction();
	
	/**
	 * 根据id查找消息模板
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param mainID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public SystemMessageFunctionVO findSystemMessageFunctionByMainID(String mainID);
	
	/**
	 * 根据模板id查找消息配置
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param functionID
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public MessageConfigVO findMessageConfigByFunctionID(String functionID);
	
	/**
	 * 添加消息配置
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param messageConfigDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertMessageConfig(MessageConfigDTO messageConfigDTO);
	
	/**
	 * 修改消息配置
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param messageConfigDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateMessageConfig(MessageConfigDTO messageConfigDTO);
	
	/**
	 * 修改消息模板
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param systemMessageFunctionDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateSystemMessageFunction(SystemMessageFunctionDTO systemMessageFunctionDTO);
	
	/**
	 * 添加消息内容
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param messageTemplateDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertMessageTemplate(MessageTemplateDTO messageTemplateDTO);
	
	/**
	 * 修改消息内容
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param messageTemplateDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateMessageTemplate(MessageTemplateDTO messageTemplateDTO);
	
	/**
	 * 根据模板id类型查找消息内容
	 * @date 2014年11月29日
	 * @author wanghehua
	 * @param messageTemplateDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public MessageTemplateVO findMessageTemplateByFunctionIDType(MessageTemplateDTO messageTemplateDTO);
}
