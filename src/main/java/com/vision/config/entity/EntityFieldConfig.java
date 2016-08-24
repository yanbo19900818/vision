package com.vision.config.entity;

import lombok.Data;
import net.sf.cglib.reflect.FastMethod;

import java.lang.reflect.Field;

@Data
public class EntityFieldConfig {
    Field field;
    FastMethod setMethod;
    FastMethod getMethod;
}
