<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2 class="main-title">CSV読み込み</h2>
<<<<<<< HEAD
        <a href="student_CSV.jsp">学生登録</a>
        <a href="subject_CSV.jsp">科目登録</a>
        <a href="test_CSV.jsp">成績登録</a>
=======
        <div class="menu-grid">
            
            <div class="menu-card student_CSV">
                <a href="StudentCSV.action">学生登録</a>
            </div>

            <div class="menu-card subject_CSV">
                <a href="SubjectCSV.action">科目登録</a>
            </div>

            <div class="menu-card test_CSV">
                <a href="TestCSV.action">成績登録</a>
            </div>
        </div>
>>>>>>> 2a60bfd8294e0a5ad14db5b5358ca1fa4ccb360b
    </c:param>
</c:import>