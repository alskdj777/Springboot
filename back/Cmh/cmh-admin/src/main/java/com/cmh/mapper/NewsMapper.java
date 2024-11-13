package com.cmh.mapper;

import java.util.List;
import com.cmh.domain.News;

/**
 * 行业动态Mapper接口
 * 
 * @author 南风洛水
 * @date 2024-06-25
 */
public interface NewsMapper 
{
    /**
     * 查询行业动态
     * 
     * @param id 行业动态主键
     * @return 行业动态
     */
    public News selectNewsById(Long id);

    /**
     * 查询行业动态列表
     * 
     * @param news 行业动态
     * @return 行业动态集合
     */
    public List<News> selectNewsList(News news);

    /**
     * 新增行业动态
     * 
     * @param news 行业动态
     * @return 结果
     */
    public int insertNews(News news);

    /**
     * 修改行业动态
     * 
     * @param news 行业动态
     * @return 结果
     */
    public int updateNews(News news);

    /**
     * 删除行业动态
     * 
     * @param id 行业动态主键
     * @return 结果
     */
    public int deleteNewsById(Long id);

    /**
     * 批量删除行业动态
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsByIds(Long[] ids);
}
