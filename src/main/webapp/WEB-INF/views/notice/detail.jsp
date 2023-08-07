<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>공지사항 상세조회</title>
	</head>
	<body>
		<h1>공지사항 상세</h1>
		<ul>
			<li>
				<label>글번호</label>
				<span>${requestScope.notice.noticeNo }</span> 
				<!-- requestScope. 뒤에는 콘트롤러에 request.setAttribute("notice" 와 똑같이. requestScope는 생략가능 -->
				<!-- notice. 뒤에는 필드변수명 -->
			</li>
			<li>
				<label>작성일</label>
				<span>${notice.noticeDate }</span>
			</li>
			<li>
				<label>글쓴이</label>
				<span>${notice.noticeWriter }</span>
			</li>
			<li>
				<label>제목</label>
				<span>${notice.noticeSubject }</span>
			</li>
			<li>
				<label>내용</label>
				<p>${ notice.noticeContent }</p> 
			</li>
		</ul>
<!-- 		목록으로 -> 리스트로  -->
<!-- 		수정하기 -> 수정페이지로 -->
<!-- 		삭제하기 -> 삭제 바로  -->
		<a href = "/notice/list.do">목록으로</a>
		<!-- .do는 서블렛으로  -->
		<a href = "/notice/modify.do?noticeNo=${notice.noticeNo}">수정하기</a>
		<a href = "javascript:void(0)" onclick="deleteCheck();">삭제하기</a>  
		<!-- 쿼리스트링 직접 만듦. //noticeNo=${notice.noticeNo} 노티스넘버로 삭제하겠다는식으로 만듦   -->
		<!-- javascript:void(0) 클릭시 이동방지  -->
<!-- 		<button id="" onclick="">삭제하기</button> //a로 하느냐 버튼으로 하느냐는 css에 따라 만드는방식 다름  -->



		<script>
			const deleteCheck =()=>{
				const noticeNo = '${notice.noticeNo}';
				if(confirm("정말 삭제하시겠습니까?")){
					location.href="/notice/delete.do?noticeNo="+noticeNo;
				}
			}
		
		</script>
	</body>
</html>