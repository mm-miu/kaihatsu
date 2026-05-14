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

        <h2 class="main-title">ユーザー登録</h2>

        <form action="TeacherCreateExecute.action">
            
            <div class="content-input">
                <label for="id">ID</label>
                <input type="text" id="id" name="id" 
                    value="${id}" placeholder="半角でログイン名を入力してください" maxlength="10" required>
                <br>
                <c:if test="${not empty errors.cd}">
                    <p style="color:red">${errors.cd}</p>
                </c:if>
                <c:if test="${not empty errors.cd_count}">
                    <p style="color:red">${errors.cd_count}</p>
                </c:if>
            </div>

            <div class="content-input">
                <label for="name">名前</label>
                <input type="text" id="name" name="name" 
                    value="${name}" placeholder="名前を入力してください" maxlength="10" required>
                <br>
                <c:if test="${not empty errors.cd}">
                    <p style="color:red">${errors.cd}</p>
                </c:if>
                <c:if test="${not empty errors.cd_count}">
                    <p style="color:red">${errors.cd_count}</p>
                </c:if>
            </div>

            

            <c:choose>
                <c:when test="${ authority=='1'}">
                    <div class="content-input">
                        <label for="school_cd">所属</label>
                        <select name="school" >
                            <c:forEach var="sList" items="${schoolList}">
                                <option value="${sList.cd}" label="${sList.name}">${sList.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>          
            
            <div class="button-2">
                <button name="end" type="submit">登録</button>
                <br>
            </div>
        </form>

        <a href="TeacherList.action">戻る</a>
    </c:param>

</c:import>

