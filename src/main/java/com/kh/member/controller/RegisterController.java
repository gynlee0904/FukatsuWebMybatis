package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;





/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//두겟 : 메인페이지에서 회원가입 눌렀을때 작동하는 페이지 이동 용도 //경로로 이동 못하기 때문에 리퀘스트디스패쳐 이용해서 겅로 써야함 //url창 접속은 .do로 접속 
		request.getRequestDispatcher("/WEB-INF/views/member/enroll.jsp").forward(request, response);
	}	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("member-id");    
		String memberPw = request.getParameter("member-pw");  
		String memberName = request.getParameter("member-name");  
		int memberAge = Integer.parseInt(request.getParameter("member-age"));     
		String memberGender = request.getParameter("member-gender");      
		String memberEmail = request.getParameter("member-email");  
		String memberAddress = request.getParameter("member-addr"); 
		String memberPhone = request.getParameter("member-phone");  
		String memberHobby = request.getParameter("member-hobby");  
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberGender, memberEmail, memberPhone, memberAddress, memberHobby);
		
		//쿼리문 : INSERT INTO MEMBER_TBL VALUES(,,,,,,,,,DEFAULT,DEFAULT,DEFAULT)
		MemberService service = new MemberService(); 
		int result = service.insertMember(member);
		if(result>0) {
			//성공 -> 로그인(인덱스) 페이지로 이동
			//위드아웃데이터 sendRedirect
			response.sendRedirect("/index.jsp");
			
			
		}else {
			//실패 -> 에러메세지 출력
			request.setAttribute("url", "/member/register.do");  //회원가입페이지로 이동(이동할 유알엘은 에러페이지.jsp에 있음)
			request.setAttribute("msg", "회원등록이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
	}

}
