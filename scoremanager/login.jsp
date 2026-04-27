<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<head>
  <meta charset="UTF-8">
  <title>${param.title }</title>

  <style>
    
    /* ログインボックス内の文字
    　ログイン名、パスワード、表示
    */
    .main {
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


<c:set var="content" >
    <div class="main" >
        <div class="login-box" >
            <h2>ログイン</h2>
            <form action="Login.action" method="post">
                <p>ログイン名<input type="text" name="id" 
                    value="${empty id ? "半角でご入力ください" : id}" 
                    required>
                </p>
                <p>パスワード<input type="password" name="password"
                    placeholder="${empty password ? "30字以内の半角英数字でご入力ください" : ""}" 
                    required></p>
                <p><label>
                    <input type="checkbox" name="chk_d_ps">
                    パスワードを表示
                </label></p>
                <input type="submit" value="ログイン" name="login">
            </form>
        </div>
    </div>
</c:set>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content" value="${content}"></c:param>
</c:import>
