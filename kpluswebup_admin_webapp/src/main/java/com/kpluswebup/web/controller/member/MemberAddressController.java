package com.kpluswebup.web.controller.member;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CustomerDeliveryAddressDTO;
import com.kpluswebup.web.member.service.CustomerAddressService;
import com.kpluswebup.web.vo.AreaVO;
import com.kpluswebup.web.vo.CustomerDeliveryAddressVO;
import com.kpluswebup.web.vo.CustomerVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/member")
public class MemberAddressController extends BaseController {

    @Autowired
    private CustomerAddressService customerAddressService;
    @Autowired
    private AreaService            areaService;

    /**
     * 
     * @date 2015年4月16日
     * @author yuanyuan
     * @param customerID
     * @return
     * @since JDK 1.6
     * @Description 收货地址列表
     */
    @RequestMapping("addressList")
    public ModelAndView addressList(String customerID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/address_list");
        List<CustomerDeliveryAddressVO> list = customerAddressService.findAddressByCustomerID(customerID,0);
        modelAndView.addObject("addressList", list);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerID);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    /**
     * 删除收货地址
     * @date 2015年4月16日
     * @author yuanyuan
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("deleteAddress")
    public @ResponseBody
    JsonResult deleteAddress(Long id) {
        Boolean isSuccess = customerAddressService.deleteAddressByPrimaryKey(id);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 添加页面
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("addAddressPage")
    public ModelAndView addAddressPage(String customerId) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/address_add");
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        if (provinceList != null && provinceList.size() > 0) {
            List<AreaVO> cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
            modelAndView.addObject("cityList", cityList);
            if (cityList != null && cityList.size() > 0) {
                List<AreaVO> districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
                modelAndView.addObject("districtList", districtList);
            }
        }
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(customerId);
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    /**
     * 
     * @date 2015年4月16日
     * @author yuanyuan
     * @param customerId
     * @param name
     * @param mobile
     * @param telephone
     * @param provinceId
     * @param cityId
     * @param districtId
     * @param address
     * @param zip
     * @return
     * @since JDK 1.6
     * @Description 添加
     */
    @RequestMapping("addAddress")
    public ModelAndView addAddress(String customerId, String name, String mobile, String telephone, String provinceId,
                                   String cityId, String districtId, String address, String zip) {
        CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerDeliveryAddressDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            customerDeliveryAddressDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            customerDeliveryAddressDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(provinceId)) {
            customerDeliveryAddressDTO.setProvinceID(provinceId);
        }
        if (StringUtil.isNotEmpty(cityId)) {
            customerDeliveryAddressDTO.setCityID(cityId);
        }
        if (StringUtil.isNotEmpty(districtId)) {
            customerDeliveryAddressDTO.setDisctrictID(districtId);
        }
        if (StringUtil.isNotEmpty(address)) {
            customerDeliveryAddressDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(zip)) {
            customerDeliveryAddressDTO.setZip(zip);
        }
        if (StringUtil.isNotEmpty(customerId)) {
            customerDeliveryAddressDTO.setCustomerID(customerId);
        }
        customerDeliveryAddressDTO.setCreator(getCurrentOperator());
        customerAddressService.addAddress(customerDeliveryAddressDTO);
        return new ModelAndView("redirect:addressList.do?customerID=" + customerId);
    }

    /**
     * 编辑页面
     * 
     * @date 2014年11月12日
     * @author wanghehua
     * @param id
     * @return
     * @since JDK 1.6
     * @Description
     */
    @RequestMapping("editAddressPage")
    public ModelAndView editAddressPage(Long id) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/membercenter/address_edit");
        CustomerDeliveryAddressVO addressVO = customerAddressService.findAddressByID(id);
        modelAndView.addObject("addressVO", addressVO);
        List<AreaVO> provinceList = areaService.getAllProvince();
        modelAndView.addObject("provinceList", provinceList);
        List<AreaVO> cityList = null;
        List<AreaVO> districtList = null;
        if (StringUtil.isNotEmpty(addressVO.getProvinceID())) {
            cityList = areaService.getAreaByParentID(addressVO.getProvinceID());
        } else {
            if (provinceList != null && provinceList.size() > 0) {
                cityList = areaService.getAreaByParentID(provinceList.get(0).getMainID());
            }
        }
        modelAndView.addObject("cityList", cityList);
        if (StringUtil.isNotEmpty(addressVO.getCityID())) {
            districtList = areaService.getAreaByParentID(addressVO.getCityID());
        } else {
            if (cityList != null && cityList.size() > 0) {
                districtList = areaService.getAreaByParentID(cityList.get(0).getMainID());
            }
        }
        modelAndView.addObject("districtList", districtList);
        CustomerVO customerVO = new CustomerVO();
        customerVO.setMainID(addressVO.getCustomerID());
        modelAndView.addObject("customerVO", customerVO);
        return modelAndView;
    }

    @RequestMapping("editAddress")
    public ModelAndView editAddress(Long id, String customerId, String name, String mobile, String telephone,
                                    String provinceId, String cityId, String districtId, String address, String zip) {
        CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
        if (StringUtil.isNotEmpty(name)) {
            customerDeliveryAddressDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(mobile)) {
            customerDeliveryAddressDTO.setMobile(mobile);
        }
        if (StringUtil.isNotEmpty(telephone)) {
            customerDeliveryAddressDTO.setTelephone(telephone);
        }
        if (StringUtil.isNotEmpty(provinceId)) {
            customerDeliveryAddressDTO.setProvinceID(provinceId);
        }
        if (StringUtil.isNotEmpty(cityId)) {
            customerDeliveryAddressDTO.setCityID(cityId);
        }
        if (StringUtil.isNotEmpty(districtId)) {
            customerDeliveryAddressDTO.setDisctrictID(districtId);
        }
        if (StringUtil.isNotEmpty(address)) {
            customerDeliveryAddressDTO.setAddress(address);
        }
        if (StringUtil.isNotEmpty(zip)) {
            customerDeliveryAddressDTO.setZip(zip);
        }
        customerDeliveryAddressDTO.setId(id);
        customerDeliveryAddressDTO.setModifier(getCurrentOperator());
        customerAddressService.editAddress(customerDeliveryAddressDTO);
        return new ModelAndView("redirect:addressList.do?customerID=" + customerId);
    }

    @RequestMapping("updateDefaultAddress")
    public @ResponseBody
    JsonResult updateDefaultAddress(Long addressId, String customerId,String type) {
        try {
            CustomerDeliveryAddressVO customerDeliveryAddressVO = customerAddressService.findDefaultAddressByCustomerID(customerId,Integer.valueOf(type));
            if (customerDeliveryAddressVO != null) {
                customerAddressService.editDefaultAddressByID(customerDeliveryAddressVO.getId());
            }
            customerAddressService.editIsDefaultAddressByID(addressId);
            return JsonResult.create();
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

    @RequestMapping("deliveryAddressList")
    public ModelAndView deliveryAddressList(String pageNo, String pageSize,String searchUserName,String searchStatus) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/membercenter/deliveryaddress_list");
        CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
        if (StringUtil.isNotEmpty(searchUserName)) {
            customerDeliveryAddressDTO.setName(searchUserName);
        }
        if (StringUtil.isNumberic(searchStatus)) {
            customerDeliveryAddressDTO.setStatus(Integer.parseInt(searchStatus));
        }
        if (StringUtil.isNumberic(pageSize)) {
            customerDeliveryAddressDTO.setPageSize(Long.parseLong(pageSize));
        }
        if (StringUtil.isNumberic(pageNo)) {
            customerDeliveryAddressDTO.setPageNo(Long.parseLong(pageNo));
        }
        modelAndView.addObject("customerDeliveryAddressDTO", customerDeliveryAddressDTO);
        List<CustomerDeliveryAddressVO> list = customerAddressService.findDeliveryAddressByPagination(customerDeliveryAddressDTO);
        modelAndView.addObject("deliveryAddressList", list);
        return modelAndView;
    }
    /**
     * 
     * @date 2015年4月16日
     * @author yuanyuan
     * @param status
     * @param addressId
     * @return
     * @since JDK 1.6
     * @Description 改变状态
     */
    @RequestMapping("changeDeliveryAddress")
    public @ResponseBody
    JsonResult changeDeliveryAddress(String status,String addressId) {
        try {
            ModelAndView modelAndView = this.newModelAndView();
            modelAndView.setViewName("/screen/membercenter/deliveryaddress_list");
            CustomerDeliveryAddressDTO customerDeliveryAddressDTO = new CustomerDeliveryAddressDTO();
            if (StringUtil.isNumberic(status)) {
                customerDeliveryAddressDTO.setStatus(Integer.parseInt(status));
            }
            if (StringUtil.isNumberic(addressId)) {
                customerDeliveryAddressDTO.setId(Long.valueOf(addressId));
            }
            customerAddressService.changeDeliveryAddress(customerDeliveryAddressDTO);
            return JsonResult.create();
        } catch (Exception e) {
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }

   
}
