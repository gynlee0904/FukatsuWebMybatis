package com.kh.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;
import com.kh.notice.model.vo.PageData;





/**
 * Servlet implementation class ListController
 */
@WebServlet("/notice/list.do")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//쿼리문 : SELECT * FROM NOTICE_TBL ORDER BY NOTICE_NO DESC  
		NoticeService service = new NoticeService();
		//여러개의 데이터니까 List로 받았고 쿼리문을 보니 매개변수는 필요없음
		String page = request.getParameter("currentPage") != null ? request.getParameter("currentPage"): "1";
		int currentPage = Integer.parseInt(page);
//		List<Notice> nList = service.selectNoticeList(currentPage);
		PageData pd = service.selectNoticeList(currentPage);
		List<Notice> nList = pd.getnList();
		String pageNavi = pd.getPageNavi();
		//nList는 없어도 널이 아니라서 isEmpty()로 비어있는지 체크 
		//비슷한 방법으로 nList.size)가 있음 
		if(!nList.isEmpty()) {
			//성공시 list.jsp로 이동
			request.setAttribute("nList", nList);
			request.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(request, response);
		}else {
			//실패시 메인으로 이동 
			request.setAttribute("url", "/index.jsp");  //회원가입페이지로 이동(이동할 유알엘은 에러페이지.jsp에 있음)
			request.setAttribute("msg", "데이터조회가 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
