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
<<<<<<< HEAD
        <a href="student_CSV.jsp">学生登録</a>
        <a href="subject_CSV.jsp">科目登録</a>
        <a href="test_CSV.jsp">成績登録</a>
=======
        <div class="menu-grid">
            
            <div class="menu-card student_CSV">
                <a href="StudentCSV.action">学生登録</a>
            </div>
=======
>>>>>>> 6d01d3befd4d880e8c43e392bfd4ce07ea6b5d42

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
>>>>>>> 2a60bfd8294e0a5ad14db5b5358ca1fa4ccb360b
    </c:param>
</c:import>