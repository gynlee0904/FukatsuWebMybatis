package com.kh.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.member.model.vo.Member;

public class MemberDAO {

	public int insertMember(SqlSession session, Member member) {
		int result = session.insert("MemberMapper.insertMember",member);
		return result;
		//쿼리문과 값세팅은 xml파일로 => 리소시스/매퍼스/member-mapper.xml
		//"MemberMapper.insertMember" => xml파일에서 namespace = MemberMapper에 있는 id= insertMember 가져다 쓴다는 의미
		//MemberMapper 쓴다는 신고를 mybatis-config에 적어줌  <mapper resource="mappers/member-mapper.xml"/>
	}
	
	
	public int updateMember(SqlSession session, Member member) {
		int result = session.update("MemberMapper.updateMember",member);
		return result;
	}
	
	
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deleteMember",memberId);
		return result;
	}


	public Member selectCheckLogin(SqlSession session, Member member) {
		Member mOne = session.selectOne("MemberMapper.selectCheckLogin", member);  //한개를 가져오냐(member) 여러개를 가져오냐(list)에 따라 메소드가 달라짐
		return mOne;
	}


	public Member selectOneById(SqlSession session, String memberId) {
		Member member = session.selectOne("MemberMapper.selectOneById", memberId);  
		return member;
	}

	

	
	
	
	
	
}
