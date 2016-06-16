package com.vision.query;

/**
 * Created by chenchen on 16/3/30.
 */
public enum OperatorEnum {
    EQUELS("=", "相等"), NOT_EQUELS

            ("<>", "不相等"), BIGGER(">", "大于"), BIGGER_EQUELS(">=", "大于等于"), SMALLER("<", "小于"), SMALLER_EQUELS("<=", "小于等于"), LIKE("like", "搜索");

    private String opertor;
    private String comment;

    public String getOpertor() {
        return opertor;
    }

    public void setOpertor(String opertor) {
        this.opertor = opertor;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    OperatorEnum(String operator, String comment) {
        this.opertor = operator;
        this.comment = comment;
    }
}
