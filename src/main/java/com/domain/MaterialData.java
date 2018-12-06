package com.domain;

public class MaterialData {
private String materialCode;
private String materialName;
private String colorCode;
private Integer quantity;
private String LWHUnit;
private Double length;
private Double width;
private Double height;
private Double weight;
private String weightUnit;
private Double maxWeight;
private Integer bearingLevel;
private Boolean onlyBottom;
private Boolean onlyTop;
private Integer kitId;
private Integer groupId;
private Boolean allowA;
private Boolean supportA;
private Integer maxLayerA;
private Integer maxOverHangA;

public String getMaterialCode() {
	return materialCode;
}
public void setMaterialCode(String materialCode) {
	this.materialCode = materialCode;
}
public String getMaterialName() {
	return materialName;
}
public void setMaterialName(String materialName) {
	this.materialName = materialName;
}
public String getColorCode() {
	return colorCode;
}
public void setColorCode(String colorCode) {
	this.colorCode = colorCode;
}
public Integer getQuantity() {
	return quantity;
}
public void setQuantity(Integer quantity) {
	this.quantity = quantity;
}
public String getLWHUnit() {
	return LWHUnit;
}
public void setLWHUnit(String lWHUnit) {
	LWHUnit = lWHUnit;
}
public Double getLength() {
	return length;
}
public void setLength(Double length) {
	this.length = length;
}
public Double getWeight() {
	return weight;
}
public void setWeight(Double weight) {
	this.weight = weight;
}
public Double getWidth() {
	return width;
}
public void setWidth(Double width) {
	this.width = width;
}
public Double getHeight() {
	return height;
}
public void setHeight(Double height) {
	this.height = height;
}
public String getWeightUnit() {
	return weightUnit;
}
public void setWeightUnit(String weightUnit) {
	this.weightUnit = weightUnit;
}
public Double getMaxWeight() {
	return maxWeight;
}
public void setMaxWeight(Double maxWeight) {
	this.maxWeight = maxWeight;
}
public Integer getBearingLevel() {
	return bearingLevel;
}
public void setBearingLevel(Integer bearingLevel) {
	this.bearingLevel = bearingLevel;
}
public Boolean getOnlyBottom() {
	return onlyBottom;
}
public void setOnlyBottom(Boolean onlyBottom) {
	this.onlyBottom = onlyBottom;
}
public Boolean getOnlyTop() {
	return onlyTop;
}
public void setOnlyTop(Boolean onlyTop) {
	this.onlyTop = onlyTop;
}
public Integer getKitId() {
	return kitId;
}
public void setKitId(Integer kitId) {
	this.kitId = kitId;
}
public Integer getGroupId() {
	return groupId;
}
public void setGroupId(Integer groupId) {
	this.groupId = groupId;
}
public Boolean getAllowA() {
	return allowA;
}
public void setAllowA(Boolean allowA) {
	this.allowA = allowA;
}
public Boolean getSupportA() {
	return supportA;
}
public void setSupportA(Boolean supportA) {
	this.supportA = supportA;
}
public Integer getMaxLayerA() {
	return maxLayerA;
}
public void setMaxLayerA(Integer maxLayerA) {
	this.maxLayerA = maxLayerA;
}
public Integer getMaxOverHangA() {
	return maxOverHangA;
}
public void setMaxOverHangA(Integer maxOverHangA) {
	this.maxOverHangA = maxOverHangA;
}
@Override
public String toString() {
	return "MaterialData [materialCode=" + materialCode + ", materialName=" + materialName + ", colorCode=" + colorCode
			+ ", quantity=" + quantity + ", LWHUnit=" + LWHUnit + ", length=" + length + ", width=" + width
			+ ", height=" + height + ", weight=" + weight + ", weightUnit=" + weightUnit + ", maxWeight=" + maxWeight
			+ ", bearingLevel=" + bearingLevel + ", onlyBottom=" + onlyBottom + ", onlyTop=" + onlyTop + ", kitId="
			+ kitId + ", groupId=" + groupId + ", allowA=" + allowA + ", supportA=" + supportA + ", maxLayerA="
			+ maxLayerA + ", maxOverHangA=" + maxOverHangA + "]";
}


}
