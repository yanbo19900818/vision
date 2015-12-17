package com.vision.dao.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * orm中entity的注解
 *
 * @author yanbo
 */
@Target(ElementType.TYPE)
public @interface Entity {
    public String databaseName();

    public String tableName();

    public boolean isSharding() default false;

    public String shardingScheme();
}
