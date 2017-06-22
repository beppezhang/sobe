package com.kpluswebup.web.content.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kpluswebup.web.content.dao.SuggestionDAO;
import com.kpluswebup.web.content.service.SuggestionService;
import com.kpluswebup.web.domain.SuggestionDTO;
import com.kpluswebup.web.vo.SuggestionVO;
@Service
public class SuggestionServiceImpl implements SuggestionService{
	
	@Autowired
	private SuggestionDAO suggestionDAO;
	
	@Override
	public List<SuggestionVO> findsuggestionByPagination(SuggestionDTO suggestionDTO) {
		
		Long count = suggestionDAO.findSuggestionCount(suggestionDTO);
		suggestionDTO.doPage(count, suggestionDTO.getPageNo(), suggestionDTO.getPageSize());
        List<SuggestionVO> list = suggestionDAO.findSuggestionByPagination(suggestionDTO);
        return list;
		
	}

	@Override
	public Boolean deleteSuggestionByMainID(String mainIDs) {
		try {
            String[] ids = mainIDs.split(",");
            for (String mainID : ids) {
            	suggestionDAO.deleteSuggestionByMainID(mainID);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	@Override
	public SuggestionVO findSuggestionByID(Long id) {
		return suggestionDAO.findSuggestionByID(id);
	}

	@Override
	public void updatesuggestionByMainID(SuggestionDTO suggestionDTO) {
		
		suggestionDAO.updatesuggestionByMainID(suggestionDTO);
	}

	@Override
	public void insterSuggestion(SuggestionDTO suggestionDTO) {
		
		suggestionDAO.instersuggestion(suggestionDTO);
	}

}
