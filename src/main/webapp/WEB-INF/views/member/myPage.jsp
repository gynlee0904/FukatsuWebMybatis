<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>마이페이지</title> <!-- 회원가입의 결과페이지 -->
		<link rel="stylesheet" href="/resources/css/member/main.css">
	
	</head>
	<body>
		<h1>마이페이지</h1>
		<form action="/member/update.do" method="post">
		<fieldset>
				<legend>회원상세정보</legend>
					<ul>
						<li>
							<label for="member-id">아이디</label>  
							<input type="text" id="member-id" name="member-id" value="${ member.memberId}" >  <!-- 컨트롤러에서 받은 member라는 키값에서 멤버아이디를 가져옴 -->		
						</li>
						<li>
							<label for="member-pw">비밀번호</label>
							<input type="password" id="member-pw" name="member-pw">
							
						</li>
						<li>
							<label for="member-name">이름</label>
							<input type="text" id="member-name" name="member-name" value="${ member.memberName}" >
							
						</li>
						<li>
							<label for="member-age">나이</label>
							<input type="text" id="member-age" name="member-age" value="${ member.memberAge}" >
							
						</li>
						<li>
							<label for="member-gender">성별</label> <!-- ${ member.memberGender} -->
								<c:if test="${ member.memberGender eq 'M' }">남자</c:if>
								<c:if test="${ member.memberGender eq 'F' }">여자</c:if>
								
								
				
						</li>
						<li>
							<label for="member-email">이메일</label>
							<input type="text" id="member-email" name="member-email" value="${ member.memberEmail}">
							
						</li>
						<li>
							<label for="member-phone">전화번호</label>
							<input type="text" id="member-phone" name="member-phone" value="${ member.memberPhone}">
							
						</li>
						<li>
							<label for="member-addr">주소</label>
							<input type="text" id="member-addr" name="member-addr" value="${ member.memberAddr}">
							
						</li>
						<li>
							<label for="member-hobby">취미</label>
							<input type="text" id="member-hobby" name="member-hobby" value="${ member.memberHobby}">
							
						</li>
						</li>
						<li>
							<label for="member-hobby">가입날짜</label>
							<input type="text" id="member-hobby"  value="${ member.memberDate}" readonly>
						</li>

					</ul>
					<div>
						<button type="submit">수정하기</button>
							<a href = "javascript:void(0)" onclick="checkDelete();">탈퇴하기</a>
					</div>
		</fieldset>	
	</form>
		<script>
// 			document.querySelector("#delete").addEventListener("click", function(){
// 					<!-- 탈퇴할때 진짜 할거냐고 물어볼때  -->
				
// 				confirm ("탈퇴하시겠습니까?")
// 	            });
			function checkDelete(){
				const memberId = '${sessionScope.memberId}'
				if(confirm ("탈퇴하시겠습니까?")){
					location.href = "/member/delete.do?memberId="+memberId;
				}
				//확인(true) 눌렀을때만 탈퇴되도록  //취소누르면 실행문이 동작 안함 
				
			}
		</script> 
					
				
	
	</body>
</html>