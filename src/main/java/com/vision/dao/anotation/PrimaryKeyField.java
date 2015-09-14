package com.vision.dao.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface PrimaryKeyField {
	public PrimaryKeyType primaryKeyType() default PrimaryKeyType.PRIMARY_KEY;

	public String colName();

	public String colType();

	public enum PrimaryKeyType {
		AUTO_INCREASE, PRIMARY_KEY;
	}
}
