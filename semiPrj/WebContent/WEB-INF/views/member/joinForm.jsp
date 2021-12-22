<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    #div-main{
        width: 50vw;
        height: 50vh;
        margin: auto;
        background-color: cadetblue;
    }
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
    <%@ include file="/WEB-INF/views/common/header.jsp"%>
    
    <div id="div-main">
        <form action="join" method="post">
        	<input type="hidden" value="f">
            <label>아이디   : </label> <input type="text" name="id" id="idBox"> <button type="button" id="dupCheck">중복확인</button>
            <br>
            <label>비밀번호 : </label> <input type="text" name="pwd">
            <br>
            <label>이름 : </label> <input type="text" name="name">
            <br>
            <input type="submit" value="가입하기">
        </form>
    </div>
    
    <script>
    	$("#dupCheck").on('click', function(){
    		$.ajax({
    			url : '/semi/memberDupCheck',
    			method : 'get',
    			data: {
    				id : $("#idBox").val()
    			},
    			success : function(data){
    				alert(data);
    			},
    			error : function(err){
    				alert("error");
    			}
    		});
    	})
    </script>

</body>
</html>