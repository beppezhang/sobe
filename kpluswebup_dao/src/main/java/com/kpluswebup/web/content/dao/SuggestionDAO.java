package com.kpluswebup.web.content.dao;

import java.util.List;

import com.kpluswebup.web.domain.SuggestionDTO;
import com.kpluswebup.web.vo.SuggestionVO;

public interface SuggestionDAO {

    /**
     * 查询总条数
     * @date 2015年5月26日
     * @author wanghehua
     * @param suggestionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Long findSuggestionCount(SuggestionDTO suggestionDTO);

    /**
     * 查询意见反馈
     * @date 2015年5月26日
     * @author wanghehua
     * @param suggestionDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<SuggestionVO> findSuggestionByPagination(SuggestionDTO suggestionDTO);

    /**
     * 删除意见反馈
     * @date 2015年5月26日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public Integer deleteSuggestionByMainID(String mainID);

    /**
     * 通过id查找意见反馈
     * @date 2015年5月26日
     * @author wanghehua
     * @param mainID
     * @return
     * @since JDK 1.6
     * @Description
     */
    public SuggestionVO findSuggestionByID(Long id);

    /**
     * 修改意见反馈
     * @date 2015年5月26日
     * @author wanghehua
     * @param suggestionDTO
     * @since JDK 1.6
     * @Description
     */
    public void updatesuggestionByMainID(SuggestionDTO suggestionDTO);

   /**
    * 添加意见反馈
    * @date 2015年5月26日
    * @author wanghehua
    * @param suggestionDTO
    * @since JDK 1.6
    * @Description
    */
    public void instersuggestion(SuggestionDTO suggestionDTO);

}
