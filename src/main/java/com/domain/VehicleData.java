package com.domain;

public class VehicleData {
private String carLicenseNo;
private String carType;
private String carTypeCode;
private Double length;
private Double width;
private Double height;
private Double vehicleVolume;
private Double vehicleWeight;
private Double underPanHeight;
private Double crankHeight;
private Double crankWidth;
public String getCarLicenseNo() {
	return carLicenseNo;
}
public void setCarLicenseNo(String carLicenseNo) {
	this.carLicenseNo = carLicenseNo;
}
public String getCarType() {
	return carType;
}
public void setCarType(String carType) {
	this.carType = carType;
}
public String getCarTypeCode() {
	return carTypeCode;
}
public void setCarTypeCode(String carTypeCode) {
	this.carTypeCode = carTypeCode;
}

public Double getLength() {
	return length;
}
public void setLength(Double length) {
	this.length = length;
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
public Double getVehicleVolume() {
	return vehicleVolume;
}
public void setVehicleVolume(Double vehicleVolume) {
	this.vehicleVolume = vehicleVolume;
}
public Double getVehicleWeight() {
	return vehicleWeight;
}
public void setVehicleWeight(Double vehicleWeight) {
	this.vehicleWeight = vehicleWeight;
}
public Double getUnderPanHeight() {
	return underPanHeight;
}
public void setUnderPanHeight(Double underPanHeight) {
	this.underPanHeight = underPanHeight;
}
public Double getCrankHeight() {
	return crankHeight;
}
public void setCrankHeight(Double crankHeight) {
	this.crankHeight = crankHeight;
}
public Double getCrankWidth() {
	return crankWidth;
}
public void setCrankWidth(Double crankWidth) {
	this.crankWidth = crankWidth;
}



@Override
public String toString() {
	return "VehicleData [carLicenseNo=" + carLicenseNo + ", carType=" + carType + ", carTypeCode=" + carTypeCode
			+ ", length=" + length + ", width=" + width + ", height=" + height + ", vehicleVolume=" + vehicleVolume
			+ ", vehicleWeight=" + vehicleWeight + ", underPanHeight=" + underPanHeight + ", crankHeight=" + crankHeight
			+ ", crankWidth=" + crankWidth + "]";
}



}
