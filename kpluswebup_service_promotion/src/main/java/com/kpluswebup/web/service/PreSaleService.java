package com.kpluswebup.web.service;

import java.util.List;

import com.kpluswebup.web.domain.PreSaleDTO;
import com.kpluswebup.web.vo.PreSaleVO;

public interface PreSaleService {

    /**
     * @date 2014年12月22日
     * @author zhuhp
     * @param preSaleDTO
     * @return
     * @since JDK 1.6
     * @Description
     */
    public List<PreSaleVO> findPreSaleList(PreSaleDTO preSaleDTO);

    public void addPreSale(PreSaleVO preSale);

    public Boolean deletePreSale(String mainIds);

    public PreSaleVO findPreSale(String mainID);

    public void updatePreSale(PreSaleVO preSale);

}
