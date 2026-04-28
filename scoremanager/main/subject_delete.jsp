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
            button {
                
                border: 1px solid #333;
                border-radius: 5px;
                background-color: rgb(216, 64, 64);  
                color: white;
                padding: 3px 3px;
            }
            
            
            
        </style>
        
        <div class="border"></div>
        <h2 class="sub">科目情報削除</h2>
        <main class="contend">
        <p>「${subject.name}(${subject.cd})」を削除してもよろしいですか</p>
        <form action="SubjectDeleteExecute.action">
            <button name="delete" type="submit">削除</button>

        </form>

        <a href="SubjectList.action">戻る</a>
        
        </main>
        <div class="clear"></div>
    </c:param>
</c:import>