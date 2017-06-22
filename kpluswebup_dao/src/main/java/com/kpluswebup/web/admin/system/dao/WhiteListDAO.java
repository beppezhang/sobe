package com.kpluswebup.web.admin.system.dao;

import java.util.List;

import com.kpluswebup.web.domain.WhiteListDTO;
import com.kpluswebup.web.vo.WhiteListVO;

public interface WhiteListDAO {

	/**
	 * 查询ip白名单
	 * @date 2014年11月24日
	 * @author wanghehua
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public List<WhiteListVO> findWhiteList();
	
	/**
	 * 添加白名单
	 * @date 2014年11月24日
	 * @author wanghehua
	 * @param whiteListDTO
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	public void insertWhiteIP(WhiteListDTO whiteListDTO);
	
	/**
	 * 删除白名单
	 * @date 2014年11月24日
	 * @author wanghehua
	 * @param id
	 * @since JDK 1.6
	 * @Description
	 */
	public void deleteWhiteIP(Long id);
}
