<%-- 科目情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <h2>クラス情報変更</h2>

        <form action="ClassNumUpdateExecute.action">
            <label for="num">クラス番号</label>
            <input type="text" id="num" name="newNum" 
                value="${num}" pattern="\d+" maxlength="3" required>
            <input type="hidden" name="num" value="${num}">
            <br>
            <c:if test="${not empty errors.num}">
                <p style="color:gold">${errors.num}</p>
            </c:if>
            <c:if test="${not empty errors.num_count}">
                <p style="color:gold">${errors.num_count}</p>
            </c:if>

            <button name="end" type="submit">変更</button>
            <br>
        </form>

        <a href="SubjectList.action">戻る</a>
    </c:param>

</c:import>