package com.vision.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
public @interface PrimaryKeyField {
    PrimaryKeyType primaryKeyType() default PrimaryKeyType.PRIMARY_KEY;

    String colName() default "";

    String colType() default "";

    enum PrimaryKeyType {
        AUTO_INCREASE, PRIMARY_KEY;
    }
}
