<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2>クラス管理</h2>
        <a href="ClassCreate.action">新規登録</a>

        <p>${school.name}のクラス一覧</p>

        <table>
            <th>クラス番号</th>
            <c:forEach var="c" items="${classes}">
                <tr>
                    <td>${c}</td>
                    <td><a href="ClassUpdate.action?school_cd=${school.cd}&cd=${c}">変更</a></td>
                    <td><a href="ClassDelete.action?school_cd=${school.cd}&cd=${c}">削除</a></td>
                </tr>
            </c:forEach>
        </table>

    </c:param>
</c:import>