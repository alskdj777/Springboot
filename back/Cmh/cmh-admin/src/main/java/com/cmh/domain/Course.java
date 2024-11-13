package com.cmh.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cmh.common.annotation.Excel;
import com.cmh.common.core.domain.BaseEntity;

/**
 * 课程对象 course
 *
 * @author 南风洛水
 * @date 2024-06-25
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程编号 */
    private Long id;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String title;

    /** 课程封面 */
    @Excel(name = "课程封面")
    private String cover;

    /** 课程简介 */
    @Excel(name = "课程简介")
    private String description;

    /** 课程排序 */
    @Excel(name = "课程排序")
    private Long orders;

    /** 课程作者 */
    @Excel(name = "课程作者")
    private String author;

    /** 课程视频 */
    @Excel(name = "课程视频")
    private String video;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setCover(String cover)
    {
        this.cover = cover;
    }

    public String getCover()
    {
        return cover;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setOrders(Long orders)
    {
        this.orders = orders;
    }

    public Long getOrders()
    {
        return orders;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("cover", getCover())
            .append("description", getDescription())
            .append("orders", getOrders())
            .append("author", getAuthor())
            .append("video",getVideo())
            .toString();
    }
}
