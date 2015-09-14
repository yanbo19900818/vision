package com.vision.dao.config.entity;

import org.apache.commons.lang3.StringUtils;

import com.vision.dao.anotation.EntityField;

public class EntityFieldConfig {
	private String dbColName;
	private String dbColType;
	private String entityColName;
	private String entityColType;

	public String getDbColName() {
		return dbColName;
	}

	public void setDbColName(String dbColName) {
		this.dbColName = dbColName;
	}

	public String getDbColType() {
		return dbColType;
	}

	public void setDbColType(String dbColType) {
		this.dbColType = dbColType;
	}

	public String getEntityColName() {
		return entityColName;
	}

	public void setEntityColName(String entityColName) {
		this.entityColName = entityColName;
	}

	public String getEntityColType() {
		return entityColType;
	}

	public void setEntityColType(String entityColType) {
		this.entityColType = entityColType;
	}

}
