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
                        <li><a href="MenuCSV.action">CSV</a></li>
                        <li>科目CSV読み込み</li>
                    </ol>
                </div>
            </div>
        </div>
        <h2 class="main-title">科目CSV読み込み</h2>

        <form action="SubjectCSVExecute.action"
              method="POST"
              enctype="multipart/form-data">

            <div class="tr">
                <label for="csv" class="th">
                    CSVファイル:
                </label>

                <div class="td">
                    <input type="file"
                           id="csv"
                           name="csv"
                           accept=".csv"
                           required>
                </div>
            </div>
            <div class="dropArea">
                <div id="dropArea">
                    ここにCSVをドラッグ＆ドロップ
                </div>
            </div>
            <div class="tr">
                <div class="td">
                    <button type="submit">
                        追加
                    </button>
                </div>
            </div>

        </form>

        <a href="MenuCSV.action">戻る</a>

    </c:param>

</c:import>