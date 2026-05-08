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

        <h2 class="main-title">科目情報変更</h2>

        <form action="SubjectUpdateExecute.action">
            <div class="content-input">
                <label for="cd">科目コード</label>
                <input type="text" id="cd" name="cd" value="${cd}" readonly>
                <br>

                <!-- 変更中に別画面から対象の科目が削除された場合 -->
                <c:if test="${not empty errors.cd}">
                    <p style="color:red">${errors.cd}</p>
                </c:if>
            </div>

            <div class="content-input">
                <label for="name">科目名</label>
                <input type="text" id="name" name="name" value="${name}" maxlength="20" required>
                <br>
            </div>
            <div class="button-2">
                <button type="submit">変更</button>
                <br>
            </div>
        </form>

        <a href="SubjectList.action">戻る</a>
    </c:param>

</c:import>