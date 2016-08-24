package com.vision.config.database;

import lombok.Data;

@Data
public class DataBaseColumnConfig {
	private String columnName;
	private String dataType;
	private String typeName;
	private boolean isPrimaryKey;
}
