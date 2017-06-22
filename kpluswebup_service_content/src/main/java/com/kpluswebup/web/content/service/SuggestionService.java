package com.kpluswebup.web.content.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kpluswebup.web.domain.SuggestionDTO;
import com.kpluswebup.web.vo.SuggestionVO;

@Service
public interface SuggestionService {
	/**
     * 查询意见反馈
     * 
     * @date 2015年5月26日
     * @author wanghehua
     * @param suggestionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
	public List<SuggestionVO> findsuggestionByPagination(SuggestionDTO suggestionDTO);

	/**
     * 删除意见反馈
     * 
     * @date 2015年5月26日
     * @author wanghehua
     * @param mainIDs
     * @return
     * @since JDK 1.6
     * @Description
     */
	public Boolean deleteSuggestionByMainID(String mainIDs);

	/**
     * 通过ID查找信息
     * 
     * @date 2015年5月26日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
	public SuggestionVO findSuggestionByID(Long id);

	
	/**
     * 编辑意见反馈
     * 
     * @date 2015年5月26日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
	public void updatesuggestionByMainID(SuggestionDTO suggestionDTO);

	/**
     * 添加意见反馈
     * 
     * @date 2015年5月26日
     * @author wanghehua
     * @param suggestionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
	public void insterSuggestion(SuggestionDTO suggestionDTO);

}
