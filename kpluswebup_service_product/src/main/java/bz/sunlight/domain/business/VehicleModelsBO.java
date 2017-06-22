package bz.sunlight.domain.business;

import java.util.List;

import com.kpuswebup.comom.util.PropertiesUtil;

//TODO: url,groupName,key,value 这些字段数据库中都没有暂时写死，以后在数据库上添加
public class VehicleModelsBO {

	/*{
	    "modelId": [string], // 车型名称
	    "modelName": [string], // 车型名称
	    
	    "images": [
	        {
	            "url": [string], // 车型图片的绝对地址
	        }
	    ],
	    "groupedParams": [
	        {
	            "groupName": [string], // 参数分组名称
	            "parameters": [
	                {
	                    "key": [string], // 参数名
	                    "value": [string], // 参数值
	                }
	            ]
	        }
	    ]
	}*/

	private String modelId;
	private String modelName;
	private List<Image> images;
	private List<GroupedParam> groupedParams;
	
	
	
	public String getModelName() {
		return modelName;
	}
	
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	
	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public List<GroupedParam> getGroupedParams() {
		return groupedParams;
	}
	public void setGroupedParams(List<GroupedParam> groupedParams) {
		this.groupedParams = groupedParams;
	}
	public class Image{
		private String url;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
		
	}
	public class GroupedParam{
		private String groupName;
		
		private List<KeyValue> parameters;
		
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public List<KeyValue> getParameters() {
			return parameters;
		}
		public void setParameters(List<KeyValue> parameters) {
			this.parameters = parameters;
		}
		
	}
	

	    //用于拼接图片的绝对地址
		public String getPicUrlPrefix(){
			return PropertiesUtil.getInstanse().getString("mallStatic");
		}
	
	
}
