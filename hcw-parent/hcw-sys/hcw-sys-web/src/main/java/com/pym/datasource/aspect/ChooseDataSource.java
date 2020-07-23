package com.pym.datasource.aspect;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * TODO
 * 配合@Choose注解使用
 * 如果不使用注解优先采用读数据源
 * @author zhangping
 * @version 1.0
 * @date 2020/7/3 23:36
 */
public class ChooseDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return HandleDataSource.getDataSource();
    }
}
