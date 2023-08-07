package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class UpdateController
 */
@WebServlet(name = "NoticeUpdateController", urlPatterns = { "/notice/modify.do" })
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이지이동용 // 글 디테일에서 수정하기 누르면 하이퍼링크(/notice/modify.do?noticeNo=${notice.noticeNo})로 단순 수정하기 페이지로 이동하는거니까 모디파이 콘트롤러로 와서  겟메소드 작동 
		//디비에서 데이터 갖고와서 뿌려줌 쿼리스트링 필요 
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		NoticeService service = new NoticeService();
		//SELECT * FROM NOTICE_TBL WHERE NOTICE_NO = ? 
		Notice notice = service.selectOneByNo(noticeNo);
		if(notice != null) {
			//데이터가 있으면 modify.jsp로 이동 
			request.setAttribute("notice", notice);
			request.getRequestDispatcher("/WEB-INF/views/notice/modify.jsp").forward(request, response);			
		}else {
			//데이터가 없으면 에러페이지로 이동 
			request.setAttribute("url", "/notice/detail.do.jsp");  //회원가입페이지로 이동(이동할 유알엘은 에러페이지.jsp에 있음)
			request.setAttribute("msg", "글 수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터 수정용 //수정하기로 들어가서 글수정을 누르면 데이터전송이 필요해서 폼태그가 작동하니까 포스트메소드가 작동 
		//UPDATE NOTICE_TBL SET NOTICE_SUBJECT = ? , NOTICE_CONTENT = ? WHERE NOTICE_NO =? 
		request.setCharacterEncoding("UTF-8"); 
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		String noticeSubject = request.getParameter("noticeSubject");
		String noticeContent = request.getParameter("noticeContent");
		Notice notice = new Notice(noticeNo, noticeSubject, noticeContent);
		NoticeService service = new NoticeService();
		int result = service.updateNotice(notice);
		if(result > 0 ) {
			//수정성공하면 상세페이지 이동 
			response.sendRedirect("/notice/detail.do?noticeNo="+noticeNo);
		}else {
			//실패하면 에러메세지 출력 
			request.setAttribute("url", "/notice/modify.do?noticeNo="+noticeNo);  
			request.setAttribute("msg", "글 수정이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
	}

}
