<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">

        <h2>学生情報変更</h2>

        <form action="StudentUpdateExecute.action">
            <label for="ent_year">入学年度</label>
            <input type="text" id="ent_year" name="ent_year" value="${ent_year}" readonly>
            <br>

            <label for="no">学生番号</label>
            <input type="text" id="no" name="no" value="${no}" readonly>
            <br>

            <label for="name">氏名</label>
            <input type="text" id="name" name="name" value="${name}" required>
            <br>

            <label for="class_num">クラス</label>
            <select id="class_num" name="class_num">
                <option value="0">--------</option>
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}"
                        <c:if test="${num==class_num}">
                            selected
                        </c:if>
                        >${num}
                    </option>
                </c:forEach>
            </select>
            <br>

            <label>
                在学中
                <input type="checkbox" name="is_attend"
                    <c:if test="${is_attend==true}">checked</c:if>
                >
            </label>
            <br>

            <button name="login" type="submit">変更</button>
            <br>
        </form>

        <a href="StudentList.action">戻る</a>
    </c:param>

</c:import>