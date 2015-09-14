package com.vision.dao.config.entity;

import com.vision.dao.anotation.PrimaryKeyField.PrimaryKeyType;

public class PrimaryKeyFiledConfig extends EntityFieldConfig {
	private PrimaryKeyType primaryKeyType;

	public PrimaryKeyType getPrimaryKeyType() {
		return primaryKeyType;
	}

	public void setPrimaryKeyType(PrimaryKeyType primaryKeyType) {
		this.primaryKeyType = primaryKeyType;
	}
}
