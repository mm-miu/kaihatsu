<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2 class="main-title">CSV読み込み</h2>
        <div class="menu-grid">
            
            <div class="menu-card student_CSV">
                <h2><a href="StudentCSV.action">学生登録</a></h2>
            </div>

            <div class="menu-card subject_CSV">
                <h2><a href="SubjectCSV.action">科目登録</a></h2>
            </div>

            <div class="menu-card test_CSV">
                <h2><a href="TestCSV.action">成績登録</a></h2>
            </div>
        </div>
    </c:param>
</c:import>