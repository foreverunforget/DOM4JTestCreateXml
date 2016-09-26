package com.imooc.entity;

public class FileRelation {
	private String startpoint;
	private String endpoint;
	public String getStartpoint() {
		return startpoint;
	}
	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}
	public String getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
	public boolean equals(FileRelation o){
		if(o.getStartpoint().equals(startpoint)&&o.getEndpoint().equals(endpoint))
		return true;
		return false;
	}
}
