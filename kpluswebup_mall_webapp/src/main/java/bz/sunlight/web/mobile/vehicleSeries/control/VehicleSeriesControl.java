package bz.sunlight.web.mobile.vehicleSeries.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.async.WebAsyncManager;
import org.springframework.web.context.request.async.WebAsyncUtils;

import com.kpluswebup.web.vo.VehicleTypeVO;
import com.kpuswebup.comom.util.StringUtil;

import bz.sunlight.web.CommonControl;
import bz.sunlight.web.service.VehicleBrandsService;
import bz.sunlight.web.service.VehicleSeriesService;

@Controller
@RequestMapping("/v1/vehicleSeries")
public class VehicleSeriesControl extends CommonControl {

	
	@Autowired
	private VehicleSeriesService vehicleSeriesService;
	
	/**
	 * param: id,
	 * 查询指定车系,
	 * author:zsl
	 */
	@RequestMapping(method = RequestMethod.GET,value = "{id}")
	public @ResponseBody void IdSeries(@PathVariable String id){

		
    	try { 
    		VehicleTypeVO _vehicleTypeVO=new VehicleTypeVO();
    		if (StringUtil.isNotEmpty(id)) {
    			_vehicleTypeVO.setProductCategoryId(id);
    		}
    		String json=vehicleSeriesService.findVehicleSeriesById(_vehicleTypeVO);
    		if(json!=null){
    			write(json);
    		}else{
    			//为空
    			write_404("该车系不存在"); 
    		}
        } catch (IOException e) { 
            e.printStackTrace(); 
            write_500(null);
        }

	}

}
