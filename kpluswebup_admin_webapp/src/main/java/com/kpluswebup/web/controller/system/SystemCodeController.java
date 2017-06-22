package com.kpluswebup.web.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kpluswebup.web.admin.system.service.SystemCodeService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.domain.CodeConfigDTO;
import com.kpluswebup.web.vo.CodeConfigVO;
import com.kpluswebup.web.vo.SystemCodeVO;
import com.kpuswebup.comom.util.StringUtil;

@Controller
@RequestMapping("/admin/system")
public class SystemCodeController extends BaseController{

	@Autowired
	private SystemCodeService systemCodeService;
	
	@RequestMapping("systemcodeList")
	public ModelAndView systemcodeList(){
		ModelAndView modelAndView=this.newModelAndView();
		modelAndView.setViewName("/screen/system/systemcode_list");
		List<SystemCodeVO> list=systemCodeService.findSystemCode(null);
		if(list!=null&&list.size()>0){
			for(SystemCodeVO systemCodeVO:list){
				CodeConfigVO codeConfigVO=systemCodeService.findCodeConfigByID(systemCodeVO.getMainID());
				if(codeConfigVO!=null){
					systemCodeVO.setCodeId(codeConfigVO.getId());
					systemCodeVO.setDefaultValue(codeConfigVO.getCodeEx());
				}
			}
		}
		modelAndView.addObject("systemcodeList", list);
		return modelAndView;
	}
	
	@RequestMapping("addCodeConfig")
	public ModelAndView addCodeConfig(HttpServletRequest request){
		CodeConfigDTO codeConfigDTO=new CodeConfigDTO();
		String [] codeIds=request.getParameterValues("codeId");
		String [] mainIds=request.getParameterValues("mainID");
		String [] codeExs=request.getParameterValues("codeEx");
		if(codeIds!=null){
			for(int i=0;i<codeIds.length;i++){
				String codeId=codeIds[i];
				String mainID=mainIds[i];
				String codeEx=codeExs[i];
				if(StringUtil.isNotEmpty(codeId)){
					if(StringUtil.isNotEmpty(codeEx)){
						codeConfigDTO.setCodeEx(codeEx);
					}
					codeConfigDTO.setId(Long.valueOf(codeId));
					codeConfigDTO.setModifier(getCurrentOperator());
					systemCodeService.updateCodeConfig(codeConfigDTO);
				}else{
					if(StringUtil.isNotEmpty(mainID)){
						codeConfigDTO.setCodeID(mainID);
					}
					if(StringUtil.isNotEmpty(codeEx)){
						codeConfigDTO.setCodeEx(codeEx);
					}
					codeConfigDTO.setCreator(getCurrentOperator());
					systemCodeService.addCodeConfig(codeConfigDTO);
				}
			}
		}
		return new ModelAndView("redirect:systemcodeList.htm");
	}
}
