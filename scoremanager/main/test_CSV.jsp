<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="content">

        <h2 class="main-title">成績CSV読み込み</h2>

        <form action="TestCSVExecute.action"
              method="POST"
              enctype="multipart/form-data">

            <div class="tr">
                <label for="csv" class="th">CSVファイル:</label>
                <div class="td">
                    <input type="file" id="csv" name="csv" accept=".csv" required>
                </div>
            </div>

            <div class="tr">
                <div class="td">
                    <button type="submit">追加</button>
                </div>
            </div>

        </form>

    </c:param>

</c:import>
