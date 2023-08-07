<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 수정</title>
	</head>
	<body>
		<h1>공지사항 수정</h1>
		<form action="/notice/modify.do" method="post">
			<input type="hidden" name="noticeNo" value="${notice.noticeNo}">
			<!-- 콘트롤러에서 노티스넘버가 필요해 넣었으나 사용자에게 보일 필요는 없으므로 타입을 히든으로 함 -->
			<fieldset>
				<legend>공지사항 수정</legend>
				<ul>
					<li>
						<label>제목</label>
						<input type="text" id="" name="noticeSubject" value="${notice.noticeSubject }">  
						<!--value="${notice.noticeSubject } 제목창에 이전에 쓴 제목 끌어오도록  -->
					</li>
					<li>
						<label>내용</label>
						<textarea rows="30" cols="40" id="" name="noticeContent">${notice.noticeContent }</textarea>  
					</li>
				
				</ul>
			</fieldset>
			<div>
				<input type="submit" value="수정하기">
				<input type="reset"  value="초기화">
			</div>
		</form>
	</body>
</html>
<!-- 수정시 썼던내용 나오게 하려면 컨트롤러에서 보내줘야함 -->
<!-- 공지사항 링크 누르면 서블릿 동작 (겟방식-> 두겟 호출) 리퀘스트 디스패쳐이용해서 페이지이동 -> 공지사랑 수정 페이지 띄움  -->
<!-- 상세에서 수정하기누르면 두겟(페이지 이동 용도)이 동작 -> 페이지 나오면 작성 하고 작성하기 누르면 두포스트(데이터등록용도)가 동작 -->
