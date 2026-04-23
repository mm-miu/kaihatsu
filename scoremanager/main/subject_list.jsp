<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2>科目管理</h2>
        <a href="SubjectCreate.action">新規登録</a>

        <table>
            <th>科目コード</th>
            <th>科目名</th>
            <c:forEach var="subject" items="${subjects}">
                <tr>
                    <td>${subject.cd}</td>
                    <td>${subject.name}</td>
                    <td><a href="SubjectUpdate.action?school_cd=${school_cd}&cd=${subject.cd}">変更</a></td>
                    <td><a href="SubjectDelete.action?school_cd=${school_cd}&cd=${subject.cd}">削除</a></td>
                </tr>
            </c:forEach>
        </table>

    </c:param>
</c:import>