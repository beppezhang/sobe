package com.kpluswebup.web.admin.system.dao;

import com.kpluswebup.web.domain.TransConfigDTO;
import com.kpluswebup.web.vo.TransConfigVO;

public interface TransConfigDAO {

	/**
	 * 查找参数信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public TransConfigVO findTransConfig();
	
	/**
	 * 添加参数信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @param transConfigDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertTransConfig(TransConfigDTO transConfigDTO);
	
	/**
	 * 修改参数信息
	 * @date 2014年11月19日
	 * @author transConfigDTO
	 * @param generalDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateTransConfig(TransConfigDTO transConfigDTO);
}
