package com.vision.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface EntityField {
    public String databaseColumnName();

    public String databaseColumnType();
}