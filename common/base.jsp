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
    /* コンテンツ 枠*/
    .main{
      flex: 1;
      padding: 20px;
    }

    /* コンテンツタイトル */
    .main-title {
      font-weight: bold;
      background-color: #e9ecef;
      padding: 10px;
      border-radius: 5px;
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


    /* ログアウト画面 logout.jsp*/
    /* 画面全体*/
    .logout_main{
      margin: 10px;
      margin-left: 20px;
      margin-right: 20px;
      text-align: center;
    }
    /* h2　ログアウト*/
    .logout_main h2{
      background-color: #EEEEEE;
      padding: 20px;
      text-align: left;
    }
    /* p メッセージ */
    .logout_main p{
      background-color: #6699CC;
      padding: 10px;
    }
    /* ログインへのリンク */
    .logout_main a{
      padding-top: 10px;
    }


     /* メニュー画面 menu.jsp*/
    /* カード全体 */
    .menu-grid{
      display: flex;
      gap: 20px;
      margin-top: 20px;
    }
    /* カード自体 */
    .menu-card{
      flex: 1;
      height: 120px;
      border-radius: 10px;
      justify-content: center;
      align-items: center;
      text-decoration: none;
      color: black;
      font-weight: bold;
      display: flex;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
      transition: 0.2s;
    }
    /* ホバー */
    .menu-card:hover {
      transform: translateY(-5px);
      opacity: 0.9;
    }
    /* カード　学生管理 */
    .menu-card.student{
      background-color: #FFCCCC;
      text-align: center;
    }
    /* カード　成績管理 */
    .menu-card.score{
      background-color: #CCFFCC;
      text-align: center;
      flex-direction: column;
    }
    /* カード　科目管理 */
    .menu-card.subject{
      background-color: 	#CCFFFF;
      text-align: center;
    }
    .menu-card.class{
      background-color: #FFFF99;
      text-align: center;
    }
    /*文字　成績管理*/
    .menu-title{
      margin-bottom: 5px;
    }

    /* 文字　成績登録、参照　 */
    .menu-card.score a {
      display: block;
      margin: 3px;
      font-weight: normal;
    }


    /* 学生一覧画面 
      student.jsp
    */
    /* 新規登録 */
    .new{
      text-align: right;  
      margin-bottom: 10px;
    }
    /* 検索　フォーム */
    .form{
      display: flex;
      gap: 15px;
      align-items: flex-end;
      background: #f8f9fa;
      padding: 15px;
      border-radius: 5px;
      margin-bottom: 15px;
    }
    /*入力ボックス　横並び */
    .row{
      display: flex;
      width: 100%;
      gap: 10px;
    }
    /* カラム */
    .col-4, .col-2 {
      display: flex;
      flex-direction: column;
    }
    /* 幅 */
    .col-4 {
      flex: 1;
    }
    .col-2 {
      width: 120px;
      padding-top: 25px;
    }
    /* ラベル */
    label {
      margin-bottom: 5px;
    }
    /* セレクト */
    select{
      width: 100%;
      padding: 5px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    /* フォームのチェックボックス */
    .form-check{
      align-items: center;
    }
    /* ボタン */
    button{
      padding: 6px 12px;
      border: none;
      background-color: #6c757d;
      color: white;
      border-radius: 4px;
      cursor: pointer;
    }
    button:hover {
      background-color: #5a6268;
    }
    /* テーブル */
    table{
      width: 100%;
      border-collapse: collapse;
      background: white;
    }
    th{
      border: 1px solid #ccc;
      padding: 8px;
      text-align: left;
      background-color: #e9ecef;
    }
    td{
      border: 1px solid #ccc;
      padding: 8px;
      text-align: left;
    }
    tr:hover{
      background-color: #dde3e9;
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