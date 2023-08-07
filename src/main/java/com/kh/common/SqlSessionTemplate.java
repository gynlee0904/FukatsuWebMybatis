package com.kh.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionTemplate {
	
	public static SqlSession getSqlSession() {  //따로 객체 안만들고 쓰기위해 static 써줌 
		String resources = "mybatis-config.xml";
		SqlSession session = null;
		try {
			InputStream is = Resources.getResourceAsStream(resources);  //체크드익셉션
			SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();  //연결공장을 만드는사람
			SqlSessionFactory factory = builder.build(is);                      //연결공장을 만드는사람이 연결공장을 만듦 
			session = factory.openSession(); 									//연결공장에서는 연결을 열어줌 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
	}
}
