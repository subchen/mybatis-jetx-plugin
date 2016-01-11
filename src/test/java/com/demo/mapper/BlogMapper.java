package com.demo.mapper;

import com.demo.domain.Blog;

import java.util.List;

import jetbrick.template.scripting.JetxLanguageDriver;

import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BlogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Blog record);

	Blog selectByPrimaryKey(Integer id);

	List<Blog> selectAll();

	int updateByPrimaryKey(Blog record);

	// jetx lang
	@Lang(JetxLanguageDriver.class)
	@Select("select * from blog where id in (${join(ids,',')})")
	List<Blog> findBlogsByIds(@Param("ids") List<Integer> ids);

	// jetx lang
	@Lang(JetxLanguageDriver.class)
	@Select("findBlogsByTitle")
	List<Blog> findBlogsByTitle(@Param("title") String title);
}