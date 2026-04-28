<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <style>
            p.scses {
                text-align: center; 
                background-color: rgb(177, 237, 185); 
                    }
            a.scses{ 
                text-align: center;
                    }
        </style>
        
        <p class="scses">変更が完了しました</p>
        <a class="scses" href="SubjectList.action" >科目一覧</a>
        
    </c:param>
</c:import>