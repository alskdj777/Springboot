package com.cmh.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmh.mapper.TenantMapper;
import com.cmh.domain.Tenant;
import com.cmh.service.ITenantService;

/**
 * 租户Service业务层处理
 * 
 * @author 南风洛水
 * @date 2024-06-25
 */
@Service
public class TenantServiceImpl implements ITenantService 
{
    @Autowired
    private TenantMapper tenantMapper;

    /**
     * 查询租户
     * 
     * @param id 租户主键
     * @return 租户
     */
    @Override
    public Tenant selectTenantById(Long id)
    {
        return tenantMapper.selectTenantById(id);
    }

    /**
     * 查询租户列表
     * 
     * @param tenant 租户
     * @return 租户
     */
    @Override
    public List<Tenant> selectTenantList(Tenant tenant)
    {
        return tenantMapper.selectTenantList(tenant);
    }

    /**
     * 新增租户
     * 
     * @param tenant 租户
     * @return 结果
     */
    @Override
    public int insertTenant(Tenant tenant)
    {
        return tenantMapper.insertTenant(tenant);
    }

    /**
     * 修改租户
     * 
     * @param tenant 租户
     * @return 结果
     */
    @Override
    public int updateTenant(Tenant tenant)
    {
        return tenantMapper.updateTenant(tenant);
    }

    /**
     * 批量删除租户
     * 
     * @param ids 需要删除的租户主键
     * @return 结果
     */
    @Override
    public int deleteTenantByIds(Long[] ids)
    {
        return tenantMapper.deleteTenantByIds(ids);
    }

    /**
     * 删除租户信息
     * 
     * @param id 租户主键
     * @return 结果
     */
    @Override
    public int deleteTenantById(Long id)
    {
        return tenantMapper.deleteTenantById(id);
    }
}
