package com.vision.dao.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface EntityField {
	public String colName();

	public String colType();
}
