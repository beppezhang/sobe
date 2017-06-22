package com.kpluswebup.web.controller.order;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CarrierNoteDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.supplier.service.CarrierNoteService;
import com.kpluswebup.web.vo.CarrierNotePrintGroupVO;
import com.kpluswebup.web.vo.CarrierNoteVO;
import com.kpluswebup.web.vo.ItemVO;
import com.kpuswebup.comom.util.StringUtil;
/**
 * 
 * @author moo
 *
 */
@Controller
@RequestMapping("/admin/order")
public class CarrierNoteController extends BaseController{
	
	@Autowired
	private CarrierNoteService carrierNoteService;
	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param pageNo
	 * @param storeStatus
	 * @param supplierName
	 * @param mainID
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="carrierNoteList", method=RequestMethod.GET)
	public ModelAndView getCarrierNoteList(String pageNo, String storeStatus, String supplierName, String noteMainID){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/order/carriernote_list");
		CarrierNoteDTO carrierNoteDTO = new CarrierNoteDTO();
		if(StringUtil.isNotEmpty(pageNo)){
			carrierNoteDTO.setPageNo(Long.parseLong(pageNo));
		}
		if(StringUtil.isNotEmpty(storeStatus)){
			carrierNoteDTO.setStoreStatus(Integer.parseInt(storeStatus));
		}
		if(StringUtil.isNotEmpty(supplierName)){
			carrierNoteDTO.setSupplierName(supplierName);
		}
		if(StringUtil.isNotEmpty(noteMainID)){
			carrierNoteDTO.setMainID(noteMainID);
		}
		carrierNoteDTO.setPageSize(15L);
		List<CarrierNoteVO> carrierNoteVOList = carrierNoteService.findCarrierNoteByPagination(carrierNoteDTO);
		model.addObject("carrierNoteVOList", carrierNoteVOList);
		model.addObject("carrierNoteDTO", carrierNoteDTO);
		return model;
	}
	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteID
	 * @return
	 * @return ModelAndView
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="carrierNoteDetail", method=RequestMethod.GET)
	public ModelAndView getCarrierNote(String carrierNoteID){
		ModelAndView model = this.newModelAndView();
		model.setViewName("screen/order/carriernote_detail");
		CarrierNotePrintGroupVO group = carrierNoteService.findCarrierNoteCarrierNoteID(carrierNoteID);
		model.addObject("group", group);
		return model;
	}
	/**
	 * 
	 * @date 2015年5月25日
	 * @author moo
	 * @param carrierNoteID
	 * @param response
	 * @throws Exception
	 * @return void
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping(value="carrierNoteReceived", method=RequestMethod.POST)
	public void updateCarrierNoteStoreStatusReceived(String carrierNoteID, HttpServletResponse response) throws Exception{
		
		int result = carrierNoteService.updateCarrierNoteStoreStatusReceived(carrierNoteID);
		if(result != 0)
			throw new Exception("carrierNoteReceived,result:"+result);
		else{
			PrintWriter out = response.getWriter();
			out.print(0);
			out.close();
		}
	}
	

}
