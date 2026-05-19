<%-- 学生登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <div id="wrapper">
            <div id="main">
                <div id="breadcrumb">
                    <ol>
                        <li><a href="Menu.action">MENU</a></li>
                        <li><a href="StudentList.action">学生一覧</a></li>
                        <li>学生情報登録</li>
                    </ol>
                </div>
            </div>
        </div>
        
        <h2 class="main-title">学生情報登録</h2>

        <form action="StudentCreateExecute.action">
            <label for="ent_year">入学年度</label>
            <select id="ent_year" name="ent_year">
                <option value="0" selected disabled>--------</option>
                <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}" <c:if test="${ent_year == year}">
                        selected</c:if>>${year}
                    </option>
                </c:forEach>
            </select>
            <br>

            <div class="content-input">
                <c:if test="${not empty errors.ent_year}">
                    <p style="color:red">${errors.ent_year}</p>
                </c:if>

                <label for="no">学生番号</label><br>
                <input type="text" id="no" name="no" value="${no}" 
                    placeholder="学生番号を入力してください"
                    title="数字で入力してください"
                    pattern="\d+" maxlength="10" required>
                <br>
            </div>

            <div class="content-input">
                <c:if test="${not empty errors.no}">
                    <p style="color:red">${errors.no}</p>
                </c:if>

                <label for="name">氏名</label><br>
                <input type="text" id="name" name="name" value="${name}" placeholder="氏名を入力してください" maxlength="30" required>
                <br>
            </div>

            <label for="class_num">クラス</label>
            <select id="class_num" name="class_num">
                <option value="0" selected disabled>--------</option>
                <c:forEach var="num" items="${class_num_set}">
                    <option value="${num}">${num}</option>
                </c:forEach>
            </select>
            <c:if test="${not empty errors.class_num}">
                <p style="color:red">${errors.class_num}</p>
            </c:if>
      
            
            <div class="button-2">
                <button name="end" type="submit">登録して終了</button>
                <br>
            </div>
        </form>

        <a href="StudentList.action">戻る</a>
    </c:param>

</c:import>