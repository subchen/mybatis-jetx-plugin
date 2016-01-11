package com.demo.test;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionUtils {
	public static final SqlSessionFactory sqlSessionFactory;
	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static SqlSession openSession() {
		return sqlSessionFactory.openSession();
	}

	public static void closeSession(SqlSession session) {
		if (session != null) {
			session.close();
		}
	}
}
