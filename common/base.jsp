<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>

<head>
  <meta charset="UTF-8">
  <title>${param.title }</title>

  <style>
    /* 全体背景 */
    body {
      margin: 10px;
      background-color: #f5f5f5;
      font-family: sans-serif;
    }
    /* タイトル背景 */
    h1 {
      text-align: center;
      padding: 10px 0;
      margin: 0px;
    }
    /* ヘッダー */
    header{
      text-align: right;
      background-color: #dfdffd;
      padding: 10px;
    }
    /* span　ユーザー名 */
    span{
      margin: 5px;
    }

    .layout{
      display: flex;
    }
    .sidebar{
      width: 200px;
      background-color: #eee;
      padding: 15px;
      margin-top: 10px;
    }
    .main{
      flex: 1;
      padding: 20px;
    }
  </style>

</head>

<body>

  <header>
    <h1>${param.title}</h1>
    <c:if test="${!empty user}">
      <span>
        ${user.getName()}様
      </span>
      <a href="/kaihatsu/scoremanager/main/Logout.action">ログアウト</a>
    </c:if>
  </header>

  <div class="layout">

    <c:if test="${!empty user}">
      <div class="sidebar">
        <nav>
          <ul>
            <a href="/kaihatsu/scoremanager/main/Menu.action">メニュー</a><br>
            <a href="/kaihatsu/scoremanager/main/StudentList.action">学生管理</a><br>
            <label class="menu_title">成績管理</label>
            <li><a href="/kaihatsu/scoremanager/main/TestRegist.action">成績登録</a></li>
            <li><a href="/kaihatsu/scoremanager/main/TestList.action">成績参照</a></li><br>
            <a href="/kaihatsu/scoremanager/main/SubjectList.action">科目管理</a>
          </ul>
        </nav>
      </div>
    </c:if>
    <div class="main">
      <c:out value="${param.content}" escapeXml="false" ></c:out>
    </div>
  </div>

  <footer>
    <p>© 2023 TIC</p>
    <p>大原学園</p>
  </footer>
  
</body>

</html>