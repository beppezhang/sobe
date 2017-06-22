package bz.sunlight.domain.business;

import java.util.List;

import com.google.common.collect.Lists;
import com.kpluswebup.web.vo.ProductVO;


public class OEMSearchResultBO {

	private final String resultType = "sparePart"; 		 // 结果集类型
	private SparePartBO result;
	
	public String getResultType() {
		return resultType;
	}
	public SparePartBO getResult() {
		return result;
	}
	public void setResult(SparePartBO result) {
		this.result = result;
	}	
	
}
