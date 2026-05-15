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
                        <li><a href="SubjectList.action">科目一覧</a></li>
                        <li>科目情報登録</li>
                    </ol>
                </div>
            </div>
        </div>
        
        <h2 class="main-title">科目情報登録</h2>
        <label><p class="message">登録が完了しました</p></label>
        <a href="SubjectCreate.action">戻る</a>
        <a href="SubjectList.action">科目一覧</a>
    </c:param>
</c:import>