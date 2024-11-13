package com.cmh.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmh.mapper.TMeetingMapper;
import com.cmh.domain.TMeeting;
import com.cmh.service.ITMeetingService;

/**
 * 会议管理Service业务层处理
 * 
 * @author ltx
 * @date 2024-06-29
 */
@Service
public class TMeetingServiceImpl implements ITMeetingService 
{
    @Autowired
    private TMeetingMapper tMeetingMapper;

    /**
     * 查询会议管理
     * 
     * @param meetingid 会议管理主键
     * @return 会议管理
     */
    @Override
    public TMeeting selectTMeetingByMeetingid(Long meetingid)
    {
        return tMeetingMapper.selectTMeetingByMeetingid(meetingid);
    }

    /**
     * 查询会议管理列表
     * 
     * @param tMeeting 会议管理
     * @return 会议管理
     */
    @Override
    public List<TMeeting> selectTMeetingList(TMeeting tMeeting)
    {
        return tMeetingMapper.selectTMeetingList(tMeeting);
    }

    /**
     * 新增会议管理
     * 
     * @param tMeeting 会议管理
     * @return 结果
     */
    @Override
    public int insertTMeeting(TMeeting tMeeting)
    {
        return tMeetingMapper.insertTMeeting(tMeeting);
    }

    /**
     * 修改会议管理
     * 
     * @param tMeeting 会议管理
     * @return 结果
     */
    @Override
    public int updateTMeeting(TMeeting tMeeting)
    {
        return tMeetingMapper.updateTMeeting(tMeeting);
    }

    /**
     * 批量删除会议管理
     * 
     * @param meetingids 需要删除的会议管理主键
     * @return 结果
     */
    @Override
    public int deleteTMeetingByMeetingids(Long[] meetingids)
    {
        return tMeetingMapper.deleteTMeetingByMeetingids(meetingids);
    }

    /**
     * 删除会议管理信息
     * 
     * @param meetingid 会议管理主键
     * @return 结果
     */
    @Override
    public int deleteTMeetingByMeetingid(Long meetingid)
    {
        return tMeetingMapper.deleteTMeetingByMeetingid(meetingid);
    }
}
