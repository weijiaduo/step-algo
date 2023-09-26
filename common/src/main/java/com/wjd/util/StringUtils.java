package com.wjd.util;

import java.util.Arrays;

/**
 * 字符串工具类
 *
 * @author weijiaduo
 * @since 2022/10/3
 */
public final class StringUtils {

    public static String toStr(Object object) {
        if (object == null) {
            return "null";
        }

        // 非数组对象
        if (!object.getClass().isArray()) {
            return object.toString();
        }

        // 基本类型
        if (object instanceof byte[]) {
            return Arrays.toString((byte[]) object);
        }
        if (object instanceof boolean[]) {
            return Arrays.toString((boolean[]) object);
        }
        if (object instanceof char[]) {
            return Arrays.toString((char[]) object);
        }
        if (object instanceof short[]) {
            return Arrays.toString((short[]) object);
        }
        if (object instanceof int[]) {
            return Arrays.toString((int[]) object);
        }
        if (object instanceof long[]) {
            return Arrays.toString((long[]) object);
        }
        if (object instanceof float[]) {
            return Arrays.toString((float[]) object);
        }
        if (object instanceof double[]) {
            return Arrays.toString((double[]) object);
        }

        // 引用类型
        Object[] objects = (Object[]) object;
        int n = objects.length;
        if (n == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object obj : objects) {
            sb.append(toStr(obj)).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

}
