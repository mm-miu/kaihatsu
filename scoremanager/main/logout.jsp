<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<head>
    <style>
        .logout_main{
            margin: 10px;
            margin-left: 20px;
            margin-right: 20px;
            text-align: center;
        }
        .logout_main h2{
            background-color: #EEEEEE;
            padding: 20px;
            text-align: left;
        }
        .logout_main p{
            background-color: #dfdffd;
            padding: 10px;
        }
        .logout_main a{
            padding-top: 10px;
        }
    </style>
</head>

    <c:set var="content">
        <div class="logout_main">
            <h2>ログアウト</h2>
            <p>ログアウトしました</p>
            <a href="Login.action">ログイン</a>
        </div>
    </c:set>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content" value="${content}"></c:param>
</c:import>