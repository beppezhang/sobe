package com.kpluswebup.web.controller.supplier;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.SupplierDTO;
import com.kpluswebup.web.domain.SupplierGroupDTO;
import com.kpluswebup.web.domain.SupplierGroupMemberDTO;
import com.kpluswebup.web.domain.SupplierGroupSetDTO;
import com.kpluswebup.web.domain.SupplierItemDTO;
import com.kpluswebup.web.service.SupplierItemService;
import com.kpluswebup.web.supplier.service.SupplierGroupService;
import com.kpluswebup.web.supplier.service.SupplierService;
import com.kpluswebup.web.vo.CustomerGroupSetVO;
import com.kpluswebup.web.vo.SupplierGroupMemberVO;
import com.kpluswebup.web.vo.SupplierGroupSetVO;
import com.kpluswebup.web.vo.SupplierGroupVO;
import com.kpluswebup.web.vo.SupplierVO;
import com.kpuswebup.comom.util.DateUtil;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.Md5Algorithm;
import com.kpuswebup.comom.util.ResultCode;
import com.kpuswebup.comom.util.StringUtil;
import com.kpuswebup.comom.util.UUIDUtil;

@Controller
@RequestMapping("/admin/supplier")
public class SupplierController extends BaseController {
	             
	 @Autowired
     private SupplierService supplierService;
	 
	 @Autowired
     private SupplierItemService supplierItemService;
     
	 @Autowired
	 private SupplierGroupService supplierGroupService;
	 
     @RequestMapping("/supplierList")
     public ModelAndView supplierList(String pageNo, String pageSize, String searchMobile, String searchCompanyName, 
    		String searchLinkMan, String searchOpeningBank, String searchBankAccount, String searchAddress,
    		String searchUserName) {
         ModelAndView modelAndView = this.newModelAndView();
         modelAndView.setViewName("screen/supplier/supplier_list");
         SupplierDTO supplierDTO=new SupplierDTO();
         if (StringUtil.isNotEmpty(searchMobile)) {
        	 supplierDTO.setMobile(searchMobile);;
         }
         if (StringUtil.isNotEmpty(searchCompanyName)) {
        	 supplierDTO.setCompanyName(searchCompanyName);
         }
         if (StringUtil.isNotEmpty(searchLinkMan)) {
        	 supplierDTO.setLinkMan(searchLinkMan);
         }
         if (StringUtil.isNotEmpty(searchOpeningBank)) {
        	 supplierDTO.setOpeningBank(searchOpeningBank);
         }
         if (StringUtil.isNotEmpty(searchBankAccount)) {
        	 supplierDTO.setBankAccount(searchBankAccount);
         }
         if (StringUtil.isNotEmpty(searchAddress)) {
        	 supplierDTO.setAddress(searchAddress);
         }
         if (StringUtil.isNotEmpty(searchUserName)) {
        	 supplierDTO.setUserName(searchUserName);
         }
 		 if (StringUtil.isNumberic(pageSize)) {
 			supplierDTO.setPageSize(Long.parseLong(pageSize));
 		 }
 		 if (StringUtil.isNumberic(pageNo)) {
 			supplierDTO.setPageNo(Long.parseLong(pageNo));
 		 }
         List<SupplierVO> list = supplierService.findSupplier(supplierDTO);
         modelAndView.addObject("supplierList", list);
         modelAndView.addObject("supplierDTO", supplierDTO);
         return modelAndView;
     }
       
     @RequestMapping("/addSupplierPage")
     public ModelAndView addSupplierPage() {
         ModelAndView modelAndView = this.newModelAndView();
         modelAndView.setViewName("screen/supplier/supplier_add");
    	 return modelAndView;
    	 }
     
     @RequestMapping("ajaxCheckUserName")
     public @ResponseBody
     JsonResult ajaxCheckUserName(String userName) {
//         SupplierDTO supplierDTO = new SupplierDTO();
//         if(StringUtil.isNotEmpty(userName)){
//             supplierDTO.setUserName(userName);
//         }
//         if(StringUtil.isNotEmpty(companyName)){
//             supplierDTO.setCompanyName(companyName);
//         }
//		   List<SupplierVO> supplierList = supplierService.findSupplier(supplierDTO);
//    	 if (supplierList.size()==0) {
//    		 return JsonResult.create();
//    	 }
         
    	 SupplierVO supplierVO = supplierService.findSupplierByUserName(userName);
         if (supplierVO == null){
        	 return JsonResult.create();
         }

         return new JsonResult(ResultCode.ERROR_SYSTEM);
     }

     @RequestMapping("ajaxCheckCompanyName")
     public @ResponseBody
     JsonResult ajaxCheckCompanyName(String companyName) {
    	 SupplierVO supplierVO = supplierService.findSupplierByCompanyName(companyName);
         if (supplierVO == null){
        	 return JsonResult.create();
         }

         return new JsonResult(ResultCode.ERROR_SYSTEM);
     }

     @RequestMapping("addSupplier")
     public ModelAndView addSupplier(String mobile, String companyName2, String linkMan, 
    		 String openingBank, String bankAccount, String address, String userName2, String passWord,Double cashDep) {
    	 SupplierDTO supplierDTO = new SupplierDTO();
         if (StringUtil.isNotEmpty(mobile)) {
        	 supplierDTO.setMobile(mobile);
         }
         if (StringUtil.isNotEmpty(companyName2)) {
        	 supplierDTO.setCompanyName(companyName2);
         }
         if (StringUtil.isNotEmpty(linkMan)) {
        	 supplierDTO.setLinkMan(linkMan);
         }
         if (StringUtil.isNotEmpty(openingBank)) {
        	 supplierDTO.setOpeningBank(openingBank);
         }
         if (StringUtil.isNotEmpty(bankAccount)) {
        	 supplierDTO.setBankAccount(bankAccount);
         }
         if (StringUtil.isNotEmpty(address)) {
        	 supplierDTO.setAddress(address);
         }
         if (StringUtil.isNotEmpty(userName2)) {
        	 supplierDTO.setUserName(userName2);
         }
         if (StringUtil.isNotEmpty(cashDep+"")) {
             supplierDTO.setCashDep(cashDep);
         }
         if (StringUtil.isNotEmpty(passWord)) {
        	 supplierDTO.setPassWord(Md5Algorithm.getInstance().md5Digest(passWord.getBytes()));;
         }
         supplierDTO.setMainID(UUIDUtil.getUUID());
         supplierDTO.setCreator(getCurrentOperator());
         supplierService.addSupplier(supplierDTO);
         return new ModelAndView("redirect:supplierList.htm");
     }
     
     @RequestMapping("/editSupplierPage")
     public ModelAndView editSupplierPage(String mainID) {
         ModelAndView modelAndView = this.newModelAndView();
         modelAndView.setViewName("screen/supplier/supplier_edit");
         SupplierVO supplierVO=supplierService.findSupplierByID(mainID);
         modelAndView.addObject("supplierVO", supplierVO);
 		 //modelAndView.addObject("mainID", mainID);
    	 return modelAndView;
    	 }
     
     @RequestMapping("editSupplier")
     public ModelAndView editSupplier(String mainID, String mobile, String companyName, String linkMan, 
    		 String openingBank, String bankAccount, String address, String userName, String passWord,Double cashDep) {
    	 SupplierDTO supplierDTO = new SupplierDTO();
         if (StringUtil.isNotEmpty(mobile)) {
        	 supplierDTO.setMobile(mobile);
         }
         if (StringUtil.isNotEmpty(companyName)) {
        	 supplierDTO.setCompanyName(companyName);
         }
         if (StringUtil.isNotEmpty(linkMan)) {
        	 supplierDTO.setLinkMan(linkMan);
         }
         if (StringUtil.isNotEmpty(openingBank)) {
        	 supplierDTO.setOpeningBank(openingBank);
         }
         if (StringUtil.isNotEmpty(bankAccount)) {
        	 supplierDTO.setBankAccount(bankAccount);
         }
         if (StringUtil.isNotEmpty(address)) {
        	 supplierDTO.setAddress(address);
         }
         /*if (StringUtil.isNotEmpty(userName)) {
        	 supplierDTO.setUserName(userName);
         }
         if (StringUtil.isNotEmpty(passWord)) {
        	 supplierDTO.setPassWord(Md5Algorithm.getInstance().md5Digest(passWord.getBytes()));
         }*/
         if (StringUtil.isNotEmpty(cashDep+"")) {
             supplierDTO.setCashDep(cashDep);
         }
         supplierDTO.setMainID(mainID);
         supplierDTO.setModifier(getCurrentOperator());
         supplierService.editSupplier(supplierDTO);
         return new ModelAndView("redirect:supplierList.htm");
     }
     
    @RequestMapping("deleteSupplier")
     public @ResponseBody
     JsonResult deleteSupplier(String mainID) {
         Boolean isSuccess = supplierService.deleteSupplierById(mainID);
         if (isSuccess) {
             return JsonResult.create();
         }
         return new JsonResult(ResultCode.ERROR_SYSTEM);
     }
    
    @RequestMapping("exportSupplier")
	public void exportSupplier(HttpServletResponse response, String searchMobile, String searchCompanyName, String searchLinkMan,
    		 								String searchOpeningBank, String searchBankAccount, String searchAddress, String searchUserName) {
		try {
			SupplierDTO supplierDTO=new SupplierDTO();
			if (StringUtil.isNotEmpty(searchMobile)) {
	        	 supplierDTO.setMobile(searchMobile);;
	         }
	         if (StringUtil.isNotEmpty(searchCompanyName)) {
	        	 supplierDTO.setCompanyName(searchCompanyName);
	         }
	         if (StringUtil.isNotEmpty(searchLinkMan)) {
	        	 supplierDTO.setLinkMan(searchLinkMan);
	         }
	         if (StringUtil.isNotEmpty(searchOpeningBank)) {
	        	 supplierDTO.setOpeningBank(searchOpeningBank);
	         }
	         if (StringUtil.isNotEmpty(searchBankAccount)) {
	        	 supplierDTO.setBankAccount(searchBankAccount);
	         }
	         if (StringUtil.isNotEmpty(searchAddress)) {
	        	 supplierDTO.setAddress(searchAddress);
	         }
	         if (StringUtil.isNotEmpty(searchUserName)) {
	        	 supplierDTO.setUserName(searchUserName);
	         }
			supplierService.exportSupplier(response, supplierDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    
    //----------------------------------------------供应商分组-------------------------------------------------
    
    @RequestMapping("suppliergroupList")
    public ModelAndView suppliergroupList(SupplierGroupDTO supplierGroupDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/supplier/suppliergroup_list");
        List<SupplierGroupVO> list = supplierGroupService.findSupplierGroupByPagination(supplierGroupDTO);
        modelAndView.addObject("suppliergroupList", list);
        modelAndView.addObject("supplierGroupDTO", supplierGroupDTO);
        return modelAndView;
    }

    @RequestMapping("/addSupplierGroupPage")
    public ModelAndView addSupplierGroupPage() {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/supplier/suppliergroup_add");
        return modelAndView;
    }

    @RequestMapping("addSupplierGroup")
    public ModelAndView addSupplierGroup(String name, String description) {
        SupplierGroupDTO supplierGroupDTO = new SupplierGroupDTO();
        if (StringUtil.isNotEmpty(name)) {
        	supplierGroupDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
        	supplierGroupDTO.setDescription(description);
        }
        supplierGroupDTO.setMainID(UUIDUtil.getUUID());
        supplierGroupDTO.setCreator(getCurrentOperator());
        supplierGroupService.addSupplierGroup(supplierGroupDTO);
        return new ModelAndView("redirect:editSupplierGroupPage.htm?mainID=" + supplierGroupDTO.getMainID());
    }

    @RequestMapping("/editSupplierGroupPage")
    public ModelAndView editSupplierGroupPage(String mainID) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("screen/supplier/suppliergroup_edit");
        SupplierGroupVO supplierGroupVO = supplierGroupService.findSupplierGroupByMainID(mainID);
        modelAndView.addObject("supplierGroupVO", supplierGroupVO);
        modelAndView.addObject("mainID", mainID);
        List<SupplierGroupSetVO> list = supplierGroupService.findSupplierGroupSetByGroupId(mainID);
        modelAndView.addObject("groupsetList", list);
        return modelAndView;
    }

    @RequestMapping("editSupplierGroup")
    public ModelAndView editSupplierGroup(String mainID, String name, String description) {
    	SupplierGroupDTO supplierGroupDTO = new SupplierGroupDTO();
        if (StringUtil.isNotEmpty(name)) {
        	supplierGroupDTO.setName(name);
        }
        if (StringUtil.isNotEmpty(description)) {
        	supplierGroupDTO.setDescription(description);
        }
        supplierGroupDTO.setMainID(mainID);
        supplierGroupDTO.setModifier(getCurrentOperator());
        supplierGroupService.editSupplierGroup(supplierGroupDTO);
        supplierGroupService.deleteSupplierGroupMemberByGroupID(mainID);
        List<SupplierGroupSetVO> list = supplierGroupService.findSupplierGroupSetByGroupId(mainID);
        if (list != null && list.size() > 0) {
            SupplierVO supplierVO = new SupplierVO();
            for (SupplierGroupSetVO supplierGroupSetVO : list) {
                if (supplierGroupSetVO.getSetType() == 4) {
                    try {
                        supplierVO.setMincreateTime(DateUtil.strintToDatetimeYMD(supplierGroupSetVO.getMinimum()));
                        supplierVO.setMaxcreateTime(DateUtil.strintToDatetimeYMD(supplierGroupSetVO.getMaxmum()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }else if (supplierGroupSetVO.getSetType() == 7) {
                    supplierVO.setUserName(supplierGroupSetVO.getMinimum());
                }
            }
            List<SupplierVO> supplierlist = supplierGroupService.findSupplierByGroupSet(supplierVO);
            if (supplierlist != null && supplierlist.size() > 0) {
                SupplierGroupMemberDTO supplierGroupMemberDTO = new SupplierGroupMemberDTO();
                for (SupplierVO supplierVO2 : supplierlist) {
                    supplierGroupMemberDTO.setGroupID(supplierGroupDTO.getMainID());
                	supplierGroupMemberDTO.setSupplierID(supplierVO2.getMainID());
                	supplierGroupMemberDTO.setCreator(getCurrentOperator());
                	supplierGroupService.addSupplierGroupMember(supplierGroupMemberDTO);
                }
            }
        }
        return new ModelAndView("redirect:suppliergroupList.htm");
    }

    @RequestMapping("deleteSupplierGroup")
    public @ResponseBody
    JsonResult deleteSupplierGroup(String mainIds) {
        Boolean isSuccess = supplierGroupService.deleteSupplierGroupByMainID(mainIds);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("deleteSupplierGroupSet")
    public @ResponseBody
    JsonResult deleteSupplierGroupSet(Long id) {
        Boolean isSuccess = supplierGroupService.deleteGroupSetByID(id);
        if (isSuccess) {
            return JsonResult.create();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("addSupplierGroupSet")
    public @ResponseBody
    JsonResult addSupplierGroupSet(String groupId, Integer setType, String minimum, String maxmum) {
        SupplierGroupSetDTO supplierGroupSetDTO = new SupplierGroupSetDTO();
        if (StringUtil.isNotEmpty(groupId)) {
        	supplierGroupSetDTO.setGroupID(groupId);
        }
        if (setType != null) {
        	supplierGroupSetDTO.setSetType(setType);
        }
        if (StringUtil.isNotEmpty(minimum)) {
        	supplierGroupSetDTO.setMinimum(minimum);
        }
        if (StringUtil.isNotEmpty(maxmum)) {
        	supplierGroupSetDTO.setMaxmum(maxmum);
        }
        try {
        	supplierGroupService.addGroupSet(supplierGroupSetDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    @RequestMapping("editSupplierGroupSet")
    public @ResponseBody
    JsonResult editSupplierGroupSet(Long id, String sexType, String minimum, String maxmum) {
        SupplierGroupSetDTO supplierGroupSetDTO = new SupplierGroupSetDTO();
        if (StringUtil.isNotEmpty(sexType)) {
        	supplierGroupSetDTO.setMinimum(sexType);
        } else {
            if (StringUtil.isNotEmpty(minimum)) {
            	supplierGroupSetDTO.setMinimum(minimum);
            }
            if (StringUtil.isNotEmpty(maxmum)) {
            	supplierGroupSetDTO.setMaxmum(maxmum);
            }
        }
        supplierGroupSetDTO.setId(id);
        try {
        	supplierGroupService.editGroupSet(supplierGroupSetDTO);
            return JsonResult.create();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(ResultCode.ERROR_SYSTEM);
    }

    /**
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param supplierId
     * @param supplierGroupMemberDTO
     * @return
     * @since JDK 1.6
     * @Description  组内供应商（组内供应商）
     */
    @RequestMapping("supplierGroupSetList")
    public ModelAndView supplierGroupSetList(String groupID, SupplierGroupMemberDTO supplierGroupMemberDTO) {
        ModelAndView modelAndView = this.newModelAndView();
        modelAndView.setViewName("/screen/supplier/suppliergroupset_list");
        supplierGroupMemberDTO.setGroupID(groupID);
        List<SupplierGroupMemberVO> list = supplierGroupService.findSupplierGroupMemberByPagination(supplierGroupMemberDTO);
        modelAndView.addObject("suppliergroupsetList", list);
        modelAndView.addObject("supplierGroupMemberDTO", supplierGroupMemberDTO);
        SupplierGroupVO supplierGroupVO = new SupplierGroupVO();
        supplierGroupVO.setMainID(groupID);
        modelAndView.addObject("supplierGroupVO", supplierGroupVO);
        return modelAndView;
    }

    /**
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description  导出
     */
    @RequestMapping("exportSupplierGroupMember")
    public void exportSupplierGroupMember(HttpServletResponse response, String mainIds,String groupID) {
        try {
        	supplierGroupService.exportSupplierGroupMember(response, mainIds,groupID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @date 2015年4月22日
     * @author meisu
     * @param response
     * @param mainIds
     * @since JDK 1.6
     * @Description  导出供应商商品
     */
    @RequestMapping("exportSupplierItem")
    public void exportSupplierItem(HttpServletResponse response, String supplierName, String searchItemId,
    															String searchItemName, Integer status) {
        try {
        	SupplierItemDTO supplierItemDTO = new SupplierItemDTO();
        	if (StringUtil.isNotEmpty(supplierName)) {
            	supplierItemDTO.setSupplierName(supplierName);
            }
        	if (StringUtil.isNotEmpty(searchItemId)) {
            	supplierItemDTO.setItemID(searchItemId);
            }
        	if (StringUtil.isNotEmpty(searchItemName)) {
            	supplierItemDTO.setItemName(searchItemName);
            }
        	if (null!=status) {
            	supplierItemDTO.setStatus(status);
            }
        	supplierItemService.exportSupplierItem(response, supplierItemDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
