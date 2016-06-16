package com.vision.query;

/**
 * 查询条件类
 * Created by chenchen on 16/3/30.
 */
public class Condition {

    private String field;
    private Object value;
    private OperatorEnum operator;
    private ConditionTypeEnum type;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public OperatorEnum getOperator() {
        return operator;
    }

    public void setOperator(OperatorEnum operator) {
        this.operator = operator;
    }

    public ConditionTypeEnum getType() {
        return type;
    }

    public void setType(ConditionTypeEnum type) {
        this.type = type;
    }

    public Condition(String field, Object value, OperatorEnum operator, ConditionTypeEnum type) {
        this.field = field;
        this.value = value;
        this.operator = operator;
        this.type = type;
    }
}
