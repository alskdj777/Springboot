package com.cmh.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cmh.mapper.NewsMapper;
import com.cmh.domain.News;
import com.cmh.service.INewsService;

/**
 * 行业动态Service业务层处理
 * 
 * @author 南风洛水
 * @date 2024-06-25
 */
@Service
public class NewsServiceImpl implements INewsService 
{
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 查询行业动态
     * 
     * @param id 行业动态主键
     * @return 行业动态
     */
    @Override
    public News selectNewsById(Long id)
    {
        return newsMapper.selectNewsById(id);
    }

    /**
     * 查询行业动态列表
     * 
     * @param news 行业动态
     * @return 行业动态
     */
    @Override
    public List<News> selectNewsList(News news)
    {
        return newsMapper.selectNewsList(news);
    }

    /**
     * 新增行业动态
     * 
     * @param news 行业动态
     * @return 结果
     */
    @Override
    public int insertNews(News news)
    {
        return newsMapper.insertNews(news);
    }

    /**
     * 修改行业动态
     * 
     * @param news 行业动态
     * @return 结果
     */
    @Override
    public int updateNews(News news)
    {
        return newsMapper.updateNews(news);
    }

    /**
     * 批量删除行业动态
     * 
     * @param ids 需要删除的行业动态主键
     * @return 结果
     */
    @Override
    public int deleteNewsByIds(Long[] ids)
    {
        return newsMapper.deleteNewsByIds(ids);
    }

    /**
     * 删除行业动态信息
     * 
     * @param id 行业动态主键
     * @return 结果
     */
    @Override
    public int deleteNewsById(Long id)
    {
        return newsMapper.deleteNewsById(id);
    }
}
