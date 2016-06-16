package com.vision.config.entity;

import com.vision.anotation.PrimaryKeyField.PrimaryKeyType;

public class PrimaryKeyFiledConfig extends EntityFieldConfig {
	private PrimaryKeyType primaryKeyType;

	public PrimaryKeyType getPrimaryKeyType() {
		return primaryKeyType;
	}

	public void setPrimaryKeyType(PrimaryKeyType primaryKeyType) {
		this.primaryKeyType = primaryKeyType;
	}
}
