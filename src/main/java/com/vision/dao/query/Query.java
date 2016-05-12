package com.vision.dao.query;

import com.vision.dao.config.entity.EntityConfig;
import com.vision.dao.manager.EntityConfigManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenchen on 16/3/30.
 */
public class Query {
    private List<Condition> conditions = new ArrayList<>();
    private String entityName;
    private EntityConfigManager entityConfigManager;

    public Query(Class clazz) {
        entityConfigManager.getEntityConfig(clazz.getName());
    }

    /**
     * 添加条件,默认采用And条件
     *
     * @param operator
     * @param fieldName
     * @param value
     */
    public void addCondition(OperatorEnum operator, String fieldName, Object value) {
        conditions.add(new Condition(fieldName, value, operator, ConditionTypeEnum.AND));
    }

    /**
     * 添加条件
     *
     * @param operator
     * @param fieldName
     * @param value
     * @param conditionType and 还是 or 条件
     */
    public void addCondition(OperatorEnum operator, String fieldName, Object value, ConditionTypeEnum conditionType) {
        conditions.add(new Condition(fieldName, value, operator, conditionType));
    }

    public String toSQL() {
        if (conditions.size() <= 0) return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Where ");
        for (int i = 0; i < conditions.size(); i++) {
            Condition condition = conditions.get(i);
            if (i != 0) {
                stringBuilder.append(condition.getType().getCode()).append(" ");
            }
            if (condition.getValue() instanceof String) {
                stringBuilder.append(condition.getField()).append(condition.getOperator().getOpertor()).append("'").append(condition.getValue()).append("'");
            } else {
                stringBuilder.append(condition.getField()).append(condition.getOperator().getOpertor()).append(condition.getValue()).append(" ");
            }
        }
        return stringBuilder.toString();
    }

}
