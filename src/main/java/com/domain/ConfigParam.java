package com.domain;

public class ConfigParam {
private Double lenghGap;
private Double widthGap;
private Double heightGap;
private Double weightGap;
private String weightUnit;
private String LWHUnit;


public Double getLenghGap() {
	return lenghGap;
}

public void setLenghGap(Double lenghGap) {
	this.lenghGap = lenghGap;
}

public String getLWHUnit() {
	return LWHUnit;
}

public void setLWHUnit(String lWHUnit) {
	LWHUnit = lWHUnit;
}

public Double getWidthGap() {
	return widthGap;
}

public void setWidthGap(Double widthGap) {
	this.widthGap = widthGap;
}

public Double getHeightGap() {
	return heightGap;
}

public void setHeightGap(Double heightGap) {
	this.heightGap = heightGap;
}

public Double getWeightGap() {
	return weightGap;
}

public void setWeightGap(Double weightGap) {
	this.weightGap = weightGap;
}

public String getWeightUnit() {
	return weightUnit;
}

public void setWeightUnit(String weightUnit) {
	this.weightUnit = weightUnit;
}

@Override
public String toString() {
	return "ConfigParam [lenghGap=" + lenghGap + ", widthGap=" + widthGap + ", heightGap=" + heightGap + ", weightGap="
			+ weightGap + ", weightUnit=" + weightUnit + ", LWHUnit=" + LWHUnit + "]";
}

}
