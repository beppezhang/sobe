package com.kpluswebup.web.admin.system.service;

import com.kpluswebup.web.domain.GeneralDTO;
import com.kpluswebup.web.vo.GeneralVO;

public interface GeneralService {

	/**
	 * 查找基本信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public GeneralVO findGeneral();
	
	/**
	 * 添加基本信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @param generalDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void addGeneral(GeneralDTO generalDTO);
	
	/**
	 * 修改基本信息
	 * @date 2014年11月19日
	 * @author wanghehua
	 * @param generalDTO
	 * @since JDK 1.6
	 * @Description
	 */
	public void updateGeneral(GeneralDTO generalDTO);
}
