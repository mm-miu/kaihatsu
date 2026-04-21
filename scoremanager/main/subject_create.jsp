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

        <h2>科目情報登録</h2>

        <form action="SubjectCreateExecute.action">
            <label for="cd">科目コード</label>
            <input type="text" id="cd" name="cd" 
                value="${empty cd ? '科目コードを入力してください' : cd}" required>
            <br>
            <c:if test="${not empty errors.cd}">
                <p style="color:gold">${errors.cd}</p>
            </c:if>
            <c:if test="${not empty errors.cd_count}">
                <p style="color:gold">${errors.cd_count}</p>
            </c:if>

            <label for="name">科目名</label>
            <input type="text" id="name" name="name" value="${empty name ? '科目名を入力してください' : name}" required>
            <br>
            <c:if test="${not empty errors.name}">
                <p style="color:gold">${errors.name}</p>
            </c:if>

            <button name="end" type="submit">登録</button>
            <br>
        </form>

        <a href="SubjectList.action">戻る</a>
    </c:param>

</c:import>