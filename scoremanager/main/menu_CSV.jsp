<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2 class="main-title">CSV読み込み</h2>
        <a href="student_CSV.jsp">学生登録</a>
        <a href="subject_CSV.jsp">科目登録</a>
    </c:param>
</c:import>