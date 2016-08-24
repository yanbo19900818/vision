package com.vision.config.entity;

import com.vision.anotation.PrimaryKeyField.PrimaryKeyType;
import lombok.Data;

@Data
public class PrimaryKeyFiledConfig extends EntityFieldConfig {
    private PrimaryKeyType primaryKeyType;

}
