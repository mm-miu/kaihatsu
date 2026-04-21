<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <h2>学生情報登録</h2>

        <form action="StudentCreateExecute.action">
            <label for="ent_year">入学年度</label>
            <select id="ent_year" name="ent_year">
                <option value="0">--------</option>
                <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}" <c:if test="${ent_year == year}">
                        selected</c:if>>${year}
                    </option>
                </c:forEach>
            </select>
            <br>
            <c:if test="${not empty errors.ent_year}">
                <p style="color:gold">${errors.ent_year}</p>
            </c:if>

            <label for="no">学生番号</label>
            <input type="text" id="no" name="no" value="${empty no ? '学生番号を入力してください' : no}" required>
            <br>
            <c:if test="${not empty errors.no}">
                <p style="color:gold">${errors.no}</p>
            </c:if>

            <label for="name">氏名</label>
            <input type="text" id="name" name="name" value="${empty name ? '氏名を入力してください' : name}" required>
            <br>

            <label for="class_num">クラス</label>
            <select id="class_num" name="class_num">
                <option value="0">--------</option>
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}">${num}</option>
                </c:forEach>
            </select>
            <br>

            <button name="end" type="submit">登録して終了</button>
            <br>
        </form>

        <a href="StudentList.action">戻る</a>
    </c:param>

</c:import>