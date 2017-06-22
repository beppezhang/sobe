package bz.sunlight.domain.business;


public class VINSearchResultBO {

	private final String resultType = "vehicleModel"; 	 // 结果集类型
	private VehicleModelsBO result;						 // 车型信息
	
	public VehicleModelsBO getResult() {
		return result;
	}
	public void setResult(VehicleModelsBO result) {
		this.result = result;
	}
	public String getResultType() {
		return resultType;
	}
	
}
