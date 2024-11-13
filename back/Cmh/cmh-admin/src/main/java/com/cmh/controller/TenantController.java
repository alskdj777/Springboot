package com.cmh.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cmh.common.annotation.Log;
import com.cmh.common.core.controller.BaseController;
import com.cmh.common.core.domain.AjaxResult;
import com.cmh.common.enums.BusinessType;
import com.cmh.domain.Tenant;
import com.cmh.service.ITenantService;
import com.cmh.common.utils.poi.ExcelUtil;
import com.cmh.common.core.page.TableDataInfo;

/**
 * 租户Controller
 *
 * @author 南风洛水
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/tenant/tenant")
public class TenantController extends BaseController
{
    @Autowired
    private ITenantService tenantService;

    /**
     * 查询租户列表
     */
    @PreAuthorize("@ss.hasPermi('tenant:tenant:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tenant tenant)
    {
        //管理员不做数据权限控制
        if (!getUsername().equals("admin")){
            tenant.setCreateBy(getUsername());
        }
        startPage();
        List<Tenant> list = tenantService.selectTenantList(tenant);
        return getDataTable(list);
    }

    /**
     * 导出租户列表
     */
    @PreAuthorize("@ss.hasPermi('tenant:tenant:export')")
    @Log(title = "租户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tenant tenant)
    {
        List<Tenant> list = tenantService.selectTenantList(tenant);
        ExcelUtil<Tenant> util = new ExcelUtil<Tenant>(Tenant.class);
        util.exportExcel(response, list, "租户数据");
    }

    /**
     * 获取租户详细信息
     */
    @PreAuthorize("@ss.hasPermi('tenant:tenant:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tenantService.selectTenantById(id));
    }

    /**
     * 新增租户
     */
    @PreAuthorize("@ss.hasPermi('tenant:tenant:add')")
    @Log(title = "租户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tenant tenant)
    {
        tenant.setCreateBy(getUsername());
        return toAjax(tenantService.insertTenant(tenant));
    }

    /**
     * 修改租户
     */
    @PreAuthorize("@ss.hasPermi('tenant:tenant:edit')")
    @Log(title = "租户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tenant tenant)
    {
        return toAjax(tenantService.updateTenant(tenant));
    }

    /**
     * 删除租户
     */
    @PreAuthorize("@ss.hasPermi('tenant:tenant:remove')")
    @Log(title = "租户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tenantService.deleteTenantByIds(ids));
    }
}
