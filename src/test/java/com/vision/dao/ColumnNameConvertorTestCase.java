package com.vision.dao;

import com.vision.dao.util.ColumnNameConvertor;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by chenchen on 15/12/20.
 */
public class ColumnNameConvertorTestCase {
    @Test
    public void testConvertToEntityName() {
        String entityNameString = ColumnNameConvertor.convertToEntityName("user_id");
        Assert.assertTrue("userId".equals(entityNameString));

    }

    @Test
    public void testConvertToDatabaseColumnName() {
        String dbString = ColumnNameConvertor.convertToDatabaseColumnName("userId");
        Assert.assertTrue("user_id".equals(dbString));
    }
}
