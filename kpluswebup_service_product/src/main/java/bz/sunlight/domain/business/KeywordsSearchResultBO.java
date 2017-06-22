package bz.sunlight.domain.business;

import java.util.List;

import com.google.common.collect.Lists;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.comom.util.PropertiesUtil;

public class KeywordsSearchResultBO {

	private final String resultType = "spareParts";		// 结果集类型
	private List<SparePartDemo> result;					// 相应备件 
	
	public List<SparePartDemo> getResult() {
		return result;
	}
	public void setResult(List<SparePartDemo> result) {
		this.result = result;
	}
	public String getResultType() {
		return resultType;
	}
	
	public void buildResult(List<ProductVO> parts){
		result = Lists.newArrayList();

		for (ProductVO sparepart : parts){
			SparePartDemo sparePartDemo = new SparePartDemo();
			sparePartDemo.setPartId(sparepart.getMainID());
			sparePartDemo.setPartCode(sparepart.getCode());
			sparePartDemo.setPartName(sparepart.getName());
			sparePartDemo.setBrandName(sparepart.getBrandName());
			sparePartDemo.setIcon(getPicUrlPrefix() + sparepart.getPicURL());
			result.add(sparePartDemo);
		}

	}

	//仅供内部调用的用于拼图片url的方法
	private String getPicUrlPrefix(){
		return PropertiesUtil.getInstanse().getString("mallStatic") + "/img/products/";
	}

}
