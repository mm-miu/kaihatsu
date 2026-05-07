<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">

        <h2 class="main-title">クラス管理</h2>

        <div class="new">
            <a href="ClassCreate.action">新規登録</a>
        </div>

        <p>${school.name}のクラス一覧</p>

        <table>
            <th>クラス番号</th>
            <th></th>
            <th></th>
            <c:forEach var="c" items="${classes}">
                <tr>
                    <td>${c}</td>
                    <td><a href="ClassNumUpdate.action?school_cd=${school.cd}&cd=${c}">変更</a></td>
                    <td><a href="ClassNumDelete.action?school_cd=${school.cd}&cd=${c}">削除</a></td>
                </tr>
            </c:forEach>
        </table>

    </c:param>
</c:import>