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
                        <li>成績管理</li>
                    </ol>
                </div>
            </div>
        </div>

        <h2 class="main-title">成績管理</h2>
        <div class="menu-grid">
            
            <a href="TestRegist.action" class="test-menu-card test_regist">
                <h2>成績登録</h2>
            </a>

            <a href="TestList.action" class="test-menu-card test_list">
                <h2>成績参照</h2>
            </a>

        </div>
    </c:param>
</c:import>