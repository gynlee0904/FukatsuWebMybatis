package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;


/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/notice/insert.do")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글쓰기 버튼 누르면 작동하는 코드 (두겟) 단순 페이지 이동
		request.getRequestDispatcher("/WEB-INF/views/notice/insert.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//작성페이지에서 글 올리기 버튼 누르면 작동하는 코드
		//공지사항 등록 
		
		//폼태그 > 인풋태그 > 네임 속성이 가지는 값 : noticeSubject
		request.setCharacterEncoding("UTF-8");  //한글 안깨지게 인코딩
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		
		//두개의 값으로 필드 초기화하면서 객체 생성 : 매개변수 생성자 이용 
		Notice notice = new Notice(noticeSubject, noticeContent);
		NoticeService service = new NoticeService();
		int result = service.insertNotice(notice);
		
		if (result > 0) {
			//성공하면 공지사항 리스트로 이동
			//without data 페이지 이동으로
			response.sendRedirect("/notice/list.do");
			
		}else {
			//실패하면 실패메세지 출력, 이전페이지 이동 클릭시 작성페이지로 이동
			request.setAttribute("msg", "공지가 등록완료되지 않았습니다");
			request.setAttribute("url", "/notice/insert.do");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response); 
	
		}
	}

}
