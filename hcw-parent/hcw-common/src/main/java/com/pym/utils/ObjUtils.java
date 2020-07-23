package com.pym.utils;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * TODO
 *  对象工具类
 * @author zhangping
 * @version 1.0
 * @date 2020/7/14 16:01
 */
public class ObjUtils {
    /* *
     * @Description
     * 对象转json
     * @Param [obj]
     * @return java.lang.String
     * @Author zhangping
     * @Date 2020/7/14 16:03
     **/
    public static String toJson(Object obj) {
        return JSON.toJSONString(obj);
    }
    /* *
     * @Description
     * json转对象
     * @Param [json, cls]
     * @return T
     * @Author zhangping
     * @Date 2020/7/14 16:14
     **/
    public static <T> T jsonToObj(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }
    /* *
     * @Description
     * 判读对象是否为空 支持包装类 map 集合 字符串 数组
     * @Param [obj]
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/14 16:14
     **/
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        //包装类
        if (obj instanceof Number) {
            return false;
        }
        if (obj instanceof Character) {
            return ((Character)obj).toString().trim().isEmpty();
        }
        if (obj instanceof Boolean) {
            return false;
        }
        //字符串
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        //map
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        //处理集合
        if (obj instanceof Iterable) {
            return !((Iterable) obj).iterator().hasNext();
        }
        //数组
        if (obj instanceof Object[]) {
            return ((Object[]) obj).length == 0;
        }
        //基础类型数组
        if (obj instanceof int[]) {
            return ((int[]) obj).length == 0;
        }
        if (obj instanceof byte[]) {
            return ((byte[]) obj).length == 0;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj).length == 0;
        }
        if (obj instanceof long[]) {
            return ((long[]) obj).length == 0;
        }
        if (obj instanceof double[]) {
            return ((double[]) obj).length == 0;
        }
        if (obj instanceof float[]) {
            return ((float[]) obj).length == 0;
        }
        if (obj instanceof boolean[]) {
            return ((boolean[]) obj).length == 0;
        }
        if (obj instanceof char[]) {
            return ((char[]) obj).length == 0;
        }
        return false;
    }
    /* *
     * @Description
     * 判断一组对象是否全部不为空
     * @Param [objs]
     * @return boolean
     * @Author zhangping
     * @Date 2020/7/15 10:54
     **/
    public static boolean isAllNotEmpty(Object ... objs) {
        for (Object obj : objs) {
            if (isEmpty(obj)) {
                return false;
            }
        }
        return true;
    }

}
