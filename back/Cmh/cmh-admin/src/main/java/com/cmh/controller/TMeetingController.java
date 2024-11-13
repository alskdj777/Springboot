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
import com.cmh.domain.TMeeting;
import com.cmh.service.ITMeetingService;
import com.cmh.common.utils.poi.ExcelUtil;
import com.cmh.common.core.page.TableDataInfo;

/**
 * 会议管理Controller
 * 
 * @author ltx
 * @date 2024-06-29
 */
@RestController
@RequestMapping("/meetmange/meeting")
public class TMeetingController extends BaseController
{
    @Autowired
    private ITMeetingService tMeetingService;

    /**
     * 查询会议管理列表
     */
//    @PreAuthorize("@ss.hasPermi('meetmange:meeting:list')")
    @GetMapping("/list")
    public TableDataInfo list(TMeeting tMeeting)
    {
        startPage();
        List<TMeeting> list = tMeetingService.selectTMeetingList(tMeeting);
        return getDataTable(list);
    }

    /**
     * 导出会议管理列表
     */
//    @PreAuthorize("@ss.hasPermi('meetmange:meeting:export')")
    @Log(title = "会议管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TMeeting tMeeting)
    {
        List<TMeeting> list = tMeetingService.selectTMeetingList(tMeeting);
        ExcelUtil<TMeeting> util = new ExcelUtil<TMeeting>(TMeeting.class);
        util.exportExcel(response, list, "会议管理数据");
    }

    /**
     * 获取会议管理详细信息
     */
//    @PreAuthorize("@ss.hasPermi('meetmange:meeting:query')")
    @GetMapping(value = "/{meetingid}")
    public AjaxResult getInfo(@PathVariable("meetingid") Long meetingid)
    {
        return success(tMeetingService.selectTMeetingByMeetingid(meetingid));
    }

    /**
     * 新增会议管理
     */
//    @PreAuthorize("@ss.hasPermi('meetmange:meeting:add')")
    @Log(title = "会议管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TMeeting tMeeting)
    {
        return toAjax(tMeetingService.insertTMeeting(tMeeting));
    }

    /**
     * 修改会议管理
     */
//    @PreAuthorize("@ss.hasPermi('meetmange:meeting:edit')")
    @Log(title = "会议管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TMeeting tMeeting)
    {
        return toAjax(tMeetingService.updateTMeeting(tMeeting));
    }

    /**
     * 删除会议管理
     */
//    @PreAuthorize("@ss.hasPermi('meetmange:meeting:remove')")
    @Log(title = "会议管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{meetingids}")
    public AjaxResult remove(@PathVariable Long[] meetingids)
    {
        return toAjax(tMeetingService.deleteTMeetingByMeetingids(meetingids));
    }
}


