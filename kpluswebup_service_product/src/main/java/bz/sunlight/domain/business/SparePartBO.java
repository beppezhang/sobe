package bz.sunlight.domain.business;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kpluswebup.web.vo.PartsCategoryVo;
import com.kpluswebup.web.vo.ProductVO;
import com.kpuswebup.comom.util.PropertiesUtil;

public class SparePartBO {

	private String partId;					 // 备件 Id
	private String partCode;				 // 备件编号
	private String partName;				 // 备件名称
	private String oeCode;					 // 原厂编号
	private String brandName;				 // 备件品牌名称
	private boolean isGeneric;				 // 是否通用件
	public List<Map<String,String>> belongTo;// 备件分类
	public List<Map<String,String>> images;	 // 车型图片的绝对地址
	public List<KeyValue> parameters;		 // ？？？
	public List<SparePartDemo> related;		 // 副厂件
	
	public void buildRelated(List<ProductVO> partsRelated)
	{
		if (partsRelated == null || partsRelated.size() == 0)
			return;
		related = Lists.newArrayList();

		for (ProductVO sparepart : partsRelated){
			SparePartDemo sparePartDemo = new SparePartDemo();
			sparePartDemo.setPartId(sparepart.getMainID());
			sparePartDemo.setPartCode(sparepart.getCode());
			sparePartDemo.setPartName(sparepart.getName());
			sparePartDemo.setBrandName(sparepart.getBrandName());
			sparePartDemo.setIcon(getPicUrlPrefix() + sparepart.getPicURL());
			related.add(sparePartDemo);
		}
	}
	
	
	public void buildBelongTo(List<PartsCategoryVo> partsCategoryVos)
	{		
		//此处 建议直接GsonUitl工具类转换List<PartsCategoryVo> ,控制仅转换 partCategoryId,partCategoryName,并设置别名
		if (partsCategoryVos == null)
			return;
		belongTo = Lists.newArrayList();
		Map<String,String> p = null;
		for (PartsCategoryVo partsCategoryVo : partsCategoryVos) {
			if(partsCategoryVo.getFlevel()==1){
				p = Maps.newHashMap();
				p.put("partCategoryId", partsCategoryVo.getMainID());
				p.put("partCategoryName", partsCategoryVo.getName());
				belongTo.add(p);
				
				//在确定了备件大类之后内嵌一个同样的循环找到备件小类
				for (PartsCategoryVo partsCategoryVoLevel2 : partsCategoryVos) {
					if(partsCategoryVoLevel2.getFlevel()==2){
						p = Maps.newHashMap();
						p.put("partCategoryId", partsCategoryVoLevel2.getMainID());
						p.put("partCategoryName", partsCategoryVoLevel2.getName());
						belongTo.add(p);

						//找到第三层备件分类
						for (PartsCategoryVo partsCategoryVoLevel3 : partsCategoryVos) {
							if(partsCategoryVoLevel3.getFlevel()==3){
								p = Maps.newHashMap();
								p.put("partCategoryId", partsCategoryVoLevel3.getMainID());
								p.put("partCategoryName", partsCategoryVoLevel3.getName());
								belongTo.add(p);
								break;	//这里因为第三层数据问题，暂时只加入一个到目标容器
							}
						}
					}
				}
			}
		}
	}
	
	public void buildImages(List<String> urls)
	{
		if (urls == null)
			return;
		images = Lists.newArrayList();
		Map<String,String> urlMap = null;
		for (String url : urls) {
			urlMap = Maps.newHashMap();
			urlMap.put("url",getPicUrlPrefix() + url);
			images.add(urlMap);
		}
	}
	
	//仅供内部调用的用于拼图片url的方法
	private String getPicUrlPrefix(){
		return PropertiesUtil.getInstanse().getString("mallStatic") + "/img/products/";
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartCode() {
		return partCode;
	}

	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getOeCode() {
		return oeCode;
	}

	public void setOeCode(String oeCode) {
		this.oeCode = oeCode;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public boolean isGeneric() {
		return isGeneric;
	}

	public void setGeneric(boolean isGeneric) {
		this.isGeneric = isGeneric;
	}

	public List<Map<String, String>> getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(List<Map<String, String>> belongTo) {
		this.belongTo = belongTo;
	}

	public List<Map<String, String>> getImages() {
		return images;
	}

	public void setImages(List<Map<String, String>> images) {
		this.images = images;
	}

	public List<KeyValue> getParameters() {
		return parameters;
	}

	public void setParameters(List<KeyValue> parameters) {
		this.parameters = parameters;
	}

	public List<SparePartDemo> getRelated() {
		return related;
	}

	public void setRelated(List<SparePartDemo> related) {
		this.related = related;
	}
	
}
