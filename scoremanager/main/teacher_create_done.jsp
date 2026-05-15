<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
    <c:param name="content">
        <div id="wrapper">
            <div id="main">
                <div id="breadcrumb">
                    <ol>
                        <li><a href="Menu.action">MENU</a></li>
                        <li><a href="TeacherList.action">ユーザー管理</a></li>
                        <li>ユーザー登録</li>
                    </ol>
                </div>
            </div>
        </div>
        
        <h2 class="main-title">ユーザー情報登録</h2>
        <label><p class="message">登録が完了しました</p></label>
        <p>初期パスワードは"password"になります</p>
        <p>初ログイン後変更ページより変更してください</p><br>
        <a href="TeacherCreate.action">戻る</a>
        <a href="TeacherList.action">ユーザー一覧</a>
    </c:param>
</c:import>