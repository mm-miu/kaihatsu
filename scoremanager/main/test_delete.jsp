<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム - 削除結果</c:param>

    <c:param name="content">
        <c:choose>
            <c:when test="${deleted == true}">
                <p class="scses">削除が完了しました</p>
            </c:when>
            <c:otherwise>
                <p class="fail">削除に失敗しました（該当データが存在しない可能性があります）</p>
            </c:otherwise>
        </c:choose>

        <div class="center">
            <form action="test_list.jsp" method="get" style="display:inline;">
                <input type="hidden" name="studentNo" value="${studentNo}" />
                <button type="submit" class="btn-return">成績一覧へ戻る</button>
            </form>
        </div>

    </c:param>
</c:import>
