<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="content">
    <h2>ログイン</h2>
    <form action="Login.action" method="post">
        <p>ログイン名<input type="text" name="id" 
            value="${empty id ? "半角でご入力ください" : id}" 
            required>
        </p>
        <p>パスワード<input type="password" name="password"
            placeholder="${empty password ? "30字以内の半角英数字でご入力ください" : ""}" 
            required></p>
        <p><label>
            <input type="checkbox" name="chk_d_ps">
            パスワードを表示
        </label></p>
        <input type="submit" value="ログイン" name="login">
    </form>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content" value="${content}"></c:param>
</c:import>
