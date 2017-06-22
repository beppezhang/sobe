package com.kpluswebup.web.admin.system.service;

import com.kpluswebup.web.domain.TransConfigDTO;
import com.kpluswebup.web.vo.TransConfigVO;

public interface TransConfigService {

	/**
	 * 查找基本信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public TransConfigVO findTransConfig();
	
	/**
	 * 添加基本信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @param transConfigDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void addTransConfig(TransConfigDTO transConfigDTO);
	
	/**
	 * 修改基本信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @param transConfigDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateTransConfig(TransConfigDTO transConfigDTO);
}
