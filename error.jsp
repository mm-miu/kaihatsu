<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set  var="content">
    <div class="login-main" >
        <div class="login-box" >
            <c:choose>
                <c:when test="${empty user}">
                    <h2>ログイン</h2>
                    <ul>
                        <li>ログインに失敗しました。IDまたはパスワードが正しくありません。</li>
                    </ul>
                    <form action="Login.action" method="post">
                        <p>ログイン名<input type="text" name="id" value="${empty id ? '半角でご入力ください' : id}" required>
                        </p>
                        <p>パスワード<input type="password" name="password" id="password"
                            placeholder="${empty password ? '30字以内の半角英数字でご入力ください' : ''}" required></p>
                        <p><label>
                                <input type="checkbox" name="chk_d_ps" id="showPassword">
                                パスワードを表示
                            </label></p>
                        <input type="submit" value="ログイン" name="login">
                    </form>
                </c:when>
                <c:otherwise>
                    <p>エラーが発生しました</p>
                </c:otherwise>
            </c:choose>
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

<script>
    const checkbox = document.getElementById("showPassword");
    const password = document.getElementById("password");

    checkbox.addEventListener("change",function(){
        if(checkbox.checked){
            password.type = "text";
        }else{
            password.type = "password";
        }
    });
</script>
