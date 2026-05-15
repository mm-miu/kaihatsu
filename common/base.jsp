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
    
    /* コンテンツ 枠*/
    .main{
      flex: 1;
      padding: 20px;
    }

    /* コンテンツタイトル h2*/
    .main-title {
      font-weight: bold;
      background-color: #e9ecef;
      padding: 10px;
      border-radius: 5px;
      margin-bottom: 30px;
    }
    /* 登録や削除の完了画面のメッセージ */
    .message{
      background-color: #c2c2e1;
      padding: 10px;
    }

    /* フッター */
    footer{
      text-align: center;
      background-color: #EEEEEE;
    }
    

   /* login.jspとerror.jsp */
    /* ログインボックス内の文字
    　ログイン名、パスワード、表示
    */
    .login-main {
      display: flex;
      justify-content: center;
      align-items: flex-start;
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
    /* テキストボックス　名前、パスワード */
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
    /* ログインボタン　ホバー */
    .login-box input[type="submit"]:hover {
      background-color: #1565c0;
    }
    /* パス表示ボタン　チェック後 */
    input[type="checkbox"] {
      transform: scale(1.5);
      accent-color: mediumpurple;
    }

    /* ログイン失敗時エラー文 */
    .errors_login_text{
      color: red;
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
    /* p ログアウト　メッセージ */
    .logout_main p{
      background-color: #c2c2e1;
      padding: 10px;
    }
    /* ログインへのリンク */
    .logout_main a{
      padding-top: 10px;
    }


     /* メニュー画面 menu.jsp*/
    /* カード全体 */
    .menu-grid{
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 25px;
      margin-top: 20px;
    }
    /* カード自体 */
    .menu-card{
      flex: 1;
      height: 140px;
      border-radius: 10px;
      justify-content: center;
      align-items: center;
      text-decoration: none;
      color: #0066FF;
      font-weight: bold;
      display: flex;
      cursor: pointer;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
      transition: 0.2s;
    }
    /* ホバー メニュー,　csvメニュー, 成績メニュー*/
    .menu-card:hover,
    .csv-menu-card:hover,
    .test-menu-card:hover {
      transform: translateY(-5px);
      opacity: 0.9;
    }
    /* カード　学生管理 */
    .menu-card.student{
      background-color: #ffeaa0;
    }
    /* カード　成績管理 */
    .menu-card.test{
      background-color: #ffc6c6;
    }
    /* カード　科目管理 */
    .menu-card.subject{
      background-color: #CCFFFF;
    }
    /* カード　クラス管理 */
    .menu-card.class{
      background-color: #ffd8b2;
    }
    /* カード　CSV読み込み */
    .menu-card.CSV{
      background-color: #98fcb2	;
    }

    /* カード　ユーザー管理 */
    .menu-card.user{
      background-color: #e7d5ff;
    }

    /*文字　成績管理　*/
    .menu-title{
      margin-bottom: 5px;
    }

    /* 文字　成績登録、参照　 */
    .menu-card.score a {
      display: block;
      margin: 3px;
      font-weight: normal;
    }


    /* 学生一覧画面 フォームは共通のcss */
    /* 新規登録 ＊科目の新規登録も同じ */
    .new{
      text-align: left;  
      margin-bottom: 10px;
      padding-left: 10px;
    }
    /* 検索　フォーム */
    .form{
      display: flex;
      flex-direction: column;
      gap: 15px;

      background: white;
      padding: 15px;
      border-radius: 10px;
      border: solid 1px #AAAAAA;
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
      position: relative;
    }
    /* 幅 クラス　入学年度*/
    .col-4 {
      flex: 1;
    }
    /* 在学中　チェックボックス */
    .col-2 {
      width: 120px;
      padding-top: 25px;
    }
    /* チェックボックス　位置調節 */
    .col-2.bt{
      margin-top: auto;
    }
    /* ラベル */
    label {
      margin-bottom: 5px;
    }
    /* プルダウン　セレクト */
    select{
      width: 100%;
      padding: 5px;
      border-radius: 4px;
      border: 1.5px solid #AAAAAA;
    }
    /* 項目(データ)の部分 */
    option {
      background-color: #ffffff; /* 中身を白に戻す */
      color: #000;
    }
    /* プルダウン　ホバー */
    select:hover {
      background-color: #e9ecef;
    }
    /* プルダウン　フォーカス */
    select:focus {
      border-color: #93b5ff;
      outline: none;
      box-shadow: 0 0 0 2px rgba(74,144,226,0.2);
    }
    /* ボーダー線を項目欄に表示させない */
    option[disabled][selected] {
      display: none;
    }
    /* フォームのチェックボックス */
    .form-check{
      align-items: center;
    }
    
    /* ボタン　共通 */
    button{
      padding: 6px 12px;
      border: none;
      background-color: #6c757d;
      color: white;
      border-radius: 4px;
      cursor: pointer;
    }
    /* ボタン　ホバー */
    button:hover {
      background-color: #5a6268;
    }

    /*ボタンの動き*/
    button:active{
      -webkit-transform: translateY(5px);
      transform: translateY(3px);/*下に動く*/
      box-shadow: 0px 0px 1px rgba(0, 0, 0, 0.2);/*影を小さく*/
      border-bottom: none;
    }
    /* 表*/
    /* テーブル */
    table{
      width: 100%;
      border-collapse: collapse;
      background: white;
      margin-bottom: 10px;
    }
    /* 列名行 */
    th{
      border: 1px solid #ccc;
      padding: 8px;
      text-align: left;
      background-color: #e9ecef;
      position: sticky;
      top: 0;
      z-index: 0;
    }
    /* データ行 */
    td{
      border: 1px solid #ccc;
      padding: 8px;
      text-align: left;
    }
    /* ホバー */
    tr:hover{
      background-color: #f0f3f6;
    }
    /* 検索時、入学年度未記入エラー文 */
    .errors_student_list{
      width: 100%;
      color: red;
      margin-top: 8px;
    }
    


    /* 新規登録、変更　テキスト入力用 */
    .content-input {
      margin-bottom: 10px;
      margin-top: 10px;
    }
    /* テキストエリアの細かい調節 */
    .content-input input{
      width: 100%;
      line-height: 2;
    }
    /* 変更時、新規などの時用ボタン */
    .button-2 button{
      margin-top: 20px;
    }
    /* 変更画面の在学中チェックボタンの調節 */
    .is_attend{
      margin-top: 10px;
    }
    /* 変更画面の変更ボタンの調節*/
    .button-2.change{
      margin-top: -20px;
    }


    /* 成績 */
    /* 成績メニュー menu_test.jsp*/
    .test-menu-card{
      flex: 1;
      height: 170px;
      border-radius: 10px;
      margin-top: 25px;
      justify-content: center;
      align-items: center;
      text-decoration: none;
      color: #0066FF;
      font-weight: bold;
      display: flex;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
      transition: 0.2s;
    }
    /* 成績登録　カード */
    .test-menu-card.test_regist{
      background-color: #ffc3c3;
    }
    /* 成績登録　検索ボタンの調節 */
    .button-2.submit{
      margin-top: 10px;
      padding-left: 40px;
      padding-right: 40px;
    }
    /* 成績参照　カード */
    .test-menu-card.test_list{
      background-color: #ffc3c3;
    }
    /* 成績参照 h4　科目情報　学生情報*/
    .content-title{
      margin-left: 10px;
    }
    /* 学生番号で検索　検索ボタンの細かい調節
     */
    .col-2.test{
      padding-left: 1em;
      padding-right: 1em;
      height: 3em;
      padding-top: 35px;
    }
    /* 成績参照注意書き */
    .p_note{
      color: cornflowerblue;
    }


    /* 科目 */
    /* 科目管理の削除用ボタン */
    .border button {
      border: 1px solid #333;
      border-radius: 5px;
      background-color: rgb(216, 64, 64);  
      color: white;
      padding: 3px 3px;
    }


    /* CSV */
    /* CSV読み込みメニュー */
    /* カード自体 */
    .csv-menu-card{
      flex: 1;
      height: 170px;
      border-radius: 10px;
      margin-top: 25px;
      justify-content: center;
      align-items: center;
      text-decoration: none;
      color: #0066FF;
      font-weight: bold;
      display: flex;
      cursor: pointer;
      box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
      transition: 0.2s;
    }
    /* カード　学生登録*/
    .csv-menu-card.student_CSV{
      background-color: 	#ffeaa0;
    }
    /* カード成績登録 */
    .csv-menu-card.test_CSV{
      background-color: #ffc6c6;
    }
    /* カード　科目登録 */
    .csv-menu-card.subject_CSV{
      background-color: #CCFFFF;
    }


    /*CSVファイル選択追加画面
    　student_CSV.jsp subject_CSV.jsp test_CSV.jsp  */
    /* ファイル選択と追加ボタンの全体的な調節 */
    .tr{
      margin: 20px;
    }
    /* csvファイル：　の文字とファイル選択ボタンの調節 */
    .td{
      margin-top: 15px;
    }
    /* ドラックドロップエリア枠 */
    .dropArea{
      width: auto;
      height:180px;
      margin-right: 0;
      border:10px dashed #e3c3f9;
      border-radius:10px;
      text-align:center;
      line-height: 175px;
      color:#ba96d2;
      margin-top:10px;
      margin-left:auto;
      cursor:pointer;
    }


    /* csv書き込み */
    /* テキストエリア　エリア上限 */
    textarea{
      resize: both;
      max-width: 800px;
      max-height: 200px;
      min-width: 200px;
      min-height: 100px;
      margin-bottom: 20px;
    }


    /* ユーザー管理 */
    /* ユーザー管理チェックボックス調節 */
    .content-input input[type="checkbox"] {
      width: 1%;
      line-height: 5;
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
            <li><a href="/kaihatsu/scoremanager/main/SubjectList.action">科目管理</a></li><br>
            <li><a href="/kaihatsu/scoremanager/main/ClassNumList.action">クラス管理</a></li><br>
            <li><a href="/kaihatsu/scoremanager/main/MenuCSV.action">CSV</a></li><br>
            <li><a href="/kaihatsu/scoremanager/main/TeacherList.action">ユーザー管理</a></li>
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
  

  <script>
    const dropArea = document.getElementById("dropArea");
    const fileInput = document.getElementById("csv");

    // ドラッグ中
    dropArea.addEventListener("dragover", (e) => {
        e.preventDefault();
        dropArea.style.backgroundColor = "#eef";
    });

    // 離れた
    dropArea.addEventListener("dragleave", () => {
        dropArea.style.backgroundColor = "";
    });

    // ドロップ
    dropArea.addEventListener("drop", (e) => {
        e.preventDefault();
        dropArea.style.backgroundColor = "";

        const files = e.dataTransfer.files;

        if (files.length > 0) {

            // CSVチェック（軽く）
            if (!files[0].name.endsWith(".csv")) {
                alert("CSVファイルを選択してください");
                return;
            }

            fileInput.files = files; // ←これが超重要
        }
    });
  </script>
</body>

</html>


