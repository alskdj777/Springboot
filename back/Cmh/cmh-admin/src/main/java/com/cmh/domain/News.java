package com.cmh.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cmh.common.annotation.Excel;
import com.cmh.common.core.domain.BaseEntity;

/**
 * 行业动态对象 news
 *
 * @author 南风洛水
 * @date 2024-06-25
 */
public class News extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 新闻标题 */
    @Excel(name = "新闻标题")
    private String title;

    /** 新闻图片 */
    @Excel(name = "新闻图片")
    private String image;

    /** 新闻内容 */
    @Excel(name = "新闻内容")
    private String content;

    /** 新闻简介 */
    @Excel(name = "新闻简介")
    private String summary;

    /** 新闻作者 */
    @Excel(name = "新闻作者")
    private String author;

    /**
     * 创建者
     */
    private String titleKeyword; // 新增字段

    // Getter and Setter for titleKeyword
    public String getTitleKeyword() {
        return titleKeyword;
    }

    public void setTitleKeyword(String titleKeyword) {
        this.titleKeyword = titleKeyword;
    }

    private String createBy;

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
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getSummary()
    {
        return summary;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getAuthor()
    {
        return author;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("image", getImage())
            .append("content", getContent())
            .append("summary", getSummary())
            .append("author", getAuthor())
            .toString();
    }
}
