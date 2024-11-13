package com.cmh.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cmh.common.annotation.Excel;
import com.cmh.common.core.domain.BaseEntity;

/**
 * 租户对象 tenant
 * 
 * @author 南风洛水
 * @date 2024-06-25
 */
public class Tenant extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 租户编号 */
    private Long id;

    /** 租户名称 */
    @Excel(name = "租户名称")
    private String name;

    /** 租户图标 */
    @Excel(name = "租户图标")
    private String icon;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contact;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 管理员 */
    @Excel(name = "管理员")
    private String admin;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIcon(String icon) 
    {
        this.icon = icon;
    }

    public String getIcon() 
    {
        return icon;
    }
    public void setContact(String contact) 
    {
        this.contact = contact;
    }

    public String getContact() 
    {
        return contact;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setAdmin(String admin) 
    {
        this.admin = admin;
    }

    public String getAdmin() 
    {
        return admin;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("icon", getIcon())
            .append("contact", getContact())
            .append("phone", getPhone())
            .append("admin", getAdmin())
            .append("notes", getNotes())
            .toString();
    }
}
