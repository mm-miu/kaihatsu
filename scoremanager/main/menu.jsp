<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <c:set var="content">
        <section>
        <div id="wrapper">
            <div id="main">
                <div id="breadcrumb">
                    <ol>
                        <li><a href="Menu.action">MENU</a></li>
                    </ol>
                </div>
            </div>
        </div>

            <h2 class="main-title">メニュー</h2>
            <div class="menu-grid">
                <a href="StudentList.action" class="menu-card student">
                    <h2>学生管理</h2>
                </a>
                <a href="MenuTest.action" class="menu-card test">
                    <h2>成績管理</h2>
                </a>
                <a href="SubjectList.action" class="menu-card subject">
                    <h2>科目管理</h2>
                </a>
                <a href="ClassNumList.action" class="menu-card class">
                    <h2>クラス管理</h2>
                </a>
                <a href="MenuCSV.action" class="menu-card CSV">
                    <h2>CSV</h2>
                </a>
                <a href="TeacherList.action" class="menu-card user">
                    <h2>ユーザー管理</h2>
                </a>
            </div>
        </section>
    </c:set>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content" value="${content}"></c:param>
</c:import>