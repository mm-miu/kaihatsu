<%-- 学生一覧JSP --%>
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
                        <li>科目情報削除</li>
                    </ol>
                </div>
            </div>
        </div>
        
        <p class="message">変更が完了しました</p>
        <a href="SubjectList.action" >科目一覧</a>
    </c:param>
</c:import>