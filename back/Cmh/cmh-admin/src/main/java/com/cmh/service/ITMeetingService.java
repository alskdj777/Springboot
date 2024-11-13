package com.cmh.service;

import java.util.List;
import com.cmh.domain.TMeeting;

/**
 * 会议管理Service接口
 * 
 * @author ltx
 * @date 2024-06-29
 */
public interface ITMeetingService 
{
    /**
     * 查询会议管理
     * 
     * @param meetingid 会议管理主键
     * @return 会议管理
     */
    public TMeeting selectTMeetingByMeetingid(Long meetingid);

    /**
     * 查询会议管理列表
     * 
     * @param tMeeting 会议管理
     * @return 会议管理集合
     */
    public List<TMeeting> selectTMeetingList(TMeeting tMeeting);

    /**
     * 新增会议管理
     * 
     * @param tMeeting 会议管理
     * @return 结果
     */
    public int insertTMeeting(TMeeting tMeeting);

    /**
     * 修改会议管理
     * 
     * @param tMeeting 会议管理
     * @return 结果
     */
    public int updateTMeeting(TMeeting tMeeting);

    /**
     * 批量删除会议管理
     * 
     * @param meetingids 需要删除的会议管理主键集合
     * @return 结果
     */
    public int deleteTMeetingByMeetingids(Long[] meetingids);

    /**
     * 删除会议管理信息
     * 
     * @param meetingid 会议管理主键
     * @return 结果
     */
    public int deleteTMeetingByMeetingid(Long meetingid);
}
