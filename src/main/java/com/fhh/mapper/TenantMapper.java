package com.fhh.mapper;

import com.fhh.pojo.Tenant;

public interface TenantMapper {
    int deleteByPrimaryKey(String tenantId);

    int insert(Tenant record);

    int insertSelective(Tenant record);

    Tenant selectByPrimaryKey(String tenantId);

    int updateByPrimaryKeySelective(Tenant record);

    int updateByPrimaryKey(Tenant record);
}