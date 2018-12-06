package com.domain;

import java.util.ArrayList;

public class PackingDetailParam {
	private String pickPoint;
	private String planPickTime;
	private ArrayList<MaterialData> materialData;
	public String getPickPoint() {
		return pickPoint;
	}

	public void setPickPoint(String pickPoint) {
		this.pickPoint = pickPoint;
	}


	public String getPlanPickTime() {
		return planPickTime;
	}

	public void setPlanPickTime(String planPickTime) {
		this.planPickTime = planPickTime;
	}

	public ArrayList<MaterialData> getMaterialData() {
		return materialData;
	}

	public void setMaterialData(ArrayList<MaterialData> materialData) {
		this.materialData = materialData;
	}

	@Override
	public String toString() {
		return "PackingDetailParam [pickPoint=" + pickPoint + ", planPickTime=" + planPickTime + ", materialData="
				+ materialData + "]";
	}
	
}
