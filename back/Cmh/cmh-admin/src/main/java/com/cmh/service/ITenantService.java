package com.cmh.service;

import java.util.List;
import com.cmh.domain.Tenant;

/**
 * 租户Service接口
 * 
 * @author 南风洛水
 * @date 2024-06-25
 */
public interface ITenantService 
{
    /**
     * 查询租户
     * 
     * @param id 租户主键
     * @return 租户
     */
    public Tenant selectTenantById(Long id);

    /**
     * 查询租户列表
     * 
     * @param tenant 租户
     * @return 租户集合
     */
    public List<Tenant> selectTenantList(Tenant tenant);

    /**
     * 新增租户
     * 
     * @param tenant 租户
     * @return 结果
     */
    public int insertTenant(Tenant tenant);

    /**
     * 修改租户
     * 
     * @param tenant 租户
     * @return 结果
     */
    public int updateTenant(Tenant tenant);

    /**
     * 批量删除租户
     * 
     * @param ids 需要删除的租户主键集合
     * @return 结果
     */
    public int deleteTenantByIds(Long[] ids);

    /**
     * 删除租户信息
     * 
     * @param id 租户主键
     * @return 结果
     */
    public int deleteTenantById(Long id);
}