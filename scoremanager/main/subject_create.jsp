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

        <h2 class="main-title">科目情報登録</h2>

        <form action="SubjectCreateExecute.action">
            
            <div class="content-input">
                <label for="cd">科目コード</label>
                <input type="text" id="cd" name="cd"
                    value="${cd}"
                    placeholder="科目コードを入力してください"
                    maxlength="3" required>
                <br>
                <c:if test="${not empty errors.cd}">
                    <p style="color:red">${errors.cd}</p>
                </c:if>
                <c:if test="${not empty errors.cd_count}">
                    <p style="color:red">${errors.cd_count}</p>
                </c:if>
            </div>
            
            <div class="content-input">
                <label for="name">科目名</label>
                <input type="text" id="name" name="name" 
                    value="${name}"
                    placeholder="科目名を入力してください"
                    maxlength="20" required>
                <br>
                <c:if test="${not empty errors.name}">
                    <p style="color:red">${errors.name}</p>
                </c:if>
            </div>
            <div class="button-2">
                <button name="end" type="submit">登録</button>
                <br>
            </div>
        </form>

        <a href="SubjectList.action">戻る</a>
    </c:param>

</c:import>