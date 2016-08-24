package com.vision.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.sql.JDBCType;

@Target(ElementType.FIELD)
public @interface PrimaryKeyField {
    PrimaryKeyType primaryKeyType() default PrimaryKeyType.AUTO_INCREASE;

    enum PrimaryKeyType {
        AUTO_INCREASE, SEQUENCE;
    }
}
