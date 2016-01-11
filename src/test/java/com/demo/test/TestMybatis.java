package com.demo.test;

import java.util.Arrays;
import java.util.List;

import jetbrick.template.JetEngine;
import jetbrick.template.scripting.JetEngineHolder;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.demo.domain.Blog;
import com.demo.mapper.BlogMapper;

public class TestMybatis {
	SqlSession session;
	BlogMapper mapper;

	@Test
	public void findBlogsByIds() {
		List<Blog> b = mapper.findBlogsByIds(Arrays.asList(Integer.valueOf(7), Integer.valueOf(8)));
		System.out.println(JSON.toJSONString(b));
	}

	@Test
	public void findBlogsByTitle() {
		List<Blog> b = mapper.findBlogsByTitle("test");
		System.out.println(JSON.toJSONString(b));
	}

	@After
	public void a() {
		System.out.println("close session");
		SqlSessionUtils.closeSession(session);
	}

	@Before
	public void b() {
		System.out.println("createJetEngine");
		JetEngineHolder.setJetEngine(JetEngine.create());
		System.out.println("open session");
		session = SqlSessionUtils.openSession();
		mapper = session.getMapper(BlogMapper.class);
	}
}
