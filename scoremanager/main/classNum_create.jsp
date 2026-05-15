<%-- クラス登録JSP --%>
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
                        <li><a href="ClassNumList.action">クラス一覧</a></li>
                        <li>クラス情報登録</li>
                    </ol>
                </div>
            </div>
        </div>

        <h2 class="main-title">クラス情報登録</h2>

        <form action="ClassNumCreateExecute.action">
            <div class="content-input">
                <label for="num">クラス番号</label>
                <input type="text" id="num" name="num" 
                    value="${num}"
                    placeholder="クラス番号を入力してください"
                    pattern="\d+" maxlength="3" required>
                <br>
                <c:if test="${not empty errors.num}">
                    <p style="color:red">${errors.num}</p>
                </c:if>
                <c:if test="${not empty errors.num_count}">
                    <p style="color:red">${errors.num_count}</p>
                </c:if>
            </div>
            <div class="button-2">
                <button name="end" type="submit">登録</button>
                <br>
            </div>
        </form>

        <a href="ClassNumList.action">戻る</a>
    </c:param>

</c:import>