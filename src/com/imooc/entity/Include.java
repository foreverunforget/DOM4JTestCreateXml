package com.imooc.entity;
//
public class Include {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
				private String name;
                private String type;
                private String path;
                //no 指的是系統文件，yes指的是用戶自定義文件
                private String local;
				public String getLocal() {
					return local;
				}
				public void setLocal(String local) {
					this.local = local;
				}
				public String getName() {
					return name;
				}
				public void setName(String name) {
					this.name = name;
				}
				public String getType() {
					return type;
				}
				public void setType(String type) {
					this.type = type;
				}
				public String getPath() {
					return path;
				}
				public void setPath(String path) {
					this.path = path;
				}
}
