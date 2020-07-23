package com.pym.datasource.aspect;

/**
 * TODO
 *
 * @author zhangping
 * @version 1.0
 * @date 2020/7/4 1:10
 */
public class HandleDataSource {
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void putDataSource(String datasource) {
        holder.set(datasource);
    }

    public static String getDataSource() {
        return holder.get();
    }

    public static void clear() {
        holder.remove();
    }
}
