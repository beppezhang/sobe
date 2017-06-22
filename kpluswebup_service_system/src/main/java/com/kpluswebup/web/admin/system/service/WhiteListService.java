package com.kpluswebup.web.admin.system.service;

import java.util.List;

import com.kpluswebup.web.domain.WhiteListDTO;
import com.kpluswebup.web.vo.WhiteListVO;

public interface WhiteListService {

	/**
	 * 查询IP白名单
	 * 
	 * @date 2014年11月24日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<WhiteListVO> findWhileList();

	/**
	 * 添加白名单
	 * 
	 * @date 2014年11月24日
	 * @author wanghehua
	 * @param whiteListVO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public void addWhiteIP(WhiteListDTO whiteListDTO);
	
	/**
	 * 删除白名单
	 * @date 2014年11月24日
	 * @author wanghehua
	 * @param id
	 * @since JDK 1.6
	 * @Description
	 */
	public Boolean deleteWhiteIP(Long id);
}
