package com.kpluswebup.web.controller.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.admin.system.service.ShippingAddressService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.ShippingAddressDTO;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.ShippingAddressVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/system")
public class ShippingAddressController extends BaseController {

	@Autowired
	private ShippingAddressService shippingAddressService;
	@Autowired
	private AreaService areaService;

	@RequestMapping("shippingAddressList")
	public ModelAndView shippingAddressList(
			ShippingAddressDTO shippingAddressDTO) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("screen/system/shippingaddress_list");
		List<ShippingAddressVO> list = shippingAddressService
				.findShippingAddressByPagionation(shippingAddressDTO);
		modelAndView.addObject("shippingaddressList", list);
		modelAndView.addObject("shippingAddressDTO", shippingAddressDTO);
		return modelAndView;
	}

	@RequestMapping("addShippingAddressPage")
	public ModelAndView addShippingAddressPage() {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/shippingaddress_add");
		List<AreaVO> provinceList = areaService.getAllProvince();
		modelAndView.addObject("provinceList", provinceList);
		if (provinceList != null && provinceList.size() > 0) {
			List<AreaVO> cityList = areaService.getAreaByParentID(provinceList
					.get(0).getMainID());
			modelAndView.addObject("cityList", cityList);
			if (cityList != null && cityList.size() > 0) {
				List<AreaVO> districtList = areaService
						.getAreaByParentID(cityList.get(0).getMainID());
				modelAndView.addObject("districtList", districtList);
			}
		}
		return modelAndView;
	}

	@RequestMapping("addShippingAddress")
	public ModelAndView addShippingAddress(String name, String mobile,
			String telephone, String provinceId, String cityId,
			String districtId, String address, String zip, Integer isDefault,
			Integer forReturn) {
		ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO();
		if (StringUtil.isNotEmpty(name)) {
			shippingAddressDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(mobile)) {
			shippingAddressDTO.setMobile(mobile);
		}
		if (StringUtil.isNotEmpty(telephone)) {
			shippingAddressDTO.setTelephone(telephone);
		}
		if (StringUtil.isNotEmpty(provinceId)) {
			shippingAddressDTO.setProvinceID(provinceId);
		}
		if (StringUtil.isNotEmpty(cityId)) {
			shippingAddressDTO.setCityID(cityId);
		}
		if (StringUtil.isNotEmpty(districtId)) {
			shippingAddressDTO.setDisctrictID(districtId);
		}
		if (StringUtil.isNotEmpty(address)) {
			shippingAddressDTO.setAddress(address);
		}
		if (StringUtil.isNotEmpty(zip)) {
			shippingAddressDTO.setZip(zip);
		}
		if (isDefault != null) {
			if (isDefault == 0) {
				ShippingAddressVO shippingAddressVO = shippingAddressService
						.findDefaultShippingAddress();
				if (shippingAddressVO != null) {
					shippingAddressService
							.updateDefaultShippingAddress(shippingAddressVO
									.getId());
				}
			}
			shippingAddressDTO.setIsDefault(isDefault);
		}
		if (forReturn != null) {
			if (forReturn == 0) {
				ShippingAddressVO shippingAddressVO = shippingAddressService
						.findforReturnDefaultShippingAddress();
				if (shippingAddressVO != null) {
					shippingAddressService
							.updateforReturnDefaultShippingAddress(shippingAddressVO
									.getId());
				}
			}
			shippingAddressDTO.setForReturn(forReturn);
		}
		shippingAddressDTO.setCreator(getCurrentOperator());
		shippingAddressService.addShippingAddress(shippingAddressDTO);
		return new ModelAndView("redirect:shippingAddressList.htm");
	}

	/**
	 * 编辑页面
	 * 
	 * @date 2014年11月20日
	 * @author wanghehua
	 * @param id
	 * @return
	 * @since JDK 1.6
	 * @Description
	 */
	@RequestMapping("editShippingAddressPage")
	public ModelAndView editShippingAddressPage(Long id) {
		ModelAndView modelAndView = this.newModelAndView();
		modelAndView.setViewName("screen/system/shippingaddress_edit");
		ShippingAddressVO shippingAddressVO = shippingAddressService
				.findShippingAddressByID(id);
		modelAndView.addObject("shippingAddressVO", shippingAddressVO);
		List<AreaVO> provinceList = areaService.getAllProvince();
		modelAndView.addObject("provinceList", provinceList);
		List<AreaVO> cityList = null;
		List<AreaVO> districtList = null;
		if (StringUtil.isNotEmpty(shippingAddressVO.getProvinceID())) {
			cityList = areaService.getAreaByParentID(shippingAddressVO
					.getProvinceID());
		} else {
			if (provinceList != null && provinceList.size() > 0) {
				cityList = areaService.getAreaByParentID(provinceList.get(0)
						.getMainID());
			}
		}
		modelAndView.addObject("cityList", cityList);
		if (StringUtil.isNotEmpty(shippingAddressVO.getCityID())) {
			districtList = areaService.getAreaByParentID(shippingAddressVO
					.getCityID());
		} else {
			if (cityList != null && cityList.size() > 0) {
				districtList = areaService.getAreaByParentID(cityList.get(0)
						.getMainID());
			}
		}
		modelAndView.addObject("districtList", districtList);
		return modelAndView;
	}

	@RequestMapping("editShippingAddress")
	public ModelAndView editShippingAddress(Long id, String name,
			String mobile, String telephone, String provinceId, String cityId,
			String districtId, String address, String zip, Integer isDefault,
			Integer forReturn) {
		ShippingAddressDTO shippingAddressDTO = new ShippingAddressDTO();
		if (StringUtil.isNotEmpty(name)) {
			shippingAddressDTO.setName(name);
		}
		if (StringUtil.isNotEmpty(mobile)) {
			shippingAddressDTO.setMobile(mobile);
		}
		if (StringUtil.isNotEmpty(telephone)) {
			shippingAddressDTO.setTelephone(telephone);
		}
		if (StringUtil.isNotEmpty(provinceId)) {
			shippingAddressDTO.setProvinceID(provinceId);
		}
		if (StringUtil.isNotEmpty(cityId)) {
			shippingAddressDTO.setCityID(cityId);
		}
		if (StringUtil.isNotEmpty(districtId)) {
			shippingAddressDTO.setDisctrictID(districtId);
		}
		if (StringUtil.isNotEmpty(address)) {
			shippingAddressDTO.setAddress(address);
		}
		if (StringUtil.isNotEmpty(zip)) {
			shippingAddressDTO.setZip(zip);
		}
		if (isDefault != null) {
			if (isDefault == 0) {
				ShippingAddressVO shippingAddressVO = shippingAddressService
						.findDefaultShippingAddress();
				if (shippingAddressVO != null) {
					shippingAddressService
							.updateDefaultShippingAddress(shippingAddressVO
									.getId());
				}
			}
			shippingAddressDTO.setIsDefault(isDefault);
		}
		if (forReturn != null) {
			if (forReturn == 0) {
				ShippingAddressVO shippingAddressVO = shippingAddressService
						.findforReturnDefaultShippingAddress();
				if (shippingAddressVO != null) {
					shippingAddressService
							.updateforReturnDefaultShippingAddress(shippingAddressVO
									.getId());
				}
			}
			shippingAddressDTO.setForReturn(forReturn);
		}
		shippingAddressDTO.setModifier(getCurrentOperator());
		shippingAddressDTO.setId(id);
		shippingAddressService.updateShippingAddress(shippingAddressDTO);
		return new ModelAndView("redirect:shippingAddressList.htm");
	}

	@RequestMapping("deleteShippingAddress")
	public @ResponseBody
	JsonResult deleteShippingAddress(Long id) {
		Boolean isSuccess = shippingAddressService.deleteShippingAddress(id);
		if (isSuccess) {
			return JsonResult.create();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping("updateDefaultShippingAddress")
	public @ResponseBody
	JsonResult updateDefaultShippingAddress(Long id) {
		try {
			ShippingAddressVO shippingAddressVO = shippingAddressService
					.findDefaultShippingAddress();
			if (shippingAddressVO != null) {
				shippingAddressService
						.updateDefaultShippingAddress(shippingAddressVO.getId());
			}
			shippingAddressService.updateIsDefaultShippingAddress(id);
			return JsonResult.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

	@RequestMapping("updateForReturnShippingAddress")
	public @ResponseBody
	JsonResult updateForReturnShippingAddress(Long id) {
		try {
			ShippingAddressVO shippingAddressVO = shippingAddressService
					.findforReturnDefaultShippingAddress();
			if (shippingAddressVO != null) {
				shippingAddressService
						.updateforReturnDefaultShippingAddress(shippingAddressVO
								.getId());
			}
			shippingAddressService.updateforReturnIsDefaultShippingAddress(id);
			return JsonResult.create();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JsonResult(ResultCode.ERROR_SYSTEM);
	}

}
