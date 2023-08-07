package com.kh.notice.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.notice.model.vo.Notice;

public class NoticeDAO {

	public int insertNotice(SqlSession session, Notice notice) {
		//JDBC와 다르게 1줄이면 코드 끝 
		//insert할거면 session에서 insert()메서드 호출 후 
		//mapper.xml의 네임값(NoticeMapper)와 쿼리문의 id값(insertNotice)를 호출 
		//한줄 쓰고 끝나는게 아니라 노티스매퍼.xml을 만들고 태그를 이용해 쿼리문 작성 
		//mapper.xml을 사용한다고 mybatis-config.xml에 신고,등록해야함 
		int result = session.insert("NoticeMapper.insertNotice",notice);
		return result;
	}
	
	



	public List<Notice> selectNoticeList(SqlSession session, int currentPage) {
		//select할거면 session에서 selectList, selectOne 메소드중에서 필요에 맞게 호출 
		//mapper.xml의 네임값(NoticeMapper)와 쿼리문의 id값(selectNoticeList)를 호출
		//넘겨주는값(매개변수)은 없으므로 name.id값만 selectList()메소드의 전달값으로 넘겨줌 
		//mapper.xml에서는 select이기 떄문에 rsetToNotice에 해당하는 리절트맵 작성 필요함
		
		/*
		 * RowBounds는 왜 쓰나요? => 쿼리문을 변경하지 않고도 페이징을 처리할 수 있게 해주는 클래스 
		 * RowBounds의 동작은 offset값과 limit값을 이용해서 동작함 
		 * limit값은 한페이지당 보여주고 싶은 게시물의 갯수 
		 * offset은 시작값 , 변하는값. 
		 * 1페이지에서는 0*10부터 시작해서 10개를 가져오고   1~10
		 * 2페이지에서는 1*10부터 시작해서 10개를 가져옴    11~20
		 * 3페이지에서는 2*10부터 ~ 10개 가져옴 			21~30
		 */
		int limit = 10; 
		int offset = (currentPage-1) *limit ;
		
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice>nList = session.selectList("NoticeMapper.selectNoticeList",null, rowBounds);
		//selectNoticeList 실행하라고 넘겨주는 값이 없음. 없어서 중간에 null로 표시해줌 
		//(안써도 되는데 세번쨰에rowBounds 써야하기 때문에 일부러 기재한것)  
		return nList;
		}


	public Notice selectOneByNo(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectOneByNo",noticeNo);
		return notice;
	}


	public int deleteNoticeByNo(SqlSession session, int noticeNo) {
		int result = session.delete("NoticeMapper.deleteNoticeByNo",noticeNo);
		return result;
	}





	public int updateNotice(SqlSession session, Notice notice) {
		int result = session.update("NoticeMapper.updateNotice",notice);
		return result;
	}
	
	
	public String generatePageNavi(SqlSession session, int currentPage) {
		//전체게시물의 갯수 
		int totalCount = getTotalCount(session);  //게시글이 늘어날때마다 네비 수 늘어나도록 메소드처리
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		int totalNaviCount;
		
		if(totalCount % recordCountPerPage > 0 ) {
			totalNaviCount = totalCount / recordCountPerPage +1;
		}else {
			totalNaviCount = totalCount / recordCountPerPage;
		}
		
		
		
		int startNavi = ((currentPage -1)/naviCountPerPage)* naviCountPerPage+1;
		//currentPag가 1일떄 ((currentPage -1)/naviCountPerPage)가 0이 되면 naviCountPerPage는 계산이 안되서 1이 됨 
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > totalNaviCount) {
			endNavi = totalNaviCount;
		}
		StringBuffer result = new StringBuffer();
		boolean needPrev = true;
		boolean needNext = true;
		if (startNavi !=1) {
			result.append("<a href='/notice/list.do?currentPage="+(startNavi-1)+"'>[이전]</a>"); 
		}
		
		for(int i = startNavi; i <= endNavi; i++) {
			result.append("<a href='/notice/list.do?currentPage="+i+"'>"+i+"</a>&nbsp;&nbsp;&nbsp;"); 
			//result.append하면 문자열을 쭉 나열해줌 
		}
		if (endNavi != totalNaviCount) {
			result.append("<a href='/notice/list.do?currentPage="+(endNavi+1)+"'>[다음]</a>");
		}
		return result.toString();

		
	}


	private int getTotalCount(SqlSession session) {
		int totalCount = session.selectOne("NoticeMapper.getTotalCount");
		return totalCount;
	}


}