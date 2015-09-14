package com.vision.dao.config.entity;

import java.util.List;

public class EntityConfig {
	private String DBName;
	private String tableName;
	private boolean isSharding;
	private String shardingScheme;
	/**
	 * 主键
	 */
	private PrimaryKeyFiledConfig primaryKeyFiledConfig;
	/**
	 * 字段
	 */
	List<EntityFieldConfig> entityFieldConfigs;

	public String getDBName() {
		return DBName;
	}

	public void setDBName(String dBName) {
		DBName = dBName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public boolean isSharding() {
		return isSharding;
	}

	public void setSharding(boolean isSharding) {
		this.isSharding = isSharding;
	}

	public String getShardingScheme() {
		return shardingScheme;
	}

	public void setShardingScheme(String shardingScheme) {
		this.shardingScheme = shardingScheme;
	}

	public PrimaryKeyFiledConfig getPrimaryKeyFiledConfig() {
		return primaryKeyFiledConfig;
	}

	public void setPrimaryKeyFiledConfig(PrimaryKeyFiledConfig primaryKeyFiledConfig) {
		this.primaryKeyFiledConfig = primaryKeyFiledConfig;
	}

	public List<EntityFieldConfig> getEntityFieldConfigs() {
		return entityFieldConfigs;
	}

	public void setEntityFieldConfigs(List<EntityFieldConfig> entityFieldConfigs) {
		this.entityFieldConfigs = entityFieldConfigs;
	}

}
