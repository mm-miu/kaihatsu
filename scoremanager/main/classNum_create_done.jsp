<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2>クラス情報登録</h2>
        <label><p>登録が完了しました</p></label>
        <a href="ClassCreate.action">戻る</a>
        <a href="ClassList.action">クラス一覧</a>
    </c:param>
</c:import>