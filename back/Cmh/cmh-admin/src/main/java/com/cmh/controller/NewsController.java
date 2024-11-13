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
import com.cmh.domain.News;
import com.cmh.service.INewsService;
import com.cmh.common.utils.poi.ExcelUtil;
import com.cmh.common.core.page.TableDataInfo;

/**
 * 行业动态Controller
 *
 * @author 南风洛水
 * @date 2024-06-25
 */
@RestController
@RequestMapping("/news/news")
public class NewsController extends BaseController
{
    @Autowired
    private INewsService newsService;

    /**
     * 查询行业动态列表
     */
    @PreAuthorize("@ss.hasPermi('news:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(News news)
    {
        //管理员不做数据权限控制
        if (!getUsername().equals("admin")){
            news.setCreateBy(getUsername());
        }
        startPage();
        List<News> list = newsService.selectNewsList(news);
        return getDataTable(list);
    }

    /**
     * 导出行业动态列表
     */
    @PreAuthorize("@ss.hasPermi('news:news:export')")
    @Log(title = "行业动态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, News news)
    {
        List<News> list = newsService.selectNewsList(news);
        ExcelUtil<News> util = new ExcelUtil<News>(News.class);
        util.exportExcel(response, list, "行业动态数据");
    }

    /**
     * 获取行业动态详细信息
     */
    @PreAuthorize("@ss.hasPermi('news:news:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(newsService.selectNewsById(id));
    }

    /**
     * 新增行业动态
     */
    @PreAuthorize("@ss.hasPermi('news:news:add')")
    @Log(title = "行业动态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody News news)
    {
        news.setCreateBy(getUsername());
        return toAjax(newsService.insertNews(news));
    }

    /**
     * 修改行业动态
     */
    @PreAuthorize("@ss.hasPermi('news:news:edit')")
    @Log(title = "行业动态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody News news)
    {
        return toAjax(newsService.updateNews(news));
    }

    /**
     * 删除行业动态
     */
    @PreAuthorize("@ss.hasPermi('news:news:remove')")
    @Log(title = "行业动态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(newsService.deleteNewsByIds(ids));
    }
}
