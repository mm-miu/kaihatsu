<%-- 科目登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <h2>クラス情報登録</h2>

        <form action="ClassCreateExecute.action">
            <label for="num">クラス番号</label>
            <input type="text" id="num" name="num" 
                value="${empty num ? 'クラス番号を入力してください' : num}" pattern="\d+" required>
            <br>
            <c:if test="${not empty errors.num}">
                <p style="color:gold">${errors.num}</p>
            </c:if>
            <c:if test="${not empty errors.num_count}">
                <p style="color:gold">${errors.num_count}</p>
            </c:if>

            <button name="end" type="submit">登録</button>
            <br>
        </form>

        <a href="ClassList.action">戻る</a>
    </c:param>

</c:import>