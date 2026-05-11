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

            <a href="StudentCSV.action" class="csv-menu-card student_CSV">
                <h2>学生登録</h2>
            </a>

            <a href="TestCSV.action" class="csv-menu-card test_CSV">
                <h2>成績登録</h2>
            </a>

            <a href="SubjectCSV.action" class="csv-menu-card subject_CSV">
                <h2>科目登録</h2>
            </a>

        </div>

            <h2 class="main-title">CSV書き込み</h2>

        <div class="menu-grid">
            <div class="csv-menu-card student_CSV">
                <h2><a href="StudentCreateCSVExecute.action">学生</a></h2>
            </div>
        </div>
        
    </c:param>
</c:import>