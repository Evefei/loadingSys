package com.domain;


import java.util.ArrayList;

import org.python.core.PyObject;
import org.python.core.PyType;

public class GenerateLoadingParam extends PyObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer uid;
	private Integer assign_code;
	private ArrayList<PackingDetailParam> packingDetail;
	//private PackingDetailParam[] packingDetail;
	private VehicleData vehicleData;
	private ConfigParam configParam;
	
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getAssign_code() {
		return assign_code;
	}
	public void setAssign_code(Integer assign_code) {
		this.assign_code = assign_code;
	}
	
	
	public ArrayList<PackingDetailParam> getPackingDetail() {
		return packingDetail;
	}
	public void setPackingDetail(ArrayList<PackingDetailParam> packingDetail) {
		this.packingDetail = packingDetail;
	}
	public VehicleData getVehicleData() {
		return vehicleData;
	}
	public void setVehicleData(VehicleData vehicleData) {
		this.vehicleData = vehicleData;
	}
	public ConfigParam getConfigParam() {
		return configParam;
	}
	public void setConfigParam(ConfigParam configParam) {
		this.configParam = configParam;
	}
	
	@Override
	public String toString() {
		return "GenerateLoadingParam [uid=" + uid + ", assign_code=" + assign_code + ", packingDetail=" + packingDetail
				+ ", vehicleData=" + vehicleData + ", configParam=" + configParam + "]";
	}

}
