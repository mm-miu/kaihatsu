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
    /*サイドバー全体*/
    .layout{
      display: flex;
    }
    /* サイドバー */
    .sidebar{
      width: 200px;
      background-color: #eee;
      padding: 15px;
      margin-top: 10px;
    }
    /* メイン */
    .main{
      flex: 1;
      padding: 20px;
    }
    /* サイドバー 文字全体*/
    .sidebar ul {
      list-style: none;
      padding: 0;
    }
    /*　サイドバー成績管理　*/
    .sidebar_title {
      font-weight: bold;
      margin-top: 15px;
    }
    /* サイドバー文字*/
    .sidebar a {
      text-decoration: none;
      margin-bottom: 5px;
      color: blue;
    }
    /* フッター */
    footer{
      text-align: center;
      background-color: #EEEEEE;
    }
    

   /* こっからlogin.jspとerror.jsp */
    /* ログインボックス内の文字
    　ログイン名、パスワード、表示
    */
    .login-main {
      display: flex;
      justify-content: center;
      align-items: flex-start;
      padding-top: 80px;
    }
    /* ボックス 枠*/
    .login-box {
      width: 400px;
      background: white;
      border: 1px solid #ccc;
      padding: 30px;
      box-shadow: 0 0 5px rgba(0,0,0,0.1);
    }
    /* ボックス内の文字
    　ログイン
    */
    .login-box h2 {
      text-align: center;
      margin-bottom: 20px;
      color: #CCCCFF;
    }
    /* テキストボックス内の文字 */
    .login-box input[type="text"],
    .login-box input[type="password"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    /* ログインボタン */
    .login-box input[type="submit"] {
      display: block;
      margin: 20px auto 0;
      padding: 10px 30px;
      background-color: #1976d2;
      color: white;
      border: none;
      border-radius: 5px;
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
            <li><a href="/kaihatsu/scoremanager/main/Menu.action">メニュー</a></li><br>
            <li><a href="/kaihatsu/scoremanager/main/StudentList.action">学生管理</a></li><br>
            <label class="sidebar_title">成績管理</label>
            <li><a href="/kaihatsu/scoremanager/main/TestRegist.action">　　成績登録</a></li>
            <li><a href="/kaihatsu/scoremanager/main/TestList.action">　　成績参照</a></li><br>
            <li><a href="/kaihatsu/scoremanager/main/SubjectList.action">科目管理</a></li>
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