<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">
        <h2 class="main-title">成績管理</h2>
        <div class="menu-grid">
            
            <div class="test-menu-card test_regist">
                <h2><a href="TestRegist.action">成績登録</a></h2>
            </div>

            <div class="test-menu-card test_list">
                <h2><a href="TestList.action">成績参照</a></h2>
            </div>

        </div>
    </c:param>
</c:import>