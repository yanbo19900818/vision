package com.vision.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.sql.JDBCType;

@Target(ElementType.FIELD)
public @interface EntityField {
     String databaseColumnName();

     JDBCType databaseColumnType();
}
