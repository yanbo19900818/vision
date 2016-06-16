package com.vision.query;

/**
 * Created by chenchen on 16/3/30.
 */
public enum ConditionTypeEnum {
    OR("OR", ""), AND("AND", "");
    private String code;
    private String comment;

    ConditionTypeEnum(String code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
