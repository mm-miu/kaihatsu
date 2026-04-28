<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

    <c:set var="content">
        <section>
            <h2 class="main-title">メニュー</h2>
            <div class="menu-grid">
                <div class="menu-card student">
                    <a href="StudentList.action">学生管理</a>
                </div>
                    <div class="menu-card score">
                        <div class="menu-title">成績管理</div>
                            <a href="TestRegist.action">成績登録</a>
                            <a href="TestList.action">成績参照</a>
                    </div>
                <div class="menu-card subject">
                    <a href="SubjectList.action">科目管理</a>
                </div>
                <div class="menu-card class">
                    <a href="ClassList.action">クラス管理</a>
                </div>
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