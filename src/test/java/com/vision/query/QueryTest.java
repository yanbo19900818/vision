package com.vision.query;

import com.vision.wrapper.TestObject;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by chenchen on 16/4/4.
 */
public class QueryTest {
    @Test
    public void testToString() {
        Query query
                = new Query(TestObject.class);
        query.addCondition(OperatorEnum.EQUELS, "id", 1, ConditionTypeEnum.AND);
        query.addCondition(OperatorEnum.EQUELS, "username", "yanbo", ConditionTypeEnum.OR);
        String sql = query.toSQL();
        Assert.assertEquals("Where id=1 OR username='yanbo'", sql);
    }
}
