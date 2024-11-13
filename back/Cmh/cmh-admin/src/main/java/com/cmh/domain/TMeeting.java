package com.cmh.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.cmh.common.annotation.Excel;
import com.cmh.common.core.domain.BaseEntity;

/**
 * 会议管理对象 t_meeting
 * 
 * @author ltx
 * @date 2024-06-29
 */
public class TMeeting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会议ID */
    @Excel(name = "会议ID")
    private Long meetingid;

    /** 会议名称 */
    @Excel(name = "会议名称")
    private String meetingname;

    /** 会议封面 */
    @Excel(name = "会议封面")
    private String cover;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creator;

    /** 会议状态 */
    @Excel(name = "会议状态")
    private String status;

    /** 会议内容 */
    @Excel(name = "会议内容")
    private String content;

    /** 开始时间 */
    @Excel(name = "开始时间")
    private String starttime;

    /** 结束时间 */
    private String endtime;

    public void setMeetingid(Long meetingid) 
    {
        this.meetingid = meetingid;
    }

    public Long getMeetingid() 
    {
        return meetingid;
    }
    public void setMeetingname(String meetingname) 
    {
        this.meetingname = meetingname;
    }

    public String getMeetingname() 
    {
        return meetingname;
    }
    public void setCover(String cover) 
    {
        this.cover = cover;
    }

    public String getCover() 
    {
        return cover;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setStarttime(String starttime) 
    {
        this.starttime = starttime;
    }

    public String getStarttime() 
    {
        return starttime;
    }
    public void setEndtime(String endtime) 
    {
        this.endtime = endtime;
    }

    public String getEndtime() 
    {
        return endtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("meetingid", getMeetingid())
            .append("meetingname", getMeetingname())
            .append("cover", getCover())
            .append("creator", getCreator())
            .append("status", getStatus())
            .append("content", getContent())
            .append("starttime", getStarttime())
            .append("endtime", getEndtime())
            .toString();
    }
}
