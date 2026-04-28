<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム - 成績参照（学生別）</c:param>
    <c:param name="content">

        <h2>成績参照（学生別）</h2>

        <!-- 氏名表示 -->
        <div>
            氏名：
            <c:choose>
                <c:when test="${not empty student}">
                    <c:out value="${student.name}" />（<c:out value="${student.no}" />）
                </c:when>
                <c:otherwise>
                    <c:out value="${studentNo}" />
                </c:otherwise>
            </c:choose>
        </div>

        <hr/>

        <!-- 成績一覧（点数が未入力の行は DAO 側で除外される前提） -->
        <c:if test="${empty tests}">
            <p>成績情報が存在しませんでした。</p>
        </c:if>

        <c:if test="${not empty tests}">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>科目名</th>
                        <th>科目コード</th>
                        <th>回数</th>
                        <th>点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="row" items="${tests}">
                        <tr>
                            <td><c:out value="${row.subjectName}" /></td>
                            <td><c:out value="${row.subjectCd}" /></td>
                            <td><c:out value="${row.num}" /></td>
                            <td><c:out value="${row.point}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

    </c:param>
</c:import>
