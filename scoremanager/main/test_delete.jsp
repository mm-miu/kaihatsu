<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
  <c:param name="title">得点管理システム - 削除結果</c:param>
  <c:param name="content">
    <h2>削除結果</h2>

    <!-- メッセージを表示 -->
    <p><c:out value="${message}" /></p>

    <!-- 元の一覧へ戻るリンク -->
    <p><a href="/test/list?student_no=${studentNo}">一覧へ戻る</a></p>
  </c:param>
</c:import>
