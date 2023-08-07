package com.kh.notice.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.SqlSessionTemplate;
import com.kh.notice.model.dao.NoticeDAO;
import com.kh.notice.model.vo.Notice;
import com.kh.notice.model.vo.PageData;


//서비스에서는 연결 생성, Dao 호출, 커밋롤백, 연결해제 해줌 
	//DAO 호출을 위한 nDao 필드 필요 

public class NoticeService {
	private NoticeDAO nDao;
	
	public NoticeService() {
		//필드는 생성자에서 초기화해줌
		nDao = new NoticeDAO();
	}
	
	
	public int insertNotice(Notice notice) {
	//마이바티스에서는 SqlSession 이용
	//SqlSessionTemplate는 mybatis-config.xml파일을 참조하여 SqlSession연결을 생성함 
	//연결고앙빌더가 연결공장을 만들면 연결이 생성됨 
	
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = nDao.insertNotice(session, notice);
		//커밋, 롤백은 DML인 경우에만 하는것임 
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}session.close();
		return result;  //리턴값은 널이나 0으로 두면 안됨. 0->result, null->nList or notice 로 반환  
	}



	public int deleteNoticeByNo(int noticeNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = nDao.deleteNoticeByNo(session, noticeNo);
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}session.close();
		return result;
	}


	public int updateNotice(Notice notice) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		int result = nDao.updateNotice(session, notice);
		//커밋, 롤백은 DML인 경우에만 하는것임 
		if(result > 0) {
			session.commit();
		}else {
			session.rollback();
		}session.close();
		return result;
	}


//	public List<Notice> selectNoticeList(int currentPage) {
//		//서비스, DAO, mapper.xml순으로 코딩 
//		//서비스는 연결생성, DAO호출, 연결해제 
//		SqlSession session = SqlSessionTemplate.getSqlSession();
//		List<Notice>nList = nDao.selectNoticeList(session, currentPage);
//		session.close();
//		return nList; 
//	}
	
	public PageData selectNoticeList(int currentPage) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		List<Notice>nList = nDao.selectNoticeList(session, currentPage);
		String pageNavi = nDao.generatePageNavi(currentPage);
		PageData pd = new PageData();
		session.close();
		return pd; 
	}


	public Notice selectOneByNo(int noticeNo) {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		Notice notice = nDao.selectOneByNo(session, noticeNo);
		session.close();
		return notice;
	
	}

}
