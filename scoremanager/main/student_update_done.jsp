<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2>学生情報変更</h2>
        <label><p>変更が完了しました</p></label>
        <a href="StudentList.action">学生一覧</a>
    </c:param>
</c:import>