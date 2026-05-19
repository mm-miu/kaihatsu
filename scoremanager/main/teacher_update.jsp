<%-- 学生一覧JSP --%>
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
                        <li><a href="TeacherList.action">ユーザー管理</a></li>
                        <li>ユーザー情報変更</li>
                    </ol>
                </div>
            </div>
        </div>

        <h2 class="main-title">ユーザー情報変更</h2>

            <div class="permission-wrapper">
                <input type="checkbox" id="permissionToggle" hidden>
                <label class="permission-title" for="permissionToggle">
                    権限
                </label>
                    <div class="permission-tooltip">
                        <div><b>■ 権限1</b></div>
                        <div>・変更：すべて可能</div>

                        <div><b>■ 権限2</b></div>
                        <div>・変更：自身のパスワードと名前の変更可能／自身の学校ユーザーの名前変更可能</div>

                        <div><b>■ 権限3</b></div>
                        <div>・変更：自身のパスワードと名前の変更可能／他のユーザーの変更不可</div>
                    </div>
            </div>

        <form action="TeacherUpdateExecute.action">
           
            <div class="content-input">
                <label for="name">id：</label>
                ${C_Id}
                <input type="hidden" name="C_Id" value="${C_Id}">
                <br>
                <c:choose>
                    <c:when test="${user.id.equals(C_Id) || my_authority=='1'}">
                        <label for="password">パスワード</label>
                        <input type="password" id="password" name="password" value="${password}"
                        pattern="^[A-Za-z0-9]{1,30}$" title="半角英数字30文字以内で入力してください。" maxlength="30"
                        required>
                        <label for="showPassword">パスワードを表示
                            <input type="checkbox" name="chk_d_ps" id="showPassword" >
                        </label>
                        <br><br>
                        
                    </c:when>
                    <c:otherwise>
                        <label for="pass">パスワード</label><br>
                        ${password}<br>
                    </c:otherwise>
                </c:choose>

                <label for="name">氏名</label>
                <input type="text" id="name" name="name" 
                    value="${name}"
                    placeholder="${name}" maxlength="10" required>
                <br>
            </div>
            
            <c:if test="${ my_authority=='1'}">
                <label for="class_num">所属校</label>
                <select name="school_cd" >
                    <option value="0" selected disabled>--------</option>
                    <c:forEach var="sList" items="${schoolList}">
                        <option value="${sList.cd}" label="${sList.name}"
                            <c:if test="${sList.cd==school}">
                                    selected
                            </c:if>
                            >${sList.name}
                        </option>
                    </c:forEach>
                </select>
                <br>
                
                <label for="my_authority">権限</label>
                <select name="authority">
                    <option value="0" selected disabled>--------</option>
                    <c:forEach var="i" begin="1" end="3">
                        <option value="${i}"
                            <c:if test="${i==authority}">
                                    selected
                            </c:if>
                            >${i}
                        </option>
                    </c:forEach>
                </select>
            </c:if>
            
               
            <br>
            
            <div class="button-2">
                <button name="login" type="submit">変更</button>
                <br>
            </div>
        </form>

        <a href="TeacherList.action">戻る</a>
    </c:param>

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