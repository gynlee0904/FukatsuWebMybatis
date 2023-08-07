<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 코어태그 자동완성 가능 taglib uri=" 컨트롤 + 스페이스바 -->

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>후카츠 멤버 웹</title>
	</head>
	<body>
		
		<h1>이름없는 멤버 웹</h1>
		<c:if test="${sessionScope.memberId ne null }"> <!-- 로그인 성공시 -->
			${memberName}님 환영합니다. <a href = "/member/logout.do">로그아웃</</a>
			<a href ="/member/myPage.do?memberId=${memberId}">마이페이지</a> 
			<!-- 폼태그가 아니라서 자동으로 쿼리스트링이 만들어지지않음. 직접 만들어야 함 -->
 			<!-- 에이태그는 겟방식으로 요청.겟방식으로 받아야 함  -->
		</c:if>
		<c:if test="${sessionScope.memberId eq null }"> <!-- 로그인 실패시 -->
		<h2> 로그인 페이지 </h2>
		<fieldset>
			<legend> 로그인 </legend>
			<form action="/member/login.do" method="post">  
				<input type="text" name="member-id"><br>
				<input type="password" name="member-pw"><br>
				<div>
				<input type="submit" value="로그인">
				<input type="reset" value="취소">
				<a href = "/member/enroll.jsp">회원가입</a>
				</div>
			</form>
		</fieldset>
		</c:if>
	</body>
</html>

