package com.vision.util;

/**
 * Created by DELL on 2015/9/16.
 */
public class ColumnNameConvertor {
    public static String convertToEntityName(String databaseColumnName) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean nextCharNeedUp = false;
        for (int i = 0; i < databaseColumnName.length(); i++) {
            char c = databaseColumnName.charAt(i);
            if (c == '_') {
                nextCharNeedUp = true;
            } else {
                if (nextCharNeedUp) {
                    c = (char) (c - 32);
                    nextCharNeedUp = false;
                }
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static String convertToDatabaseColumnName(String entityColumnName) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < entityColumnName.length(); i++) {
            char c = entityColumnName.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + 32);
                if (i != 0) {
                    stringBuilder.append("_");
                }
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}
