package com.kpluswebup.web.controller.system;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.kpluswebup.web.admin.system.service.AreaService;
import com.kpluswebup.web.common.controller.BaseController;
import com.kpluswebup.web.vo.AreaVO;
import com.kpuswebup.comom.util.JsonResult;
import com.kpuswebup.comom.util.ResultCode;

@Controller
@RequestMapping("/admin/system")
public class AreaController extends BaseController {
    
    @Autowired
    private AreaService areaService;
    
    @RequestMapping("ajaxAreaMessage")
    public @ResponseBody
    JsonResult ajaxAreaMessage(String parentID) {
        try {
            List<AreaVO> list = areaService.getAreaByParentID(parentID);
            return new JsonResult(list);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(ResultCode.ERROR_SYSTEM);
        }
    }
}
